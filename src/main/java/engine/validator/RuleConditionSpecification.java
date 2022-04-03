package engine.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
class RuleConditionSpecification {

    private List<ConditionValidator> conditions = new ArrayList<>();
}
