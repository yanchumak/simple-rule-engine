package engine.validator;

import engine.dsl.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ConditionValidator {

   private String name;
   private List<ConditionParamValidator> params = new ArrayList<>();

   final void validate(Condition condition) throws ValidationException {
        Map<String, Object> conditionParams = condition.getParamsMap();
        Set<String> allowedParams = new HashSet<>();
        params.forEach(paramValidator -> {
            String paramName = paramValidator.getName();
            Object paramValue = conditionParams.get(paramName);

            if (paramValue == null) {
                throw ValidationException.createFormatted(
                        "param '%s' in condition '%s' should be present and can't be null", paramName, name);
            }

            paramValidator.validate(paramValue);
            allowedParams.add(paramName);
        });

        if (conditionParams.size() != params.size()) {
            condition.getParams().forEach(conditionParam -> {
                if (!allowedParams.contains(conditionParam.getName())) {
                    throw ValidationException.createFormatted(
                            "param '%s' is not allowed in condition '%s'", conditionParam.getName(), condition.getName());
                }
            });
        }
    }
}
