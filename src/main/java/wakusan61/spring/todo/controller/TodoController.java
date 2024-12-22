package wakusan61.spring.todo.controller;

import org.springframework.web.bind.annotation.*;
import wakusan61.spring.todo.model.Todo;
import wakusan61.spring.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping
  public List<Todo> getAllTodos() {
    return todoService.getAllTodos();
  }

  @GetMapping("/{id}")
  public Todo getTodoById(@PathVariable Long id) {
    return todoService.getTodoById(id);
  }

  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    return todoService.createTodo(todo);
  }

  @PutMapping("/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    todo.setId(id);
    return todoService.updateTodo(todo);
  }

  @DeleteMapping("/{id}")
  public void deleteTodoById(@PathVariable Long id) {
    todoService.deleteTodoById(id);
  }
}
