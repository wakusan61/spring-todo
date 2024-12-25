package wakusan61.spring.todo.model;

/**
 * Enum のコード値を取得するためのインターフェース
 * 1. MyBatis で DB から取得した値を Enum に変換する際に使用する
 * 2. Dtoをレスポンスで返すときにEnumのコード値を返す際に使用する
 */
public interface CodeEnum {
    int getCode();
}
