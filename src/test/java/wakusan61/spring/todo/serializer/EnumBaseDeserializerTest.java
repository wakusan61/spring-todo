package wakusan61.spring.todo.serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wakusan61.spring.todo.common.EnumBase;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumBaseDeserializerTest {

  private JsonParser jsonParser;
  private DeserializationContext deserializationContext;
  private EnumBaseDeserializer enumBaseDeserializer;

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

  @BeforeEach
  void setUp() throws IOException {
    jsonParser = new JsonFactory().createParser("{\"code\":1}");
    ObjectMapper mapper = new ObjectMapper();
    deserializationContext = new DefaultDeserializationContext.Impl(mapper.getDeserializationContext().getFactory());
    enumBaseDeserializer = new EnumBaseDeserializer(TestEnum.class);
  }

  @Test
  @DisplayName("正しいコードでEnumをデシリアライズする")
  void deserializeWithValidCode() throws IOException {
    jsonParser.nextToken(); // Move to the start of the object
    jsonParser.nextToken(); // Move to the field name
    jsonParser.nextToken(); // Move to the field value

    EnumBase result = enumBaseDeserializer.deserialize(jsonParser, deserializationContext);

    assertEquals(TestEnum.VALUE1, result);
  }

  @Test
  @DisplayName("無効なコードで例外をスローする")
  void deserializeWithInvalidCodeThrowsException() throws IOException {
    jsonParser = new JsonFactory().createParser("{\"code\":3}");
    jsonParser.nextToken(); // Move to the start of the object
    jsonParser.nextToken(); // Move to the field name
    jsonParser.nextToken(); // Move to the field value

    assertThrows(IllegalArgumentException.class, () ->
            enumBaseDeserializer.deserialize(jsonParser, deserializationContext));
  }

  @Test
  @DisplayName("nullコードで例外をスローする")
  void deserializeWithNullCodeThrowsException() throws IOException {
    jsonParser = new JsonFactory().createParser("{\"code\":null}");
    jsonParser.nextToken(); // Move to the start of the object
    jsonParser.nextToken(); // Move to the field name
    jsonParser.nextToken(); // Move to the field value

    assertThrows(IOException.class, () ->
            enumBaseDeserializer.deserialize(jsonParser, deserializationContext));
  }
}