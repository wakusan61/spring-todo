package wakusan61.spring.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import wakusan61.spring.todo.model.Todo;
import wakusan61.spring.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo API", description = "Spring Boot で作成したTodoアプリのAPI")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  @Operation(summary = "全てのTodoを取得します。", description = "Todoの一覧を取得します。")
  public List<Todo> getAllTodos() {
    return todoService.getAllTodos();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Todoを1件取得します。", description = "IDを指定してTodoを1件取得します。")
  public Todo getTodoById(@PathVariable Long id) {
    return todoService.getTodoById(id);
  }

  @PostMapping
  @Operation(summary = "Todoを新規作成します。", description = "新規にTodoを作成します。")
  public Todo createTodo(@RequestBody Todo todo) {
    return todoService.createTodo(todo);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Todoを更新します。", description = "指定したIDのTodoを更新します。")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    todo.setId(id);
    return todoService.updateTodo(todo);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Todoを削除します。", description = "指定したIDのTodoを削除します。")
  public void deleteTodoById(@PathVariable Long id) {
    todoService.deleteTodoById(id);
  }
}
