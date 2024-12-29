package wakusan61.spring.todo.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import wakusan61.spring.todo.common.EnumBase;

import java.io.IOException;

public class EnumBaseSerializer extends JsonSerializer<EnumBase> {
  @Override
  public void serialize(EnumBase value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeNumber(value.getCode());
  }
}