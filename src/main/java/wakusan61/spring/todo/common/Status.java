package wakusan61.spring.todo.common;

public enum Status implements EnumBase {
  READY(0,"準備完了"), IN_PROGRESS(1,"着手中"), DONE(2,"完了");
  private int code;
  private String name;

  Status(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
