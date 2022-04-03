package example;

import engine.evaluator.ConditionEvaluator;

import java.util.Map;

public class AtLeastOneProcessCondition implements ConditionEvaluator<SimpleRuleExecutionContext> {

    @Override
    public String getName() {
        return "atLeastOneProcess";
    }

    @Override
    public boolean evaluate(SimpleRuleExecutionContext context, Map<String, Object> params) {
        Integer someParamValue = (Integer) params.get("someParam");
        System.out.println(getName() + " " + someParamValue);
        return context.someContextSpecificValue < someParamValue;
    }
}
