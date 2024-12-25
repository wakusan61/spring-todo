package wakusan61.spring.todo.dto;

import lombok.Data;
import wakusan61.spring.todo.model.Todo.Status;

@Data
public class TodoWithNoIdDto {
  private String  title;
  private Status status;
}
