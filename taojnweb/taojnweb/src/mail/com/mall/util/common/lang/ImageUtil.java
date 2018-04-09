package com.mall.util.common.lang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public abstract class ImageUtil {
	public static BufferedImage createImage(String code) {
		int width = code.length() * 15, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// g.setColor(Color.BLUE);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, width, height);

		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		for (int i = 0; i < code.length(); i++) {
			String rand = code.substring(i, i + 1);
			// g.setColor(getRandColor(100, 255));
			g.setColor(new Color(48, 76, 129));
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 随机点
		Random random = new Random();
		for (int i = 0; i < code.length() * 15; i++) {
			g.setColor(getRandColor(100, 255));
			int x = random.nextInt(width - 2) + 1;
			int y = random.nextInt(height - 2) + 1;
			g.drawRect(x, y, 0, 0);
		}
		g.dispose();
		return image;
	}

	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static byte[] compressJpegFile(byte[] in, float compressionQuality)
			throws IOException {
		RenderedImage rendImage = ImageIO.read(new ByteArrayInputStream(in));
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);

		ImageWriter writer = null;
		Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");

		if (iter.hasNext()) {
			ImageWriter iw = (ImageWriter) iter.next();
			writer = iw;
		} else
			throw new RuntimeException(
					"There is no jpeg encoding lib in system.");

		writer.setOutput(ios);
		ImageWriteParam iwparam = writer.getDefaultWriteParam();
		iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		iwparam.setCompressionQuality(compressionQuality);
		writer.write(null, new IIOImage(rendImage, null, null), iwparam);
		ios.flush();
		writer.dispose();
		ios.close();
		os.flush();
		return os.toByteArray();
	}

	public static byte[] addFrame(byte[] in, Color cl, int w, String formatName)
			throws IOException {
		Image img = ImageIO.read(new ByteArrayInputStream(in));
		int height = img.getHeight(null);
		int width = img.getWidth(null);
		BufferedImage image = new BufferedImage(width + w * 2, height + w * 2,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(cl);
		g.fillRect(0, 0, width + 2 * w, height + 2 * w);
		g.drawImage(img, w, w, null);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		ImageIO.write(image, formatName, ios);
		return os.toByteArray();
	}

	public static void main(String[] aa) throws IOException {
		FileInputStream fs = new FileInputStream(
				"C:/Users/675053447/Desktop/temp/GMTMP.BMP");
		byte[] af = new byte[10000];
		int len = fs.read(af);
		byte[] fa = new byte[len];
		System.arraycopy(af, 0, fa, 0, len);
		fa = ImageUtil.addFrame(fa, Color.WHITE, 10, "BMP");
		byte[] am = ImageUtil.compressJpegFile(fa, 1);
		File f = new File("C:/Users/675053447/Desktop/temp/GMTMP2.jpg");
		FileOutputStream out = new FileOutputStream(f);
		out.write(am);
		System.out.println(am.length);
	}
}
