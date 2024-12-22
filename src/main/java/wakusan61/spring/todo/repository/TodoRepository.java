package wakusan61.spring.todo.repository;

import org.apache.ibatis.annotations.Mapper;
import wakusan61.spring.todo.model.Todo;

import java.util.List;

@Mapper
public interface TodoRepository {
  List<Todo> findAll();
  Todo findById(Long id);
  void insert(Todo todo);
  void update(Todo todo);
  void delete(Long id);
}
