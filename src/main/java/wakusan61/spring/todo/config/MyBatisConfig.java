package wakusan61.spring.todo.config;

import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wakusan61.spring.todo.common.EnumBase;
import wakusan61.spring.todo.handler.EnumTypeHandler;

import java.util.Set;

@Configuration
public class MyBatisConfig {

  /**
   * MyBatisの設定をカスタマイズするためのBeanを定義します。
   *
   * @return ConfigurationCustomizerのインスタンス
   */
  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    return new ConfigurationCustomizer() {
      @Override
      public void customize(org.apache.ibatis.session.Configuration configuration) {
        // 特定のパッケージ内のEnumTypeHandlerを登録するメソッドを呼び出す
        registerEnumTypeHandlers(configuration, "wakusan61.spring.todo.common");
      }
    };
  }

  /**
   * 指定されたパッケージ内のEnumBaseを実装するEnumをリフレクションを使って探し、TypeHandlerを登録します。
   *
   * <p>このメソッドの目的は、EnumBaseインターフェースを実装するすべてのEnumクラスに対して
   * EnumTypeHandlerを自動的に登録することです。これにより、MyBatisがEnumの値をデータベースに
   * 保存および取得する際に、適切なTypeHandlerを使用できるようになります。</p>
   *
   * @param configuration MyBatisの設定オブジェクト
   * @param basePackage スキャンするベースパッケージ
   */
  private void registerEnumTypeHandlers(org.apache.ibatis.session.Configuration configuration, String basePackage) {
    // Reflectionsライブラリを使って指定されたパッケージ内のクラスをスキャン
    Reflections reflections = new Reflections(basePackage);
    // EnumBaseを実装するすべてのクラスを取得
    Set<Class<? extends EnumBase>> enumClasses = reflections.getSubTypesOf(EnumBase.class);

    // MyBatisのTypeHandlerRegistryを取得
    TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    // 取得したEnumクラスごとにTypeHandlerを登録
    for (Class<? extends EnumBase> enumClass : enumClasses) {
      @SuppressWarnings("unchecked")
      Class<EnumBase> enumBaseClass = (Class<EnumBase>) enumClass;
      // EnumTypeHandlerを登録
      typeHandlerRegistry.register(enumBaseClass, EnumTypeHandler.class);
    }
  }
}