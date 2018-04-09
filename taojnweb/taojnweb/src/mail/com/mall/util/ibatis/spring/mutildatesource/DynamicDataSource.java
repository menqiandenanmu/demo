/**
 * @author caedmon
 */
package com.mall.util.ibatis.spring.mutildatesource;

import java.sql.SQLFeatureNotSupportedException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@SuppressWarnings("all")
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static Logger log = Logger.getLogger("DynamicDataSource");

	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return DbContextHolder.getDbType();
	}

}
