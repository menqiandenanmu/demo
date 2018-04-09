package com.mall.util.ibatis.jdbc.dialect;

public class SQLServer2008Dialect extends Dialect {

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		String strSql, strOrderBy;
		strSql = sql.toLowerCase();
		int intPos = strSql.indexOf("order by");
		if (intPos>0) {
			strSql = sql.substring(0, intPos);
			strOrderBy = sql.substring(intPos);
		}
		else {
			strSql = sql;
			strOrderBy = "";
		}
			
		StringBuffer sbSql = new StringBuffer(sql.length() + 100);
		sbSql.append("select top " + limit + " page_main.* from (");
		sbSql.append("select row_number() over (" + strOrderBy + ") as rownumber,page_sub.* from (" + strSql + ") as page_sub");
		sbSql.append(") as page_main where rownumber>" + offset);
		
		return sbSql.toString();
	}

/*
	@Override
	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		StringBuffer sbSql = new StringBuffer(sql.length() + 100);
		sbSql.append("select top " + limit + " page_main.* from (" + sql + ") as page_main ");
		if (offset > 0)
			sbSql.append(" where id not in (select top " + offset + " page_sub.id from (" + sql + ") as page_sub)");
		sbSql.append(" order by page_main.id");
		return sbSql.toString();
	}
*/
/*
模式一
SELECT TOP 页大小 *
FROM TestTable
WHERE (ID NOT IN
(SELECT TOP 页大小*页数 id
FROM 表
ORDER BY id))
ORDER BY ID

模式二
SELECT TOP 页大小 *
FROM TestTable
WHERE (ID NOT IN
(SELECT TOP 页大小*页数 id FROM 表 ORDER BY id))
ORDER BY ID

SELECT TOP 页大小 *
FROM TestTable WHERE not exists
(select * from (select top (页大小*页数) * from TestTable order by id) b where b.id=a.id )
order by id

模式三（目前使用）
SELECT TOP 页大小 * 
FROM 
(SELECT ROW_NUMBER() OVER (ORDER BY id) AS RowNumber,* FROM table1) A
WHERE RowNumber > 页大小*(页数-1)
*/
}
