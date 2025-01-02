package wakusan61.spring.todo.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  @DisplayName("リクエストボディが不正な場合のエラーハンドリング")
  void handleHttpMessageNotReadableException() {
    HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);
    when(ex.getLocalizedMessage()).thenReturn("Invalid request body");

    ResponseEntity<Map<String, String>> response = handler.handleHttpMessageNotReadableException(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("リクエストボディが不正です", response.getBody().get("error"));
    assertEquals("Invalid request body", response.getBody().get("details"));
  }

  @Test
  @DisplayName("バリデーションエラーをキャッチして400エラーに対応")
  void handleValidationExceptions() {
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.getFieldErrors()).thenReturn(List.of(
            new FieldError("objectName", "field1", "error message 1"),
            new FieldError("objectName", "field2", "error message 2")
    ));

    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    when(ex.getBindingResult()).thenReturn(bindingResult);

    ResponseEntity<Map<String, String>> response = handler.handleValidationExceptions(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("error message 1", response.getBody().get("field1"));
    assertEquals("error message 2", response.getBody().get("field2"));
  }
}