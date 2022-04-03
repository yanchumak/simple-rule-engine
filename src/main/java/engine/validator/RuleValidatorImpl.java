package engine.validator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.dsl.Condition;
import engine.dsl.Rule;
import engine.dsl.Composition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class RuleValidatorImpl implements RuleValidator {

    public static final String RULE_CONDITION_SPECIFICATION_JSON_PATH = "rule-conditions-spec.json";

    private final Map<String, ConditionValidator> conditionValidators;

    public RuleValidatorImpl() {
        RuleConditionSpecification ruleConditionSpecification = loadRuleValidation();
        conditionValidators = ruleConditionSpecification.getConditions().stream()
                .collect(Collectors.toMap(ConditionValidator::getName, Function.identity()));
    }

    private static RuleConditionSpecification loadRuleValidation() {
        InputStream is = ClassLoader.getSystemResourceAsStream(RULE_CONDITION_SPECIFICATION_JSON_PATH);
        Objects.requireNonNull(is, "can't read file " + RULE_CONDITION_SPECIFICATION_JSON_PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(is, RuleConditionSpecification.class);
        } catch (IOException e) {
            throw new RuntimeException("can't read file" + RULE_CONDITION_SPECIFICATION_JSON_PATH, e);
        }
    }

    @Override
    public void validate(Rule rule) throws ValidationException {
        if (rule == null) {
            throw new ValidationException("rule name can't be null");
        }

        if (rule.getComposition() == null) {
            throw new ValidationException("root composition can't be null");
        }

        validate(rule.getComposition());
    }


    private void validate(Composition composition) throws ValidationException {
        List<Condition> conditions = composition.getConditions();
        if (conditions == null || conditions.isEmpty()) {
            throw ValidationException.createFormatted(
                    "composition should have at least one condition\n%s", composition);
        }

        if (conditions.size() > 1 && composition.getOperator() == null) {
            throw ValidationException.createFormatted(
                    "operator should be specified for 2 and more conditions\n%s", composition);
        }

        conditions.forEach(condition -> {
            ConditionValidator conditionValidator = conditionValidators.get(condition.getName());
            if (conditionValidator == null) {
                throw ValidationException.createFormatted("condition '%s' is not valid", condition.getName());
            }
            conditionValidator.validate(condition);
        });

        Composition childComposition = composition.getComposition();
        if (childComposition != null) {
            validate(childComposition);
        }
    }
}
