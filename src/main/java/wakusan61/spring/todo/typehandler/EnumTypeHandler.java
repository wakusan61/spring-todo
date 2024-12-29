package wakusan61.spring.todo.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import wakusan61.spring.todo.common.EnumBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumTypeHandler<E extends Enum<E> & EnumBase> extends BaseTypeHandler<E> {
  private final Class<E> type;

  public EnumTypeHandler(Class<E> type) {
    if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    return rs.wasNull() ? null : toEnum(code);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return rs.wasNull() ? null : toEnum(code);
  }

  @Override
  public E getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return cs.wasNull() ? null : toEnum(code);
  }

  private E toEnum(int code) {
    for (E e : type.getEnumConstants()) {
      if (e.getCode() == code) {
        return e;
      }
    }
    throw new IllegalArgumentException("Unknown enum code: " + code);
  }
}