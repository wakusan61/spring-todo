package wakusan61.spring.todo.converter;

import org.springframework.stereotype.Component;
import wakusan61.spring.todo.dto.TodoDto;
import wakusan61.spring.todo.dto.TodoWithNoIdDto;
import wakusan61.spring.todo.model.Todo;

@Component
public class TodoConverter {
  // TodoDto から Todo へ変換
  public Todo toEntity(TodoDto dto) {
    Todo entity = new Todo();
    entity.setId(dto.getId());
    entity.setTitle(dto.getTitle());
    entity.setStatus(dto.getStatus());
    return entity;
  }

  public Todo toEntity(TodoWithNoIdDto dto) {
    Todo entity = new Todo();
    entity.setTitle(dto.getTitle());
    entity.setStatus(dto.getStatus());
    return entity;
  }


  // Todo から TodoDto へ変換
  public TodoDto toDto(Todo entity) {
    TodoDto dto = new TodoDto();
    dto.setId(entity.getId());
    dto.setTitle(entity.getTitle());
    dto.setStatus(entity.getStatus());
    return dto;
  }
}
