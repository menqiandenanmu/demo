package com.mall.util.ibatis;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.execution.BatchException;
import com.ibatis.sqlmap.engine.execution.BatchResult;
import com.mall.util.ibatis.base.BaseEntity;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;
import com.mall.util.ibatis.util.MapAndObject;

public abstract class IbatisBaseDaoImpl<E extends BaseEntity<PK>, PK extends Serializable>
		extends SqlMapClientDaoSupport implements BaseDao<E, PK> {

	static Log log = LogFactory.getLog(IbatisBaseDaoImpl.class);
	public static final long DATABASE_OPERATE_TIMES_LIMITS = 500;
	protected Class<BaseEntity<PK>> clazz;
	OperatorAdapter operatorAdapter;
	private String namespace;

	@SuppressWarnings( { "unchecked", "null" })
	public IbatisBaseDaoImpl() {
		Class type = this.getClass();
		while (!(type.getGenericSuperclass() instanceof ParameterizedType)) {
			type = type.getSuperclass();
		}
		this.clazz = (Class<BaseEntity<PK>>) ((ParameterizedType) type
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.namespace = clazz.getSimpleName().toUpperCase();
		if (this.getClass().isAnnotationPresent(Namespace.class)) {
			String s = this.getClass().getAnnotation(Namespace.class).value();
			if (s != null || s.length() > 0)
				this.namespace = s;
		}
	}

	public IbatisBaseDaoImpl(String namespace) {
		this.namespace = namespace;
	}

	public void setOperatorAdapter(OperatorAdapter operatorAdapter) {
		this.operatorAdapter = operatorAdapter;
	}

	/**
	 * 按照规则生成字符串 若参数为selectUser <code>SELECT_USER</code>
	 * 
	 * @param str
	 * @return String
	 */
	public static String toUpperCaseWithUnderscores(String str) {
		StringBuffer result = new StringBuffer();
		if (str != null) {
			boolean lastIsNum = false;
			// result.append(str.charAt(0));
			for (int i = 0; i < str.length(); i++) {
				char aChar = str.charAt(i);
				boolean thisIsNum = (aChar >= '0' && aChar <= '9');
				if ((aChar >= 'A' && aChar <= 'Z') || (lastIsNum != thisIsNum)) {
					result.append('_');
				}
				lastIsNum = thisIsNum;
				result.append(Character.toUpperCase(aChar));
			}
		}
		return result.toString();
	}

	/**
	 * 删除
	 * 
	 * @param clazz
	 *            泛型制定的对象
	 * @param obj
	 *            删除参数
	 * @param idPostfix
	 *            sqlMap中方法对应执行的标签id
	 * @return int >0 为执行成功，此方法返回影响数据库条数
	 */
	public int delete(Class<E> clazz, Object obj, String idPostfix) {
		return delete(toUpperCaseWithUnderscores(clazz.getSimpleName()), obj,
				idPostfix);
	}

	/**
	 * 删除
	 * 
	 * @param nameSpaces
	 *            sqlMap命名空间名称
	 * @param obj
	 *            删除参数
	 * @param idPostfix
	 *            sqlMap中方法对应执行的标签id
	 * @return int >0 为执行成功，此方法返回影响数据库条数
	 */
	public int delete(String nameSpace, Object obj, String idPostfix) {
		return delete(nameSpace, obj, idPostfix, null);
	}

	protected int delete(String nameSpace, Object obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis();
		int i = getSqlMapClientTemplate().delete(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ DELETE
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
		long endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[delete(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Delete object list from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return i;
	}

	public int deleteBatch(Class<E> clazz, List<E> obj, String idPostfix) {
		return deleteBatch(toUpperCaseWithUnderscores(clazz.getSimpleName()),
				obj, idPostfix);
	}

	public int deleteBatch(String nameSpace, List<E> obj, String idPostfix) {
		return deleteBatch(nameSpace, obj, idPostfix, null);
	}

	@SuppressWarnings("unchecked")
	protected int deleteBatch(String nameSpace, List<E> obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis();
		final String sqlMapId = nameSpace + "."
				+ (idPrefix == null ? "" : (idPrefix.toUpperCase() + "_"))
				+ DELETE
				+ (idPostfix == null ? "" : ("_" + idPostfix.toUpperCase()));
		final List<E> objList = (List<E>) obj;
		SqlMapClientCallback callback = new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object objItem : objList) {
					executor.delete(sqlMapId, objItem);
				}
				try {
					return executor.executeBatchDetailed();
				} catch (BatchException e) {
					log.error("", e);
					throw new SQLException("Batch Delete failed!");
				}
			}
		};
		int i = batchResult((List<BatchResult>) this.getSqlMapClientTemplate()
				.execute(callback));
		long endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[deleteBatch(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]DeleteBatch object from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return i;
	}

	public E getObj(Class<E> clazz, Object obj, String idPostfix) {
		return (E) getObj(toUpperCaseWithUnderscores(clazz.getSimpleName()),
				obj, idPostfix);
	}

	public E getObj(String nameSpace, Object obj, String idPostfix) {
		return (E) getObj(nameSpace, obj, idPostfix, null);
	}

	public Object getByObj(String nameSpace, Object obj, String idPostfix) {
		return (Object) getObj(nameSpace, obj, idPostfix, null);
	}

	/**
	 * 查找聚合
	 * 
	 * @author zhaoxs
	 * @time 2010-9-25 下午05:51:49
	 * @param nameSpace
	 * @param idPostfix
	 * @param obj
	 * @return
	 */
	public Object getObjAggregation(String nameSpace, String idPostfix,
			Object obj) {
		return (Object) getObjAggregation(nameSpace, idPostfix, obj, null);
	}

	protected Object getObjAggregation(String nameSpace, String idPostfix,
			Object obj, String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis();
		Object e = getSqlMapClientTemplate().queryForObject(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ (idPostfix == null ? SELECT_BY_PK
								: (SELECT + "_" + idPostfix.toUpperCase())),
				obj);
		long endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[getObj(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Get object from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return e;
	}

	public Object getXObject(String statementName, Object parm) {
		return getSqlMapClientTemplate().queryForObject(statementName, parm);
	}

	@SuppressWarnings("unchecked")
	protected E getObj(String nameSpace, Object obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis();
		E e = (E) getSqlMapClientTemplate().queryForObject(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ (idPostfix == null ? SELECT_BY_PK
								: (SELECT + "_" + idPostfix.toUpperCase())),
				obj);
		long endTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[getObj(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Get object from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return e;
	}

	public List<E> getObjList(Class<E> clazz, Object obj, String idPostfix) {
		return (List<E>) getObjList(toUpperCaseWithUnderscores(clazz
				.getSimpleName()), obj, idPostfix);
	}

	public List<E> getObjList(String nameSpace, Object obj, String idPostfix) {
		return (List<E>) getObjList(nameSpace, obj, idPostfix, null);
	}

	@SuppressWarnings("unchecked")
	protected List<E> getObjList(String nameSpace, Object obj,
			String idPostfix, String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis(); //
		List<E> list = (List<E>) getSqlMapClientTemplate().queryForList(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ SELECT
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
		long endTime = System.currentTimeMillis(); //
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[getObjList(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Get object list from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return list;
	}

	public int getSelectCount(Class<E> clazz, Object obj, String idPostfix) {
		return getSelectCount(
				toUpperCaseWithUnderscores(clazz.getSimpleName()), obj,
				idPostfix);
	}

	public int getSelectCount(String nameSpace, Object obj, String idPostfix) {
		return getSelectCount(nameSpace, obj, idPostfix, null);
	}

	protected int getSelectCount(String nameSpace, Object obj,
			String idPostfix, String idPrefix) {
		checkNameSpace(nameSpace);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ SELECT_COUNT
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
	}

	public Object insert(Class<?> clazz, Object obj, String idPostfix) {
		return insert(toUpperCaseWithUnderscores(clazz.getSimpleName()), obj,
				idPostfix);
	}

	public Object insert(String nameSpace, Object obj, String idPostfix) {
		return insert(nameSpace, obj, idPostfix, null);
	}

	protected Object insert(String nameSpace, Object obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis(); //
		Object o = getSqlMapClientTemplate().insert(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ INSERT
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
		long endTime = System.currentTimeMillis(); //
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[insert(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Insert object into database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return o;
	}

	public int insertBatch(Class<E> clazz, List<E> obj, String idPostfix) {
		return insertBatch(toUpperCaseWithUnderscores(clazz.getSimpleName()),
				obj, idPostfix);
	}

	public int insertBatch(String nameSpace, List<E> obj, String idPostfix) {
		return insertBatch(nameSpace, obj, idPostfix, null);
	}

	@SuppressWarnings("unchecked")
	protected int insertBatch(String nameSpace, List<E> obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis(); //
		final String sqlMapId = nameSpace + "."
				+ (idPrefix == null ? "" : (idPrefix.toUpperCase() + "_"))
				+ INSERT
				+ (idPostfix == null ? "" : ("_" + idPostfix.toUpperCase()));
		final List<E> objList = (List<E>) obj;
		SqlMapClientCallback callback = new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (E e : objList) {
					executor.insert(sqlMapId, e);
				}
				try {
					return executor.executeBatchDetailed();
				} catch (BatchException e) {
					log.error("", e);
					throw new SQLException("Batch Delete failed!");
				}
			}
		};
		int i = batchResult((List<BatchResult>) this.getSqlMapClientTemplate()
				.execute(callback));
		long endTime = System.currentTimeMillis(); //
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[insertBatch(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]InsertBatch object into database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return i;
	}

	protected void checkNameSpace(String nameSpace) {
		if (nameSpace == null) {
			throw new RuntimeException(
					"MUST give me a NameSpace for Ibtis SQL mapping.");
		}
	}

	/**
	 * 
	 * @param batchResultList
	 * @return
	 */
	protected int batchResult(List<BatchResult> batchResultList) {
		BatchResult result = batchResultList.get(0);
		int count = 0;
		for (int i : result.getUpdateCounts()) {
			if (i == java.sql.Statement.SUCCESS_NO_INFO) {
				count++;
			} else {
				count += i;
			}
		}
		return count;
	}

	public int update(Class<E> clazz, Object obj, String idPostfix) {
		return update(toUpperCaseWithUnderscores(clazz.getSimpleName()), obj,
				idPostfix);
	}

	public int update(Object obj, String idPostfix) {
		return update(namespace, obj, idPostfix);
	}

	// 返回影响的行数
	public int update(String nameSpace, Object obj, String idPostfix) {
		return update(nameSpace, obj, idPostfix, null);
	}

	public int update(String nameSpace, Object obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis(); //
		int i = getSqlMapClientTemplate().update(
				nameSpace
						+ "."
						+ (idPrefix == null ? ""
								: (idPrefix.toUpperCase() + "_"))
						+ UPDATE
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
		long endTime = System.currentTimeMillis(); //
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[update(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]Update object from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return i;
	}

	public int updateBatch(Class<E> clazz, List<E> obj, String idPostfix) {
		return updateBatch(toUpperCaseWithUnderscores(clazz.getSimpleName()),
				obj, idPostfix);
	}

	@SuppressWarnings("unchecked")
	public int updateBatch(List<E> obj, String idPostfix) {
		if (obj != null && obj.size() > 0) {
			Object objItem = obj.get(0);
			return updateBatch((Class<E>) objItem.getClass(), obj, idPostfix);
		}
		return 0;
	}

	public int updateBatch(String nameSpace, List<E> obj, String idPostfix) {
		return updateBatch(nameSpace, obj, idPostfix, null);
	}

	@SuppressWarnings("unchecked")
	public int updateBatch(String nameSpace, List<E> obj, String idPostfix,
			String idPrefix) {
		checkNameSpace(nameSpace);
		long startTime = System.currentTimeMillis(); //
		final String sqlMapId = nameSpace + "."
				+ (idPrefix == null ? "" : (idPrefix.toUpperCase() + "_"))
				+ UPDATE
				+ (idPostfix == null ? "" : ("_" + idPostfix.toUpperCase()));
		final List<E> objList = (List<E>) obj;
		SqlMapClientCallback callback = new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object objItem : objList) {
					executor.update(sqlMapId, objItem);
				}
				try {
					return executor.executeBatchDetailed();
				} catch (BatchException e) {
					log.error("", e);
					throw new SQLException("Batch update failed!");
				}
			}
		};
		int i = batchResult((List<BatchResult>) this.getSqlMapClientTemplate()
				.execute(callback));
		long endTime = System.currentTimeMillis(); //
		if (log.isDebugEnabled()) {
			long l = endTime - startTime;
			if (l > DATABASE_OPERATE_TIMES_LIMITS) {
				log.debug("[updateBatch(nameSpace=" + nameSpace + ",obj=" + obj
						+ ",idPostfix=" + idPostfix + ",idPrefix=" + idPrefix
						+ ")]UpdateBatch object from database in "
						+ (endTime - startTime) + "ms");
			}
		}
		return i;
	}

	public Number getObjectCount(String nameSpace, Object obj, String idPostfix) {
		String countStatementName = nameSpace + ".SELECT_COUNT_" + idPostfix;
		return (Number) this.getSqlMapClientTemplate().queryForObject(
				countStatementName, obj);
	}

	@SuppressWarnings("unchecked")
	public List<E> getList(String nameSpace, Object obj, String idPostfix) {
		checkNameSpace(nameSpace);
		return (List<E>) getSqlMapClientTemplate().queryForList(
				nameSpace
						+ "."
						+ SELECT
						+ (idPostfix == null ? "" : ("_" + idPostfix
								.toUpperCase())), obj);
	}

	@SuppressWarnings("unchecked")
	public Page<E> pageQuery(String nameSpace, PageRequest pageRequest,
			String idPostfix) {
		String countStatementName = nameSpace + ".SELECT_COUNT_" + idPostfix;
		Number totalCount = (Number) this.getSqlMapClientTemplate()
				.queryForObject(countStatementName, pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}

		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map<String, String> otherFilters = new HashMap<String, String>();
		otherFilters.put("offset", String.valueOf(page.getFirstResult()));
		otherFilters.put("pageSize", String.valueOf(page.getPageSize()));
		otherFilters.put("lastRows", String.valueOf(page.getFirstResult()
				+ page.getPageSize()));
		otherFilters.put("sortColumns", String.valueOf(pageRequest
				.getSortColumns()));

		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		Map parameterObject = new MapAndObject(otherFilters, pageRequest
				.getFilters());
		String statementName = nameSpace + ".SELECT_" + idPostfix;
		List list = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject, page.getFirstResult(), page.getPageSize());
		page.setResult(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page pageQuery(String nameSpace, String countStatementName,
			String statementName, PageRequest pageRequest) {
		countStatementName = nameSpace + "." + countStatementName;
		Number totalCount = (Number) this.getSqlMapClientTemplate()
				.queryForObject(countStatementName, pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}

		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map<String, String> otherFilters = new HashMap<String, String>();
		otherFilters.put("offset", String.valueOf(page.getFirstResult()));
		otherFilters.put("pageSize", String.valueOf(page.getPageSize()));
		otherFilters.put("lastRows", String.valueOf(page.getFirstResult()
				+ page.getPageSize()));
		otherFilters.put("sortColumns", String.valueOf(pageRequest
				.getSortColumns()));

		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		Map parameterObject = new MapAndObject(otherFilters, pageRequest
				.getFilters());
		statementName = nameSpace + "." + statementName;
		List list = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject, page.getFirstResult(), page.getPageSize());
		page.setResult(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	public Page<?> pageQueryObj(String nameSpace, PageRequest pageRequest,
			String idPostfix) {
		String countStatementName = nameSpace + ".SELECT_COUNT_" + idPostfix;
		Number totalCount = (Number) this.getSqlMapClientTemplate()
				.queryForObject(countStatementName, pageRequest.getFilters());
		if (totalCount == null || totalCount.intValue() <= 0) {
			return new Page(pageRequest, 0);
		}

		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map<String, String> otherFilters = new HashMap<String, String>();
		otherFilters.put("offset", String.valueOf(page.getFirstResult()));
		otherFilters.put("pageSize", String.valueOf(page.getPageSize()));
		otherFilters.put("lastRows", String.valueOf(page.getFirstResult()
				+ page.getPageSize()));
		otherFilters.put("sortColumns", String.valueOf(pageRequest
				.getSortColumns()));

		// 混合两个filters为一个filters,MapAndObject.get()方法将在两个对象取值,Map如果取值为null,则再在Bean中取值
		Map parameterObject = new MapAndObject(otherFilters, pageRequest
				.getFilters());
		String statementName = nameSpace + ".SELECT_" + idPostfix;
		List list = getSqlMapClientTemplate().queryForList(statementName,
				parameterObject, page.getFirstResult(), page.getPageSize());
		page.setResult(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PK getNewId() {
		return (PK) getSqlMapClientTemplate().queryForObject(
				namespace + "." + BaseDao.SELECT_SEQ);
	}

	@Override
	public Long getSeq(String name) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				namespace + "." + BaseDao.SELECT_SEQ + "_" + name);
	}

	@Override
	public Long getSeqWithPath(String path) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				namespace + "." + path);
	}

	@Override
	public int delete(E obj, boolean flag) {
		if (flag)
			return getSqlMapClientTemplate().delete(
					namespace + "." + BaseDao.DELETE_BY_PK, obj);
		else {
			obj.setDeleted(true);
			obj.setNow(new Timestamp(System.currentTimeMillis()));
			return getSqlMapClientTemplate().update(
					namespace + "." + BaseDao.UPDATE_BY_PK, obj);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getEntity(String name, Object obj) {
		return (E) getObject(name, obj);
	}

	@Override
	public Object getObject(String name, Object obj) {
		return getSqlMapClientTemplate().queryForObject(
				namespace + ".SELECT_BY_" + name, obj);
	}

	@Override
	public Object getObjectWithPath(String path, Object obj) {
		return getSqlMapClientTemplate().queryForObject(path, obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> queryEntitys(String name, Object obj) {
		return (List<E>) queryObjects(name, obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryObjects(String name, Object obj) {
		return queryObjectsWithPath(namespace + ".SELECT_BY_" + name, obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryObjectsWithPath(String path, Object obj) {
		return getSqlMapClientTemplate().queryForList(path, obj);
	}

	@Override
	public int delete(E obj) {
		return this.delete(obj, false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteWithPath(String path, BaseEntity obj, boolean flag) {
		if (flag)
			return getSqlMapClientTemplate().delete(path, obj.getId());
		else {
			obj.setDeleted(true);
			return getSqlMapClientTemplate().update(path, obj);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int deleteWithPath(String path, BaseEntity entity) {
		entity.setDeleted(true);
		return getSqlMapClientTemplate().update(path, entity);
	}

	@Override
	@SuppressWarnings( { "unchecked", "null" })
	public E getById(PK id) {
		if (this.getClass().isAnnotationPresent(Namespace.class)) {
			String s = this.getClass().getAnnotation(Namespace.class).value();
			if (s != null || s.length() > 0)
				this.namespace = s;
		}
		try {
			E obj = (E) clazz.newInstance();
			obj.setId(id);
			return (E) getSqlMapClientTemplate().queryForObject(
					namespace + "." + BaseDao.SELECT_BY_PK, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Page<E> pageQuery(PageRequest<?> pageRequest, String idPostfix) {
		return pageQuery(namespace, pageRequest, idPostfix);
	}

	@Override
	public Page<?> pageQueryObj(PageRequest<?> pageRequest, String idPostfix) {
		return pageQueryObj(namespace, pageRequest, idPostfix);
	}

	@Override
	public void update(E obj) {
		obj.setNow(new Timestamp(System.currentTimeMillis()));
		int result = getSqlMapClientTemplate().update(
				namespace + "." + BaseDao.UPDATE_BY_PK, obj);
		if (result != 1) {
			throw new RuntimeException("update error,path:" + namespace + "."
					+ BaseDao.UPDATE_BY_PK + ",id=" + obj.getId() + ".");
		} else {
			obj.setModifiedTime(obj.getNow());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateWithPath(String path, BaseEntity obj) {
		obj.setNow(new Timestamp(System.currentTimeMillis()));
		int result = getSqlMapClientTemplate().update(path, obj);
		if (result != 1) {
			throw new RuntimeException("update error,path:" + path + ",id:"
					+ obj.getId() + ".");
		} else {
			obj.setModifiedTime(obj.getNow());
		}

	}

	@Override
	public void insert(E obj) {
		getSqlMapClientTemplate().insert(namespace + "." + BaseDao.INSERT, obj);
	}
}
