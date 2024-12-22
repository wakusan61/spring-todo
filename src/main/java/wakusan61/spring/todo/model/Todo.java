package wakusan61.spring.todo.model;

import lombok.Data;

@Data
public class Todo {
  private Long    id;
  private String  title;
  private boolean completed;
}
