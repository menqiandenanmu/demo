package com.mall.util.ibatis;

import java.io.Serializable;
import java.util.List;

import com.mall.util.ibatis.base.BaseEntity;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public interface BaseDao<E, PK extends Serializable> {

	public static final String DEFAULT_BIZ = "__defaultBiz";
	public static final String DELETE = "DELETE";
	public static final int DELETE_OP = 4;
	public static final int INSERT_OP = 1;
	public static final String SELECT = "SELECT";

	public static final String INSERT = "INSERT";
	public static final String DELETE_BY_PK = "DELETE_BY_PK";
	public static final String SELECT_BY_PK = "SELECT_BY_PK";
	public static final String UPDATE_BY_PK = "UPDATE_BY_PK";
	public static final String SELECT_SEQ = "SELECT_SEQ";

	public static final String SELECT_COUNT = "SELECT_COUNT";
	public static final String UPDATE = "UPDATE";
	public static final int UPDATE_OP = 2;

	public int delete(String nameSpace, Object obj, String idPostfix);

	public int deleteBatch(String nameSpace, List<E> obj, String idPostfix);

	public E getObj(String namaSpace, Object obj, String idPostfix);

	public Object getByObj(String nameSpace, Object obj, String idPostfix);

	public List<E> getObjList(String nameSpace, Object obj, String idPostfix);

	public int getSelectCount(String nameSpace, Object ojb, String idPostfix);

	public Object insert(String nameSpace, Object obj, String idPostfix);

	public int insertBatch(String nameSpace, List<E> obj, String idPostfix);

	public int update(String nameSpace, Object obj, String idPostfix);

	public int updateBatch(String nameSpace, List<E> obj, String idPostfix);

	public List<E> getList(String nameSpace, Object obj, String idPostfix);

	/**
	 * 得到实体
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:40:28
	 * @param name
	 * @return
	 */
	public E getEntity(String name, Object obj);

	/**
	 * 得到对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:43:21
	 * @param name
	 * @param obj
	 * @return
	 */
	public Object getObject(String name, Object obj);

	/**
	 * 全路径得到对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:44:17
	 * @param path
	 * @param obj
	 * @return
	 */
	public Object getObjectWithPath(String path, Object obj);

	/**
	 * 查找实体
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:42:41
	 * @param name
	 * @param obj
	 * @return
	 */
	public List<E> queryEntitys(String name, Object obj);

	/**
	 * 查找对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:43:00
	 * @param name
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryObjects(String name, Object obj);

	/**
	 * 全路径得到对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:45:03
	 * @param path
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryObjectsWithPath(String path, Object obj);

	/**
	 * 删除对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:45:43
	 * @param obj
	 * @param flag
	 *            ture 真删除
	 * @return
	 */
	public int delete(E obj, boolean flag);

	/**
	 * 伪删除对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:46:13
	 * @param obj
	 * @return
	 */
	public int delete(E obj);

	/**
	 * 使用全路径删除对象
	 * 
	 * @author caedmon
	 * @date 2010-7-28 下午01:46:13
	 * @param path
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteWithPath(String path, BaseEntity obj);

	/**
	 * 使用全路径删除对象
	 * 
	 * @author caedmon
	 * @date 2010-7-28 下午01:46:13
	 * @param path
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int deleteWithPath(String path, BaseEntity obj, boolean flag);

	/**
	 * 更新对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:46:44
	 * @param obj
	 * @return
	 */
	public void update(E obj);

	/**
	 * 使用全路径更新对象
	 * 
	 * @author caedmon
	 * @date 2010-7-28 下午01:47:17
	 * @param path
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void updateWithPath(String path, BaseEntity obj);

	/**
	 * 查找对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:46:57
	 * @param id
	 * @return
	 */
	public E getById(PK id);

	/**
	 * 新增对象
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:47:22
	 * @param obj
	 */
	public void insert(E obj);

	/**
	 * 得到对象序列
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:47:41
	 * @return
	 */
	public PK getNewId();

	/**
	 * 得到序列
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:47:55
	 * @param name
	 * @return
	 */
	public Long getSeq(String name);

	/**
	 * 使用路径得到序列
	 * 
	 * @author caedmon
	 * @date 2010-7-26 下午04:48:25
	 * @param path
	 * @return
	 */
	public Long getSeqWithPath(String path);

	/**
	 * 得到分页内容
	 * 
	 * @author caedmon
	 * @date 2010-8-7 下午04:59:12
	 * @param pageRequest
	 * @param idPostfix
	 * @return
	 */
	public Page<E> pageQuery(PageRequest<?> pageRequest, String idPostfix);

	/**
	 * 得到分页内容
	 * 
	 * @author caedmon
	 * @date 2010-8-7 下午04:59:12
	 * @param pageRequest
	 * @param idPostfix
	 * @return
	 */
	public Page<?> pageQueryObj(PageRequest<?> pageRequest, String idPostfix);

}
