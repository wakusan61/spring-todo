package wakusan61.spring.todo.service;

import org.springframework.stereotype.Service;
import wakusan61.spring.todo.converter.TodoConverter;
import wakusan61.spring.todo.dto.TodoDto;
import wakusan61.spring.todo.dto.TodoWithNoIdDto;
import wakusan61.spring.todo.mapper.TodoMapper;
import wakusan61.spring.todo.model.Todo;

import java.util.List;

@Service
public class TodoService {
  private final TodoMapper todoMapper;
  private final TodoConverter todoConverter;

  public TodoService(
          TodoMapper todoMapper,
          TodoConverter todoConverter
  ) {
    this.todoMapper = todoMapper;
    this.todoConverter = todoConverter;
  }

  public List<TodoDto> getAllTodos() {
    return todoMapper.selectAll()
            .stream()
            .map(todoConverter::toDto)
            .toList();
  }

  public TodoDto getTodoById(Integer id) {
    return todoConverter.toDto(todoMapper.selectByPrimaryKey(id));
  }

  public TodoDto createTodo(TodoWithNoIdDto dto) {
    Todo todo = todoConverter.toEntity(dto);
    todoMapper.insert(todo);
    return todoConverter.toDto(todo);
  }

  public void updateTodo(TodoDto dto) {
    todoMapper.updateByPrimaryKey(todoConverter.toEntity(dto));
  }

  public void deleteTodoById(Integer id) {
    todoMapper.deleteByPrimaryKey(id);
  }
}
