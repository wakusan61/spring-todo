package wakusan61.spring.todo.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wakusan61.spring.todo.common.EnumBase;
import wakusan61.spring.todo.serializer.EnumBaseDeserializer;
import wakusan61.spring.todo.serializer.EnumBaseSerializer;

@Configuration
public class JacksonConfig {
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(EnumBase.class, new EnumBaseSerializer());
    module.addDeserializer(EnumBase.class, new EnumBaseDeserializer(EnumBase.class));
    mapper.registerModule(module);
    return mapper;
  }
}