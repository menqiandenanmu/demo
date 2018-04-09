package com.mall.util.ibatis.typehander;

import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.mall.util.common.enums.AbstractEnumSupport;

/**
 * ibatis数据库枚举返射
 * 
 * @author caedmon
 * @date 2010-10-11 上午11:25:21
 */
public abstract class HasValueTypeHandler implements TypeHandlerCallback {

	public abstract AbstractEnumSupport[] getEnums();

	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (parameter == null) {
			setter.setNull(Types.INTEGER);
		} else {
			AbstractEnumSupport status = (AbstractEnumSupport) parameter;
			setter.setInt(status.getValue());
		}
	}

	public Object getResult(ResultGetter getter) throws SQLException {
		int value = getter.getInt();
		if (getter.wasNull())
			return null;

		for (AbstractEnumSupport status : getEnums()) {
			if (Integer.valueOf(status.getValue()).equals(value)) {
				return status;
			}
		}
		throw new UnsupportedOperationException("No such status");
	}

	public Object valueOf(String s) {
		return s;
	}

}
