package com.mall.butler.web.m.service._impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.m.service.MLibraryService;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.mall.butler.web.dao.NewsClassDao;
import com.mall.butler.web.dao.NewsInfoDao;
import com.mall.butler.web.m.model.NewsExtendInfo;
import com.mall.butler.web.m.service.MNewsService;
import com.mall.butler.web.model.NewsClass;
import com.mall.butler.web.model.NewsInfo;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class MNewServiceImpl extends BaseServiceImpl implements MNewsService {
	@Autowired
	private NewsClassDao newsClassDao;
	@Autowired
	private NewsInfoDao newsInfoDao;
	@Autowired
	private MLibraryService mLibraryService;

	@Override
	public void doSaveClass(NewsClass parentClass, NewsClass newsClass) {
		// 编号重复判断
		NewsClass filter = new NewsClass();
		filter.setCode(newsClass.getCode());
		if (newsClassDao.find(filter).size() > 0)
			throw new RuntimeException("分类编号已经被使用!" + newsClass.getName());

		// 名称重复判断
		filter = new NewsClass();
		filter.setName(newsClass.getName());
		if (newsClassDao.find(filter).size() > 0)
			throw new RuntimeException("分类名已经被使用!" + newsClass.getName());

		if (parentClass != null) {
			newsClass.setParentId(parentClass.getId());
			if (parentClass.getParentNames() == null) {
				newsClass.setParentNames(parentClass.getName());
			} else {
				newsClass
						.setParentNames(parentClass.getParentNames() + "," + parentClass.getName());
			}

		}
		newsClass.setId(newsClassDao.getNewId());
		newsClassDao.insert(newsClass);
	}

	@Override
	public void doSaveNews(NewsClass newsClass, NewsInfo news, String content, String titleImg) {
		// 校验
		addNewsValidate(newsClass, news);
		news.setId(newsInfoDao.getNewId());
		news.setClassCode(newsClass.getCode());
		news.setClassName(newsClass.getName());
		// 内容不为空
		if (TxtUtil.isEmpty(content))
			throw new RuntimeException("正文不可以为空!");
		SysTextLibrary text = mLibraryService.doSaveText(news.getTitle(), content);
		news.setContentId(text.getId());
		if (!TxtUtil.isEmpty(titleImg)) {
			SysImageLibrary image = mLibraryService.doSaveImage(titleImg);
			news.setTitleImageId(image.getId());
		}
		news.setReadNum(0);
		newsInfoDao.insert(news);
	}

	public void addNewsValidate(NewsClass newsClass, NewsInfo news) {
		if (newsClass == null)
			throw new RuntimeException("分类不能为空!");
		NewsInfo newsInfo = new NewsInfo();
		newsInfo.setClassName(news.getClassName());
		newsInfo.setTitle(news.getTitle());
		List<NewsInfo> newsInfos = newsInfoDao.find(newsInfo);
		if (newsInfos != null && newsInfos.size() > 0)
			throw new RuntimeException("该分类下已存在标题为:(" + news.getTitle() + ")的标题!");

	}

	public void editNewsValidate(NewsInfo news) {
		NewsInfo newsOldInfo = newsInfoDao.getById(news.getId());
		if (!(newsOldInfo.getClassName().equals(news.getClassName()) && newsOldInfo.getTitle()
				.equals(news.getTitle()))) {
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setClassName(news.getClassName());
			newsInfo.setTitle(news.getTitle());
			List<NewsInfo> newsInfos = newsInfoDao.find(newsInfo);
			if (newsInfos != null && newsInfos.size() > 0)
				throw new RuntimeException("该分类下已存在标题为:(" + news.getTitle() + ")的标题!");
		}

	}

	@Override
	public void doUpdateClass(NewsClass newsClass) {
		NewsClass old = newsClassDao.getById(newsClass.getId());
		old.setInfo(newsClass.getInfo());
		newsClassDao.update(old);
	}

	@Override
	@Transactional
	public void doUpdateNews(NewsInfo news, String content, String titleImg) {
		// 校验
		editNewsValidate(news);
		NewsInfo old = newsInfoDao.getById(news.getId());
		old.setLoginId(news.getLoginId());
		old.setTitle(news.getTitle());
		old.setAuth(news.getAuth());
		old.setKeyWorks(news.getKeyWorks());
		old.setClassCode(news.getClassCode());
		old.setClassName(news.getClassName());
		old.setSummary(news.getSummary() == null ? "" : news.getSummary());
		old.setOrderid(news.getOrderid() == null ? ((int) (new Date().getTime() / 10000)) : news
				.getOrderid());
		old.setDescription(news.getDescription());
		old.setKeywords(news.getKeywords());
		if (!TxtUtil.isEmpty(content)) {
			SysTextLibrary text = this.getEntityById(SysTextLibrary.class, old.getContentId());
			text.setContext(content);
			this.update(text);
		}
		// 更新图片
		if (old.getTitleImageId() != null) {
			if (TxtUtil.isEmpty(titleImg))
				old.setTitleImageId(null);
			else {
				SysImageLibrary imageLibrary = this.getEntityById(SysImageLibrary.class, old
						.getTitleImageId());
				if (imageLibrary == null || !imageLibrary.getImageUrl().endsWith(titleImg)) {
					imageLibrary = mLibraryService.doSaveImage(titleImg);
					old.setTitleImageId(imageLibrary.getId());
				}
			}
		} else {
			if (!TxtUtil.isEmpty(titleImg)) {
				SysImageLibrary image = mLibraryService.doSaveImage(titleImg);
				old.setTitleImageId(image.getId());
			}
		}

		newsInfoDao.update(old);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<NewsExtendInfo> queryNews(PageRequest<NewsInfo> filter) {
		return (Page<NewsExtendInfo>) newsInfoDao.pageQueryObj(filter, "PAGE_M");
	}

	@Override
	public void doTopNews(NewsInfo news) {
		NewsInfo old = newsInfoDao.getById(news.getId());
		old.setOrderid((int) (new Date().getTime() / 10000));
		newsInfoDao.update(old);
	}

	@Override
	public void doUnTopNew(NewsInfo news) {
		NewsInfo old = newsInfoDao.getById(news.getId());
		old.setOrderid(0);
		newsInfoDao.update(old);
	}

	@Override
	public List<NewsClass> findNewsList() {
		NewsClass newsClass = new NewsClass();
		return newsClassDao.find(newsClass);
	}

	@Override
	public Page<NewsClass> queryPage(PageRequest<NewsClass> request) {
		return newsClassDao.page(request);
	}
}
