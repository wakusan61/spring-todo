package wakusan61.spring.todo.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wakusan61.spring.todo.common.Status;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoDtoTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  @Test
  @DisplayName("IDがnullの場合のバリデーションエラー")
  void validationErrorWhenIdIsNull() {
    TodoDto dto = new TodoDto();
    dto.setTitle("Test Title");
    dto.setStatus(Status.READY);

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertFalse(violations.isEmpty());
  }

  @Test
  @DisplayName("タイトルがnullの場合のバリデーションエラー")
  void validationErrorWhenTitleIsNull() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setStatus(Status.READY);

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertFalse(violations.isEmpty());
  }

  @Test
  @DisplayName("ステータスがnullの場合のバリデーションエラー")
  void validationErrorWhenStatusIsNull() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setTitle("Test Title");

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertFalse(violations.isEmpty());
  }

  @Test
  @DisplayName("タイトルが1文字未満の場合のバリデーションエラー")
  void validationErrorWhenTitleIsTooShort() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setTitle("");
    dto.setStatus(Status.READY);

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertFalse(violations.isEmpty());
  }

  @Test
  @DisplayName("タイトルが255文字を超える場合のバリデーションエラー")
  void validationErrorWhenTitleIsTooLong() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setTitle("a".repeat(256));
    dto.setStatus(Status.READY);

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertFalse(violations.isEmpty());
  }

  @Test
  @DisplayName("正常なTodoDtoのバリデーション")
  void validTodoDto() {
    TodoDto dto = new TodoDto();
    dto.setId(1);
    dto.setTitle("Test Title");
    dto.setStatus(Status.READY);

    Set<ConstraintViolation<TodoDto>> violations = validator.validate(dto, TodoDto.DefaultGroup.class);
    assertEquals(0, violations.size());
  }
}