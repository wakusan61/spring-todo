package wakusan61.spring.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import wakusan61.spring.todo.common.Status;


@Data
public class TodoDto {

  public interface DefaultGroup {}

  public interface CreateGroup {}

  @NotNull(message = "IDは必須です" , groups = {DefaultGroup.class})
  private Integer id;

  @NotNull(message = "タイトルは必須です" , groups = {DefaultGroup.class, CreateGroup.class})
  @Size(min = 1, max = 255, message = "タイトルは1文字以上255文字以下です" ,groups={DefaultGroup.class, CreateGroup.class})
  private String  title;

  @Schema(type = "integer", allowableValues = {"0", "1", "2"}, description = "0: 未着手, 1: 進行中, 2: 完了")
  @NotNull(message = "ステータスは必須です", groups = {DefaultGroup.class, CreateGroup.class})
  private Status status;
}
