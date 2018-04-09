package com.mall.butler.action.m;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.helper.SessionHelper;
import com.mall.util.common.lang.ImageUtil;

public class CheckCodeAction extends ManageBaseAction {
	private static final long serialVersionUID = -3626815387061897877L;
	@Autowired
	private SessionHelper sessionHelper;

	private void pucCheckCode() throws IOException {
		Random random = new Random();
		String rands = "";
		for (int i = 0; i < 4; i++) {
			rands = rands + random.nextInt(10);
		}
		// 保存 值 到 session
		sessionHelper.set(ManageContext.SESSION_CHECKCODE, rands);
		// 生成图片
		BufferedImage image = ImageUtil.createImage(rands);
		// 输出图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().close();
	}

	@Override
	public String execute() throws IOException {
		this.pucCheckCode();
		return NONE;
	}
}
