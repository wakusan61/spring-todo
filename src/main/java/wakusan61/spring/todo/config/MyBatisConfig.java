package wakusan61.spring.todo.config;

import wakusan61.spring.todo.model.CodeEnum;
import wakusan61.spring.todo.typehandlers.GenericEnumTypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            try {
                List<Class<?>> classes = PackageScanner.getClasses("wakusan61.spring.todo.model");

                for (Class<?> clazz : classes) {
                    for (Class<?> innerClass : clazz.getDeclaredClasses()) {
                        if (innerClass.isEnum() && CodeEnum.class.isAssignableFrom(innerClass)) {
                            @SuppressWarnings("unchecked")
                            Class<? extends Enum<?>> enumClass = (Class<? extends Enum<?>>) innerClass;
                            configuration.getTypeHandlerRegistry().register(enumClass, new GenericEnumTypeHandler(enumClass));
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed to register TypeHandler", e);
            }
        };
    }
}