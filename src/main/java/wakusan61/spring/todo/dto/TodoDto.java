package wakusan61.spring.todo.dto;

import lombok.Data;

@Data
public class TodoDto {
  private Long    id;
  private String  title;
  private boolean completed;
}
