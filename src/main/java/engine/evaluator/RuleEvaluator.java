package engine.evaluator;

import engine.dsl.Rule;

import java.util.List;

public interface RuleEvaluator<C extends RuleEvaluationContext> {

    boolean evaluate(C context, Rule rule);

    static <C extends RuleEvaluationContext> RuleEvaluator<C> of(List<ConditionEvaluator<C>> conditionEvaluators) {
        return new RuleEvaluatorImpl<>(conditionEvaluators);
    }
}
