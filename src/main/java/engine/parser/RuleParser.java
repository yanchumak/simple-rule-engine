package engine.parser;

import engine.dsl.Rule;

public interface RuleParser<T> {

    Rule parse(T input) throws ParseRuleException;

    static RuleParser<String> forJsonString() {
        return new JsonStringRuleParser();
    }
}
