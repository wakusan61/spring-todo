package wakusan61.spring.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import wakusan61.spring.todo.common.Status;

@Data
public class TodoDto {
  private Integer id;
  private String  title;
  @Schema(type = "integer", allowableValues = {"0", "1", "2"}, description = "0: 未着手, 1: 進行中, 2: 完了")
  private Status status;
}
