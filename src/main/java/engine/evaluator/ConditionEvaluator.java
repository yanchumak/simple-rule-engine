package engine.evaluator;

import java.util.Map;

public interface ConditionEvaluator<C extends RuleEvaluationContext> {

    String getName();

    boolean evaluate(C context, Map<String, Object> params);

}
