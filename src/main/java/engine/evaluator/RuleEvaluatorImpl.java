package engine.evaluator;

import engine.dsl.Condition;
import engine.dsl.Operator;
import engine.dsl.Rule;
import engine.dsl.Composition;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class RuleEvaluatorImpl<C extends RuleEvaluationContext> implements RuleEvaluator<C> {

    private final Map<String, ConditionEvaluator<C>> conditionEvaluators;

    RuleEvaluatorImpl(List<ConditionEvaluator<C>> conditionEvaluatorsList) {
        this.conditionEvaluators = conditionEvaluatorsList.stream()
                .collect(Collectors.toMap(ConditionEvaluator::getName, Function.identity()));
    }

    @Override
    public boolean evaluate(C context, Rule rule) {
        return evaluate(context, rule.getComposition());
    }

    private boolean evaluate(C context, Composition composition) {
        List<Condition> conditions = composition.getConditions();
        if (composition.getOperator() == Operator.AND) {
            boolean result = conditions.stream()
                    .allMatch(condition -> evaluate(context, condition));
            if (composition.getComposition() == null) {
                return result;
            }
            return result && evaluate(context, composition.getComposition());
        } else {
            boolean result = conditions.stream()
                    .anyMatch(condition -> evaluate(context, condition));
            if (composition.getComposition() == null) {
                return result;
            }
            return result || evaluate(context, composition.getComposition());
        }
    }

    private boolean evaluate(C context, Condition condition) {
        ConditionEvaluator<C> conditionEvaluator = conditionEvaluators.get(condition.getName());
        return conditionEvaluator.evaluate(context, condition.getParamsMap());
    }

}
