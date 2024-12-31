package wakusan61.spring.todo.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import wakusan61.spring.todo.common.EnumBase;

import java.io.IOException;

public class EnumBaseDeserializer extends JsonDeserializer<EnumBase> {
  private final Class<? extends EnumBase> enumClass;

  public EnumBaseDeserializer(Class<? extends EnumBase> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public EnumBase deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    int code = p.getIntValue();
    for (EnumBase enumConstant : enumClass.getEnumConstants()) {
      if (enumConstant.getCode() == code) {
        return enumConstant;
      }
    }
    throw new IllegalArgumentException("Unknown enum code. " + enumClass.getName() + ":" + code);
  }
}