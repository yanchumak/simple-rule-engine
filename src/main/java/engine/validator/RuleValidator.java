package engine.validator;

import engine.dsl.Rule;

public interface RuleValidator {

    void validate(Rule rule) throws ValidationException;

    static RuleValidator newInstance() {
        return new RuleValidatorImpl();
    }
}
