package com.mall.util.ibatis.pk.ints;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class PkIntegerTypeHandlerCallback implements TypeHandlerCallback {

	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		// TODO Auto-generated method stub
		if (getter == null)
			return null;
		else
			return getter.getInt();
	}

	@Override
	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		setter.setInt((Integer) parameter);
	}

	@Override
	public Object valueOf(String s) {
		return Integer.parseInt(s);
	}

}
