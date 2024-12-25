package wakusan61.spring.todo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import wakusan61.spring.todo.model.CodeEnum;

import java.io.IOException;

public class GenericEnumIntSerializer extends JsonSerializer<CodeEnum> {
    @Override
    public void serialize(CodeEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getCode());
    }
}