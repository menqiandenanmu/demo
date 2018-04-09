package com.mall.util.ibatis.pk.longs;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class PkLongTypeHandlerCallback implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		// TODO Auto-generated method stub
		if (getter == null)
			return null;
		else
			return getter.getLong();
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		if (parameter != null)
			setter.setLong((Long) parameter);
		else
			setter.setObject(null);

	}

	@Override
	public Object valueOf(String s) {
		return Long.parseLong(s);
	}

}
