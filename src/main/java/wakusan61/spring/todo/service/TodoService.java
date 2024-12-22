package wakusan61.spring.todo.service;

import org.springframework.stereotype.Service;
import wakusan61.spring.todo.model.Todo;
import wakusan61.spring.todo.repository.TodoMapper;

import java.util.List;

@Service
public class TodoService {
  private final TodoMapper todoMapper;

  public TodoService(TodoMapper todoMapper) {
    this.todoMapper = todoMapper;
  }

  public List<Todo> getAllTodos() {
    return todoMapper.findAll();
  }

  public Todo getTodoById(Long id) {
    return todoMapper.findById(id);
  }

  public Todo createTodo(Todo todo) {
    todoMapper.insert(todo);
    return todo;
  }

  public Todo updateTodo(Todo todo) {
    todoMapper.update(todo);
    return todo;
  }

  public void deleteTodoById(Long id) {
    todoMapper.delete(id);
  }
}
