package wakusan61.spring.todo.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wakusan61.spring.todo.common.EnumBase;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EnumTypeHandlerTest {

  private enum TestEnum implements EnumBase {
    VALUE1(1), VALUE2(2);

    private final int code;

    TestEnum(int code) {
      this.code = code;
    }

    @Override
    public int getCode() {
      return code;
    }
  }

  private final EnumTypeHandler<TestEnum> handler = new EnumTypeHandler<>(TestEnum.class);

  @Test
  @DisplayName("正常なパラメータ設定")
  void setNonNullParameter() throws SQLException {
    PreparedStatement ps = mock(PreparedStatement.class);
    handler.setNonNullParameter(ps, 1, TestEnum.VALUE1, null);
    Mockito.verify(ps).setInt(1, 1);
  }

  @Test
  @DisplayName("カラム名での正常な結果取得")
  void getNullableResultByColumnName() throws SQLException {
    ResultSet rs = mock(ResultSet.class);
    when(rs.getInt("column")).thenReturn(1);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(TestEnum.VALUE1, handler.getNullableResult(rs, "column"));
  }

  @Test
  @DisplayName("カラムインデックスでの正常な結果取得")
  void getNullableResultByColumnIndex() throws SQLException {
    ResultSet rs = mock(ResultSet.class);
    when(rs.getInt(1)).thenReturn(2);
    when(rs.wasNull()).thenReturn(false);
    assertEquals(TestEnum.VALUE2, handler.getNullableResult(rs, 1));
  }

  @Test
  @DisplayName("CallableStatementでの正常な結果取得")
  void getNullableResultByCallableStatement() throws SQLException {
    CallableStatement cs = mock(CallableStatement.class);
    when(cs.getInt(1)).thenReturn(1);
    when(cs.wasNull()).thenReturn(false);
    assertEquals(TestEnum.VALUE1, handler.getNullableResult(cs, 1));
  }

  @Test
  @DisplayName("不明なコードでの例外スロー")
  void unknownEnumCodeThrowsException() throws SQLException {
    ResultSet rs = mock(ResultSet.class);
    when(rs.getInt(1)).thenReturn(2);
    when(rs.wasNull()).thenReturn(false);
    assertThrows(IllegalArgumentException.class, () -> handler.getNullableResult(rs, 999));
  }

  @Test
  @DisplayName("null結果の処理")
  void handleNullResult() throws SQLException {
    ResultSet rs = mock(ResultSet.class);
    when(rs.getInt("column")).thenReturn(0);
    when(rs.wasNull()).thenReturn(true);
    assertNull(handler.getNullableResult(rs, "column"));
  }
}