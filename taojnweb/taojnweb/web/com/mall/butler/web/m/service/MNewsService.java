package com.mall.butler.web.m.service;

import java.util.List;

import com.mall.butler.service.BaseService;
import com.mall.butler.web.m.model.NewsExtendInfo;
import com.mall.butler.web.model.NewsClass;
import com.mall.butler.web.model.NewsInfo;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

/**
 * 新闻资讯管理
 * 
 * @author zhaoxs
 */
public interface MNewsService extends BaseService {
	/**
	 * 新增分类
	 * 
	 * @param parentClass
	 *            父类
	 * @param newsClass
	 *            新类
	 */
	public void doSaveClass(NewsClass parentClass, NewsClass newsClass);

	/**
	 * 更新只能更新说明信息
	 * 
	 * @param newsClass
	 */
	public void doUpdateClass(NewsClass newsClass);

	/**
	 * 添加新资讯内容
	 * 
	 * @param newsClass
	 *            分类
	 * @param news
	 * @param titleImg
	 */
	public void doSaveNews(NewsClass newsClass, NewsInfo news, String content, String titleImg);

	/**
	 * 更新资讯内容
	 * 
	 * @param news
	 * @param titleImg
	 */
	public void doUpdateNews(NewsInfo news, String content, String titleImg);

	/**
	 * 查找资讯内容
	 * 
	 * @param filter
	 * @return
	 */
	public Page<NewsExtendInfo> queryNews(PageRequest<NewsInfo> filter);

	/**
	 * 新闻置顶
	 * 
	 * @param news
	 */
	void doTopNews(NewsInfo news);

	/**
	 * 取消置顶
	 * 
	 * @param news
	 */
	void doUnTopNew(NewsInfo news);

	// //////////////////////////////

	/**
	 * 查找分类
	 * 
	 * @return
	 */
	public List<NewsClass> findNewsList();

	/**
	 * 查找资讯分类
	 * 
	 * @param request
	 * @return
	 */
	public Page<NewsClass> queryPage(PageRequest<NewsClass> request);

}
