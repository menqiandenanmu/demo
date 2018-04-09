package com.mall.butler.account.w.action;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.account.m.service.MAccountCouponService;
import com.mall.butler.account.model.AccountCoupon;
import com.mall.butler.action.w.WebBaseAction;
import com.mall.util.common.TxtUtil;
import com.mall.util.ibatis.page.Page;
import com.mall.util.ibatis.page.PageRequest;

public class AccountCouponAction extends WebBaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private AccountCoupon accountCoupon;
	@Autowired
	private MAccountCouponService accountCouponService;
	private Page<AccountCoupon> page;
	public String execute() {
		PageRequest<AccountCoupon> tradeRequest = super
				.newPage(AccountCoupon.class);
		tradeRequest.setPageNumber(currPage);
		tradeRequest.setPageSize(100);
		if (null == accountCoupon)
			accountCoupon = new AccountCoupon();
		AccountCoupon fCoupon=new AccountCoupon();
		fCoupon.setAccountId(super.getAccount().getId());
		if(!TxtUtil.isEmpty(accountCoupon.getCouponName()))
			fCoupon.setCouponName("%"+accountCoupon.getCouponName().trim()+"%");
		tradeRequest.setFilters(fCoupon);
		page = accountCouponService.pageQuery(tradeRequest);
		return SUCCESS;
	}
	public String couponPay(){
		accountCoupon=accountCouponService.getEntityById(AccountCoupon.class,id);
		if(null==accountCoupon||accountCoupon.getDeleted())
			throw new RuntimeException("赠券不存在");
		if(super.getAccount().getId().intValue()!=accountCoupon.getAccountId().intValue())
			throw new RuntimeException("违规操作");
		if(accountCoupon.getCouponStatus().intValue()!=0)
			throw new RuntimeException("赠券已经失效或已使用");
		return SUCCESS;
	}
	public void couponCode(){
		accountCoupon=accountCouponService.getEntityById(AccountCoupon.class,id);
		if(null==accountCoupon||accountCoupon.getDeleted())
			throw new RuntimeException("赠券不存在");
		if(super.getAccount().getId().intValue()!=accountCoupon.getAccountId().intValue())
			throw new RuntimeException("违规操作");
		if(accountCoupon.getCouponStatus().intValue()!=0)
			throw new RuntimeException("赠券已经失效或已使用");
		//生成条码
		try {
			 JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
			 String str = accountCoupon.getBarcode();
		      BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
		      saveToGIF(localBufferedImage, "EAN13.gif");
		      localJBarcode.setEncoder(EAN8Encoder.getInstance());
		      localJBarcode.setTextPainter(EAN8TextPainter.getInstance());
		      
		      
			// 输出图片
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ImageIO.write(localBufferedImage, "JPEG", response
					.getOutputStream());
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	  static void saveToGIF(BufferedImage paramBufferedImage, String paramString)
	  {
	    saveToFile(paramBufferedImage, paramString, "gif");
	  }
	static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "jpeg");
	}

	  static void saveToPNG(BufferedImage paramBufferedImage, String paramString)
	  {
	    saveToFile(paramBufferedImage, paramString, "png");
	  }

	static void saveToFile(BufferedImage paramBufferedImage,
			String paramString1, String paramString2) {
		try {
//			FileOutputStream localFileOutputStream = new FileOutputStream(
//					"d:/upload/" + paramString1);
			FileOutputStream localFileOutputStream = new FileOutputStream(
					"/usr/apps/www.taojn.com/images/" + paramString1);
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2,
					localFileOutputStream, 96, 96);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	public AccountCoupon getAccountCoupon() {
		return accountCoupon;
	}

	public void setAccountCoupon(AccountCoupon accountCoupon) {
		this.accountCoupon = accountCoupon;
	}

	public Page<AccountCoupon> getPage() {
		return page;
	}

	public void setPage(Page<AccountCoupon> page) {
		this.page = page;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
