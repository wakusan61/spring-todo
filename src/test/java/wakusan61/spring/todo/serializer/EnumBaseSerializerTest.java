package wakusan61.spring.todo.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wakusan61.spring.todo.common.EnumBase;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumBaseSerializerTest {

  private enum TestEnum implements EnumBase {
    VALUE1(1), VALUE2(2);

    private final int code;

    TestEnum(int code) {
      this.code = code;
    }

    @Override
    public int getCode() {
      return code;
    }
  }

  @Test
  @DisplayName("EnumBaseを正しくシリアライズする")
  void serializeEnumBaseCorrectly() throws IOException {
    StringWriter writer = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(writer);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
    EnumBase enumValue = TestEnum.VALUE1;

    new EnumBaseSerializer().serialize(enumValue, jsonGenerator, serializerProvider);
    jsonGenerator.flush();

    assertEquals("1", writer.toString());
  }

  @Test
  @DisplayName("null EnumBaseをシリアライズする際に例外をスローする")
  void serializeNullEnumBaseThrowsException() throws IOException {
    StringWriter writer = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(writer);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

    assertThrows(NullPointerException.class, () ->
            new EnumBaseSerializer().serialize(null, jsonGenerator, serializerProvider));
  }
}