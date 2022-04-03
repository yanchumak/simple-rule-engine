package engine.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class ConditionParamValidator {

    enum Type {
        INTEGER,
        STRING,
        LIST_INTEGER,
        LIST_STRING
    }

    private String name;
    private Type type;

    final void validate(Object value) throws ValidationException {
        //TODO check by type
    }

}
