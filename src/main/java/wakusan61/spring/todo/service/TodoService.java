package wakusan61.spring.todo.service;

import org.springframework.stereotype.Service;
import wakusan61.spring.todo.converter.TodoConverter;
import wakusan61.spring.todo.dto.TodoDto;
import wakusan61.spring.todo.dto.TodoWithNoIdDto;
import wakusan61.spring.todo.model.Todo;
import wakusan61.spring.todo.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {
  private final TodoRepository todoRepository;
  private final TodoConverter todoConverter;

  public TodoService(
          TodoRepository todoRepository,
          TodoConverter todoConverter
  ) {
    this.todoRepository = todoRepository;
    this.todoConverter = todoConverter;
  }

  public List<TodoDto> getAllTodos() {
    return todoRepository.findAll()
            .stream()
            .map(todoConverter::toDto)
            .toList();
  }

  public TodoDto getTodoById(Long id) {
    return todoConverter.toDto(todoRepository.findById(id));
  }

  public TodoDto createTodo(TodoWithNoIdDto dto) {
    Todo todo = todoConverter.toEntity(dto);
    todoRepository.insert(todo);
    return todoConverter.toDto(todo);
  }

  public void updateTodo(TodoDto dto) {
    todoRepository.update(todoConverter.toEntity(dto));
  }

  public void deleteTodoById(Long id) {
    todoRepository.delete(id);
  }
}
