package engine.dsl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Condition {

    private String name;
    private List<ConditionParam> params = new ArrayList<>();
    private Map<String, Object> paramsMap;

    public Map<String, Object> getParamsMap() {
        if (paramsMap == null) {
            paramsMap = params.stream()
                    .collect(Collectors.toUnmodifiableMap(ConditionParam::getName, ConditionParam::getValue));
        }
        return paramsMap;
    }
}
