package com.mall.butler.action.m;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.model.AccountInfo;
import com.mall.butler.account.model.AccountLogin;
import com.mall.butler.account.model.StoreInfo;
import com.mall.butler.sys.m.service.MRoleService;
import com.mall.butler.sys.model.SysFunctions;
import com.mall.butler.sys.vo.FunctionsVo;
import com.swetake.util.Qrcode;

/**
 * 操作框架页面
 * 
 * 项目名称：standard2.0 类名称：IndexAction 类描述： 创建人：caedmon 675053447@gmail.com
 * 创建时间：2011-4-19 下午02:23:14 修改人：caedmon 修改时间：2011-4-19 下午02:23:14 修改备注：
 * 
 * @version
 * 
 */
public class IndexAction extends ManageBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5805240585698958776L;
	private static String LEFT = "left";
	private static String PANEL = "panel";
	private static String TOP = "top";
	private List<FunctionsVo> funcs = new LinkedList<FunctionsVo>();
	private List<FunctionsVo> funcParent = new LinkedList<FunctionsVo>();
	@Autowired
	private MRoleService mRoleService;
	private StoreInfo storeInfo;
	private Integer count;
	static int width = 90;
	static int height = 90;

	public String execute() {
		this.left();
		return SUCCESS;
	}

	public void qrCode() {

		// 判断当前用户
		AccountInfo accountInfo = super.getAccount();
		if (accountInfo.getAccType().intValue() == 4) {
			// 设置二维码
			StoreInfo storeInfo = mRoleService.getEntityById(StoreInfo.class, accountInfo.getId());
			String qrcode = super.getBasePath() + "/user/qrToPay.htm?barCode=" + storeInfo.getQrCode();
			// 生成条码
			try {

				// 输出图片
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				ImageIO.write(create_image(qrcode), "JPEG", response.getOutputStream());
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static BufferedImage create_image(String sms_info) throws Exception {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		try {
			Qrcode testQrcode = new Qrcode();
			testQrcode.setQrcodeErrorCorrect('M');
			testQrcode.setQrcodeEncodeMode('B');
			testQrcode.setQrcodeVersion(7);
			String testString = sms_info;
			byte[] d = testString.getBytes("gbk");
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.WHITE);
			g.clearRect(0, 0, width, height);
			g.setColor(Color.BLACK);

			// 限制最大字节数为119
			if (d.length > 0 && d.length < 120) {
				boolean[][] s = testQrcode.calQrcode(d);
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s.length; j++) {
						if (s[j][i]) {
							g.fillRect(j * 2, i * 2, 2, 2);
						}
					}
				}
			}
			g.dispose();
			bi.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bi;
	}

	/**
	 * 框架页面头部
	 * 
	 * @author caedmon
	 * @date 2010-10-18 下午06:10:12
	 * @return
	 */
	public String top() {
		return TOP;
	}

	/**
	 * 框架页面左边
	 * 
	 * @author juror
	 * @date 2010-10-18 下午06:10:35
	 * @return
	 */
	public String left() {
		AccountLogin login = this.getLogin();
		List<SysFunctions> functionsL = mRoleService.queryFuncByLogin(login);
		Map<Long, FunctionsVo> map = new Hashtable<Long, FunctionsVo>();
		// 整理内容
		for (SysFunctions sfIndex : functionsL) {
			FunctionsVo indexVo = new FunctionsVo();
			indexVo.setId(sfIndex.getId());
			indexVo.setCheckFlage(true);
			indexVo.setFunName(sfIndex.getFunName());
			indexVo.setFunType(sfIndex.getFunType());
			String funUrl = sfIndex.getFunUrl();
			indexVo.setFunUrl(funUrl);
			map.put(sfIndex.getId(), indexVo);
		}
		// 创建树
		for (SysFunctions sfIndex : functionsL) {
			FunctionsVo indexVo = map.get(sfIndex.getId());
			if (indexVo == null || sfIndex.getParentId() == null)
				continue;
			if (sfIndex.getParentId().equals(sfIndex.getId())) {
				funcs.add(indexVo);
			} else {
				FunctionsVo parent = map.get(sfIndex.getParentId());
				parent.getChilds().add(indexVo);
				indexVo.setParent(parent);
			}
		}
		return LEFT;
	}

	/**
	 * 框架页面左边
	 * 
	 * @author caedmon
	 * @date 2010-10-18 下午06:10:35
	 * @return
	 */
	public String left1() {
		AccountLogin login = this.getLogin();
		AccountInfo accountInfo = super.getAccount();
		if (accountInfo.getAccType().intValue() == 4) {
			FunctionsVo sysFunctions = new FunctionsVo();
			sysFunctions.setFunName("店铺管理");
			sysFunctions.setFunType(0);
			sysFunctions.setId(1l);
			List<FunctionsVo> childs = new ArrayList<FunctionsVo>();
			FunctionsVo funVo = new FunctionsVo();
			funVo.setFunName("店铺管理");
			funVo.setFunType(1);
			funVo.setId(2l);
			funVo.setFunUrl("manage/store/index.htm");
			childs.add(funVo);
			FunctionsVo funVo2 = new FunctionsVo();
			funVo2.setFunName("折扣管理");
			funVo2.setFunType(1);
			funVo2.setId(3l);
			funVo2.setFunUrl("manage/account/discountRule.htm");
			childs.add(funVo2);
			FunctionsVo funVo3 = new FunctionsVo();
			funVo3.setFunName("交易管理");
			funVo3.setFunType(1);
			funVo3.setId(4l);
			funVo3.setFunUrl("manage/account/agentTrans.htm");
			childs.add(funVo3);
			sysFunctions.setChilds(childs);
			funcs.add(sysFunctions);
			FunctionsVo parentVo = new FunctionsVo();
			parentVo.setId(1l);
			parentVo.setBeginNum(1);
			parentVo.setEndNum(4);
			funcParent.add(parentVo);
		} else {
			// 取值要按照树形结构排序 order by fparentId ,fun_type
			List<SysFunctions> functionsL = mRoleService.queryFuncByLogin(login);
			Map<Long, FunctionsVo> map = new Hashtable<Long, FunctionsVo>();
			// 整理内容
			for (SysFunctions sfIndex : functionsL) {
				FunctionsVo indexVo = new FunctionsVo();
				indexVo.setId(sfIndex.getId());
				indexVo.setCheckFlage(true);
				indexVo.setFunName(sfIndex.getFunName());
				indexVo.setFunType(sfIndex.getFunType());
				indexVo.setFunUrl(sfIndex.getFunUrl());
				map.put(sfIndex.getId(), indexVo);
			}
			int num = 1;
			Long parentId = null;
			int i = 0;
			// 创建树
			FunctionsVo parentVo = null;
			int menunum = 0;
			for (SysFunctions sfIndex : functionsL) {
				// 计算一共有几个主菜单
				if (sfIndex.getFunType().intValue() == 0)
					menunum += 1;
			}
			for (SysFunctions sfIndex : functionsL) {
				i += 1;
				// 如果parentId不相同就记录上一个结束值，否则数字相加
				if (num == 1) {
					parentVo = new FunctionsVo();
					parentVo.setBeginNum(num);
				}
				if (null != parentId && !parentId.equals(sfIndex.getParentId())) {
					SysFunctions functions = mRoleService.getEntityById(SysFunctions.class, parentId);
					parentVo.setId(functions.getId());
					parentVo.setEndNum(num - 1);
					funcParent.add(parentVo);
					parentVo = new FunctionsVo();
					parentVo.setBeginNum(num);

				} else {
					if (sfIndex.getFunType() != 0)
						num += 1;
				}
				if (functionsL.size() == i && funcParent.size() > 2) {
					SysFunctions functions = mRoleService.getEntityById(SysFunctions.class, parentId);
					parentVo.setId(functions.getId());
					parentVo.setEndNum(num);
					funcParent.add(parentVo);
				}
				// if (functionsL.size() == 2 && i == functionsL.size()) {
				// SysFunctions functions = mRoleService.getEntityById(
				// SysFunctions.class, parentId);
				// parentVo = new FunctionsVo();
				// parentVo.setBeginNum(1);
				// parentVo.setId(functions.getId());
				// parentVo.setEndNum(1);
				// funcParent.add(parentVo);
				// }

				FunctionsVo indexVo = map.get(sfIndex.getId());
				if (indexVo == null)
					continue;

				if (sfIndex.getParentId().equals(sfIndex.getId())) {
					funcs.add(indexVo);
				} else {
					FunctionsVo parent = map.get(sfIndex.getParentId());
					parent.getChilds().add(indexVo);
					indexVo.setParent(parent);
				}

				parentId = sfIndex.getParentId();
			}
			if (menunum == 1) {
				// 只有一个菜单的
				SysFunctions functions = mRoleService.getEntityById(SysFunctions.class, parentId);
				parentVo = new FunctionsVo();
				parentVo.setBeginNum(1);
				parentVo.setId(functions.getId());
				parentVo.setEndNum(functionsL.size());
				funcParent.add(parentVo);
			}
		}
		return LEFT;
	}

	/**
	 * 框架页面操作面版
	 * 
	 * @author caedmon
	 * @date 2010-10-18 下午06:10:45
	 * @return
	 */
	public String panel() {
		AccountInfo accountInfo = super.getAccount();
		if (accountInfo.getAccType().intValue() == 4) {
			storeInfo = mRoleService.getEntityById(StoreInfo.class, accountInfo.getId());
		}
		return PANEL;
	}

	public List<FunctionsVo> getFuncs() {
		return funcs;
	}

	public Integer getCount() {
		return count;
	}

	public List<FunctionsVo> getFuncParent() {
		return funcParent;
	}

	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

}
