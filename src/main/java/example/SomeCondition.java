package example;

import engine.evaluator.ConditionEvaluator;

import java.util.List;
import java.util.Map;

public class SomeCondition implements ConditionEvaluator<SimpleRuleExecutionContext> {

    @Override
    public String getName() {
        return "someCondition";
    }

    @Override
    public boolean evaluate(SimpleRuleExecutionContext context, Map<String, Object> params) {
        List<Integer> value = (List<Integer>) params.get("someParam");
        System.out.println(getName());
        return context.someContextSpecificValue > value.size();
    }
}
