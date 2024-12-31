package wakusan61.spring.todo.mapper;

import java.util.List;
import wakusan61.spring.todo.model.Todo;

public interface TodoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Todo row);

    Todo selectByPrimaryKey(Integer id);

    List<Todo> selectAll();

    int updateByPrimaryKey(Todo row);
}