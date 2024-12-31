package wakusan61.spring.todo.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ActiveProfiles;
import wakusan61.spring.todo.common.Status;
import wakusan61.spring.todo.config.MyBatisConfig;
import wakusan61.spring.todo.model.Todo;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(MyBatisConfig.class)
class TodoMapperTest {

  @Autowired
  private TodoMapper todoMapper;

  @Test
  @DisplayName("Todoを正しく挿入する")
  void insertTodoCorrectly() {
    Todo todo = new Todo();
    todo.setTitle("Test Todo");
    todo.setStatus(Status.READY);

    int result = todoMapper.insert(todo);
    assertEquals(1, result);
  }

  @Test
  @DisplayName("存在するIDでTodoを正しく取得する")
  void selectTodoByExistingId() {
    Todo todo = new Todo();
    todo.setTitle("Test Todo");
    todo.setStatus(Status.READY);
    todoMapper.insert(todo);

    Todo result = todoMapper.selectByPrimaryKey(todo.getId());
    assertEquals(todo.getTitle(), result.getTitle());
    assertEquals(todo.getStatus(), result.getStatus());
  }

  @Test
  @DisplayName("存在しないIDでTodoを取得する際にnullを返す")
  void selectTodoByNonExistingId() {
    Todo result = todoMapper.selectByPrimaryKey(999);
    assertNull(result);
  }

  @Test
  @DisplayName("全てのTodoを正しく取得する")
  void selectAllTodosCorrectly() {
    Todo todo1 = new Todo();
    todo1.setTitle("Test Todo 1");
    todo1.setStatus(Status.READY);
    todoMapper.insert(todo1);

    Todo todo2 = new Todo();
    todo2.setTitle("Test Todo 2");
    todo2.setStatus(Status.IN_PROGRESS);
    todoMapper.insert(todo2);

    List<Todo> todos = todoMapper.selectAll();
    assertEquals(2, todos.size());

    List<Todo> expected = List.of(todo1, todo2);

    IntStream.range(0, todos.size()).forEach(i -> {
      assertEquals(expected.get(i).getTitle(), todos.get(i).getTitle());
      assertEquals(expected.get(i).getStatus(), todos.get(i).getStatus());
    });
  }

  @Test
  @DisplayName("Todoを正しく更新する")
  void updateTodoCorrectly() {
    Todo todo = new Todo();
    todo.setTitle("Test Todo");
    todo.setStatus(Status.READY);
    todoMapper.insert(todo);

    todo.setTitle("Updated Todo");
    todo.setStatus(Status.DONE);
    int result = todoMapper.updateByPrimaryKey(todo);
    assertEquals(1, result);

    Todo updatedTodo = todoMapper.selectByPrimaryKey(todo.getId());
    assertEquals(todo.getTitle(), updatedTodo.getTitle());
    assertEquals(todo.getStatus(), updatedTodo.getStatus());
  }

  @Test
  @DisplayName("存在するIDでTodoを正しく削除する")
  void deleteTodoByExistingId() {
    Todo todo = new Todo();
    todo.setTitle("Test Todo");
    todo.setStatus(Status.READY);
    todoMapper.insert(todo);

    int result = todoMapper.deleteByPrimaryKey(todo.getId());
    assertEquals(1, result);

    Todo deletedTodo = todoMapper.selectByPrimaryKey(todo.getId());
    assertNull(deletedTodo);
  }

  @Test
  @DisplayName("存在しないIDでTodoを削除する際に0を返す")
  void deleteTodoByNonExistingId() {
    int result = todoMapper.deleteByPrimaryKey(999);
    assertEquals(0, result);
  }
}