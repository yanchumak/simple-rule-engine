package engine.dsl;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Composition {

    private Operator operator;
    private List<Condition> conditions = new ArrayList<>();
    private Composition composition;

    public List<Condition> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

}
