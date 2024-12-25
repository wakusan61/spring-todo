package wakusan61.spring.todo.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import wakusan61.spring.todo.model.CodeEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * すべてのEnum型に対応する汎用TypeHandler
 */
public class GenericEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {

    private final Class<E> enumType;

    public GenericEnumTypeHandler(Class<E> enumType) {
        if (enumType == null) {
            throw new IllegalArgumentException("Enum type cannot be null");
        }
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return fromCode(code);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return fromCode(code);
    }

    @Override
    public E getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return fromCode(code);
    }

    private E fromCode(int code) {
        for (E constant : enumType.getEnumConstants()) {
            if (constant.getCode() == code) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code + " for Enum type: " + enumType.getName());
    }
}