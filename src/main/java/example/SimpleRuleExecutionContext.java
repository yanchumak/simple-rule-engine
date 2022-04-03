package example;

import engine.evaluator.RuleEvaluationContext;

public class SimpleRuleExecutionContext implements RuleEvaluationContext {

    public final int someContextSpecificValue;

    public SimpleRuleExecutionContext(int someSpecificValue) {
        this.someContextSpecificValue = someSpecificValue;
    }
}
