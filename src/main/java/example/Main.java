package example;

import engine.dsl.Rule;
import engine.evaluator.RuleEvaluator;
import engine.parser.RuleParser;
import engine.validator.RuleValidator;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String ruleJson = new Scanner(Main.class.getResourceAsStream("/rule.json"),
                StandardCharsets.UTF_8).useDelimiter("\\A").next();

        Rule rule = RuleParser.forJsonString().parse(ruleJson);

        RuleValidator ruleValidator = RuleValidator.newInstance();
        ruleValidator.validate(rule);

        RuleEvaluator<SimpleRuleExecutionContext> ruleEvaluator = RuleEvaluator.of(List.of
                (new AtLeastOneProcessCondition(), new SomeCondition()));
        ruleEvaluator.evaluate(new SimpleRuleExecutionContext(4), rule);
    }
}
