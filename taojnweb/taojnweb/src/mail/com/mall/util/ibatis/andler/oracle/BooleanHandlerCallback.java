package com.mall.util.ibatis.andler.oracle;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class BooleanHandlerCallback implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		// TODO Auto-generated method stub
		if (getter == null)
			return null;
		else if (getter.getLong() > 0)
			return true;
		else
			return false;
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {

		if (parameter != null && (Boolean) parameter)
			setter.setLong(1L);
		else
			setter.setLong(0L);

	}

	@Override
	public Object valueOf(String s) {
		return Long.parseLong(s);
	}

}
