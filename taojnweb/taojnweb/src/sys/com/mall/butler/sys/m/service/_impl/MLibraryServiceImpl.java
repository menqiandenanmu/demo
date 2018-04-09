package com.mall.butler.sys.m.service._impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import com.mall.butler.ManageContext;
import com.mall.butler.service._impl.BaseServiceImpl;
import com.mall.butler.sys.dao.SysImageLibraryDao;
import com.mall.butler.sys.dao.SysTextLibraryDao;
import com.mall.butler.sys.m.service.MLibraryService;
import com.mall.butler.sys.model.SysImageLibrary;
import com.mall.butler.sys.model.SysTextLibrary;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MLibraryServiceImpl extends BaseServiceImpl implements MLibraryService {
	@Autowired
	private SysImageLibraryDao sysImageLibraryDao;
	@Autowired
	private SysTextLibraryDao sysTextLibraryDao;
	private int midWidth;
	private int midHeight;
	private int smallWidth;
	private int smallHeight;

	@Override
	@Deprecated
	public SysImageLibrary doSaveImage(String filePath, Long targetId, Integer targetType) {
		// 得到保存库的相对路径和保存文件的绝对路径
		String reg = "\\..*$";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(filePath);
		// 括展名
		String ext = matcher.find() ? matcher.group(0) : "";
		String tempName = filePath.replaceAll(reg, "");
		String tempBaseName = filePath.replaceAll(reg, "").replaceFirst(ManageContext.UPLOAD_VPATH,
				ManageContext.UPLOAD_PATH);
		String midPath = tempName + "mid" + ext;
		String baseMidPath = tempBaseName + "mid" + ext;
		String smallPath = tempName + "smail" + ext;
		String baseSmallPath = tempBaseName + "smail" + ext;
		String baseFilePath = tempBaseName + ext;
		try {
			// 保存文件
			File file = new File(baseFilePath);
			if (!file.exists()) {
				return null;
			}
			Image img;
			img = ImageIO.read(file);
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return null;
			} else {
				midWidth = midWidth <= 0 ? img.getWidth(null) : midWidth;
				midHeight = midHeight <= 0 ? img.getHeight(null) : midHeight;
				smallWidth = smallWidth <= 0 ? img.getWidth(null) : smallWidth;
				smallHeight = midWidth <= 0 ? img.getHeight(null) : smallHeight;
				// 压缩中图
				BufferedImage tag = new BufferedImage(midWidth, midHeight,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(
						img.getScaledInstance(midWidth, midHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(baseMidPath);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();

				// 压缩小图
				tag = new BufferedImage(smallWidth, smallHeight, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(
						img.getScaledInstance(smallWidth, smallHeight, Image.SCALE_SMOOTH), 0, 0,
						null);
				out = new FileOutputStream(baseSmallPath);
				// JPEGImageEncoder可适用于其他图片类型的转换 s
				encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
			// 保存数据库
			SysImageLibrary image = new SysImageLibrary();
			image.setId(sysImageLibraryDao.getNewId());
			image.setImageType(0);
			image.setImageUrlMid(midPath);
			image.setImageUrlSmall(smallPath);
			image.setImageUrl(filePath);
			image.setTargetId(targetId);
			image.setTargetType(targetType);
			sysImageLibraryDao.insert(image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SysImageLibrary doSaveImage(String filePath) {
		// 得到保存库的相对路径和保存文件的绝对路径
		String reg = "\\..*$";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(filePath);
		// 括展名
		String ext = matcher.find() ? matcher.group(0) : "";
		String tempName = filePath.replaceAll(reg, "");
		String tempBaseName = filePath.replaceAll(reg, "").replaceFirst(ManageContext.UPLOAD_VPATH,
				ManageContext.UPLOAD_PATH);
		String midPath = tempName + "mid" + ext;
		String baseMidPath = tempBaseName + "mid" + ext;
		String smallPath = tempName + "smail" + ext;
		String baseSmallPath = tempBaseName + "smail" + ext;
		String baseFilePath = tempBaseName + ext;
		try {
			// 保存文件
			File file = new File(baseFilePath);
			if (!file.exists()) {
				return null;
			}
			Image img;
			img = ImageIO.read(file);
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return null;
			} else {
				midWidth = midWidth <= 0 ? img.getWidth(null) : midWidth;
				midHeight = midHeight <= 0 ? img.getHeight(null) : midHeight;
				smallWidth = smallWidth <= 0 ? img.getWidth(null) : smallWidth;
				smallHeight = midWidth <= 0 ? img.getHeight(null) : smallHeight;
				// 压缩中图
				BufferedImage tag = new BufferedImage(midWidth, midHeight,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(
						img.getScaledInstance(midWidth, midHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(baseMidPath);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();

				// 压缩小图
				tag = new BufferedImage(smallWidth, smallHeight, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(
						img.getScaledInstance(smallWidth, smallHeight, Image.SCALE_SMOOTH), 0, 0,
						null);
				out = new FileOutputStream(baseSmallPath);
				// JPEGImageEncoder可适用于其他图片类型的转换 s
				encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
			// 保存数据库
			SysImageLibrary image = new SysImageLibrary();
			image.setId(sysImageLibraryDao.getNewId());
			image.setImageType(0);
			image.setImageUrlMid(midPath);
			image.setImageUrlSmall(smallPath);
			image.setImageUrl(filePath);
			sysImageLibraryDao.insert(image);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SysTextLibrary doSaveText(SysTextLibrary text) {
		text.setId(sysTextLibraryDao.getNewId());
		sysTextLibraryDao.insert(text);
		return text;
	}

	@Override
	public SysTextLibrary doSaveText(String title, String content) {
		SysTextLibrary text = new SysTextLibrary();
		text.setId(sysTextLibraryDao.getNewId());
		text.setTitle(title);
		text.setContext(content);
		sysTextLibraryDao.insert(text);
		return text;
	}

	public void doUpdateText(SysTextLibrary text) {

	}

	public void setMidWidth(int midWidth) {
		this.midWidth = midWidth;
	}

	public void setMidHeight(int midHeight) {
		this.midHeight = midHeight;
	}

	public void setSmallWidth(int smallWidth) {
		this.smallWidth = smallWidth;
	}

	public void setSmallHeight(int smallHeight) {
		this.smallHeight = smallHeight;
	}

}
