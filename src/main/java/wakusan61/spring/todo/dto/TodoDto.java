package wakusan61.spring.todo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import wakusan61.spring.todo.model.Todo.Status;
import wakusan61.spring.todo.serializer.GenericEnumIntSerializer;

@Data
public class TodoDto {
  private Integer id;
  private String  title;

  @JsonSerialize(using = GenericEnumIntSerializer.class)
  @Schema(description = "ステータス", type = "integer", format = "int32", allowableValues = {"0", "1", "2"})
  private Status status;
}
