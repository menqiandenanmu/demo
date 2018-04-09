package com.mall.util.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 添加水印, filePath 源图片全路径 含图片名 watermark 水印图片全路径 含图片名 savePath 添加水印后的图片保存路径文件夹
 * 为空则保存到源文件目录 words 要添加的文字 为空则不添加 小于200*200的图片不添加水印
 */
public class WaterMarkUtil {

	private static int wid = 0;

	private static int het = 0;

	public static void createMark(String filePath, String words,
			String savePath, String watermark) throws IOException {
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		// 可以在此加入是否url为http的判断
		ImageIcon waterIcon = new ImageIcon(new URL(watermark));
		Image waterImg = waterIcon.getImage();
		File f = new File(filePath);
		String picname = f.getName();// 取得图片名
		// if (watermark != null && !watermark.equals("")) {// 当水印图标不为空时
		wid = waterImg.getWidth(null); // 水印图标宽度
		het = waterImg.getHeight(null); // 水印图标高度
		int width = theImg.getWidth(null); // 源图片宽度
		int height = theImg.getHeight(null); // 源图片高度
		if (width < 200 || height < 200) { // 如果高度或宽度小于200则不添加水印
			return;
		}
		if (TxtUtil.isEmpty(savePath)) {
			savePath = filePath;// 如果未指定保存路径则保存回原路径
		} else {
			savePath = savePath + "\\" + picname;// 指定保存文件夹时,拼接出保存路径
		}
		try {
			BufferedImage bimage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimage.createGraphics();
			Font font = new Font("新宋体", Font.PLAIN, 15);
			g.setColor(Color.red); // 设置颜色
			g.setFont(font);
			g.setBackground(Color.white);
			g.drawImage(theImg, 0, 0, null);
			g.drawImage(waterImg, width - wid - 5, height - het - 5, null); // 中间两个参数定义水印在背景图的位置
			// g.drawString(words, (int) (width * 0.75), (int) (height * 0.92));
			// 添加文字
			if (!TxtUtil.isEmpty(words)) {
				g.drawString(words, width - 120, height - 20);
			}
			FileOutputStream out = new FileOutputStream(savePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(70f, true); // 图片质量
			encoder.encode(bimage, param);
			out.close();
		} catch (Exception e) {
			// e.printStackTrace();
			return;
		} finally {
			System.gc();// 清理 垃圾对象
		}
	}

	// public static void main(String arg[]){
	// WaterMarkOperation wk=new WaterMarkOperation();
	// try {
	// wk.createMark("d:/321.jpg", "d:/logo.jpg", "", "d:/");
	// } catch (Exception e) {
	// System.out.print("fuck");
	// }
	// }

}
