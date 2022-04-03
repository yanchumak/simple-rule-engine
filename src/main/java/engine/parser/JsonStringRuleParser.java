package engine.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.dsl.Rule;

class JsonStringRuleParser implements RuleParser<String> {

    private final ObjectMapper objectMapper;

    JsonStringRuleParser() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Rule parse(String input) throws ParseRuleException {
        try {
            return objectMapper.readValue(input, Rule.class);
        } catch (JsonProcessingException e) {
            throw new ParseRuleException("Can't parse rule json", e);
        }
    }
}
