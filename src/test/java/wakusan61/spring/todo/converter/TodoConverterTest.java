package wakusan61.spring.todo.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wakusan61.spring.todo.common.Status;
import wakusan61.spring.todo.dto.TodoDto;
import wakusan61.spring.todo.model.Todo;

import static org.junit.jupiter.api.Assertions.*;

class TodoConverterTest {

  private final TodoConverter converter = new TodoConverter();

  @Test
  @DisplayName("TodoDtoからTodoへの変換が正しく行われる")
  void convertDtoToEntity() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setTitle("Test Title");
    dto.setStatus(Status.READY);

    Todo entity = converter.toEntity(dto);

    assertEquals(1, entity.getId());
    assertEquals("Test Title", entity.getTitle());
    assertEquals(Status.READY, entity.getStatus());
  }

  @Test
  @DisplayName("TodoからTodoDtoへの変換が正しく行われる")
  void convertEntityToDto() {
    Todo entity = new Todo();
    entity.setId(1);
    entity.setTitle("Test Title");
    entity.setStatus(Status.READY);

    TodoDto dto = converter.toDto(entity);

    assertEquals(1, dto.getId());
    assertEquals("Test Title", dto.getTitle());
    assertEquals(Status.READY, dto.getStatus());
  }

  @Test
  @DisplayName("nullのTodoDtoからTodoへの変換")
  void convertNullDtoToEntity() {
    assertThrows(NullPointerException.class,() -> converter.toEntity(null));
  }

  @Test
  @DisplayName("nullのTodoからTodoDtoへの変換")
  void convertNullEntityToDto() {
    assertThrows(NullPointerException.class,() -> converter.toDto(null));
  }
}