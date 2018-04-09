package com.mall.util.ibatis.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author: caedmon
 * @email : wolfstar4758@163.com
 * @date : 2008-12-9 下午04:08:01
 * @Information : 本地文件操作工具
 */
public class FileUtils {

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static boolean delFile(String filePathAndName) {
		File myDelFile = new java.io.File(filePathAndName);
		if (!myDelFile.exists()) { return true; }
		return myDelFile.delete();
	}

	/**
	 * 上传文件
	 * 
	 * @param uploadFileName
	 *            被上传的文件名称
	 * @param savePath
	 *            文件的保存路径
	 * @param uploadFile
	 *            被上传的文件
	 * @return newFileName
	 */
	public static String upload(String uploadFileName, String savePath, File uploadFile) {
		String newFileName = getRandomName(uploadFileName, savePath);
		try {
			FileOutputStream fos = new FileOutputStream(savePath + newFileName);
			FileInputStream fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFileName;
	}
	
	/**
	 * 
	 * 上传文件萎缩图
	 *  uploadFileName: 图片原文件名
	 *  uploadFileName: 图片组名
	 *  uploadFile :    上传文件对象
	 */
	public static String uploadForGroup(String uploadFileName,String savePath, String groupName, File uploadFile) {
		uploadFileName = uploadFileName.replaceAll("\\.", groupName+".");
		try {
			FileOutputStream fos = new FileOutputStream(savePath + uploadFileName);
			FileInputStream fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) 
				fos.write(buffer, 0, len);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadFileName;
	}

	/**
	 * 根据路径创建一系列的目录
	 * 
	 * @param path
	 */
	public static void mkDirectory(String path) {
		File file;
		try {
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
	}

	/**
	 * @param str
	 *            目标字符串
	 * @param separatorChars
	 *            分隔符字符串
	 * @return 字符串数组
	 */

	/*
	 * public static String[] split(String str, char separatorChar) { return
	 * splitWorker(str, separatorChar, false); }
	 * 
	 * private static String[] splitWorker(String str, char separatorChar,
	 * boolean preserveAllTokens) { if(str == null) return null; int len =
	 * str.length(); if(len == 0) return null; List list = new ArrayList(); int
	 * i = 0; int start = 0; boolean match = false; boolean lastMatch = false;
	 * while(i < len) if(str.charAt(i) == separatorChar) { if(match ||
	 * preserveAllTokens) { list.add(str.substring(start, i)); match = false;
	 * lastMatch = true; } start = ++i; } else { lastMatch = false; match =
	 * true; i++; } if(match || preserveAllTokens && lastMatch)
	 * list.add(str.substring(start, i)); return (String[])list.toArray(new
	 * String[list.size()]); }
	 */
	/**
	 * 调用指定字段的setter方法
	 * 
	 * <pre>
	 *    Example:
	 *    User user = new User();
	 *    MyUtils.invokeSetMethod(&quot;userName&quot;, user, new Object[] {&quot;张三&quot;});
	 * </pre>
	 * 
	 * @param fieldName
	 *            字段(属性)名称
	 * @param invokeObj
	 *            被调用方法的对象
	 * @param args
	 *            被调用方法的参数数组
	 * @return 成功与否
	 */
	public static boolean invokeSetMethod(String fieldName, Object invokeObj, Object[] args) {
		boolean flag = false;
		Field[] fields = invokeObj.getClass().getDeclaredFields(); // 获得对象实体类中所有定义的字段
		Method[] methods = invokeObj.getClass().getDeclaredMethods(); // 获得对象实体类中所有定义的方法
		for (Field f : fields) {
			String fname = f.getName();
			if (fname.equals(fieldName)) {// 找到要更新的字段名
				String mname = "set" + fname.substring(0, 1).toUpperCase() + fname.substring(1);// 组建setter方法
				for (Method m : methods) {
					String name = m.getName();
					if (mname.equals(name)) {
						// 处理Integer参数
						if (f.getType().getSimpleName().equalsIgnoreCase("integer") && args.length > 0) {
							args[0] = Integer.valueOf(args[0].toString());
						}
						// 处理Boolean参数
						if (f.getType().getSimpleName().equalsIgnoreCase("boolean") && args.length > 0) {
							args[0] = Boolean.valueOf(args[0].toString());
						}
						try {
							m.invoke(invokeObj, args);
							flag = true;
						} catch (IllegalArgumentException e) {
							flag = false;
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							flag = false;
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							flag = false;
							e.printStackTrace();
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return files.exists() ? true : false;
	}

	/**
	 * 获得随机文件名,保证在同一个文件夹下不同名 生成文件规则
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static String getRandomName(String fileName, String dir) {
		String[] split = fileName.split("\\.");// 将文件名已.的形式拆分
		String extendFile = "." + split[split.length - 1].toLowerCase(); // 获文件的有效后缀

		Random random = new Random();
		int add = random.nextInt(1000000); // 产生随机数10000以内
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = fileName + add + extendFile;
		}
		return ret;
	}

	/**
	 * 创建缩略图
	 * 
	 * @param file
	 *            上传的文件流
	 * @param height
	 *            最小的尺寸
	 * @throws IOException
	 */
	public static void createMiniPic(File file, float width, float height) throws IOException {
		Image src = javax.imageio.ImageIO.read(file); // 构造Image对象
		int old_w = src.getWidth(null); // 得到源图宽
		int old_h = src.getHeight(null);
		int new_w = 0;
		int new_h = 0; // 得到源图长
		float tempdouble;
		if (old_w >= old_h) {
			tempdouble = old_w / width;
		} else {
			tempdouble = old_h / height;
		}

		if (old_w >= width || old_h >= height) { // 如果文件小于锁略图的尺寸则复制即可
			new_w = Math.round(old_w / tempdouble);
			new_h = Math.round(old_h / tempdouble);// 计算新图长宽
			while (new_w > width && new_h > height) {
				if (new_w > width) {
					tempdouble = new_w / width;
					new_w = Math.round(new_w / tempdouble);
					new_h = Math.round(new_h / tempdouble);
				}
				if (new_h > height) {
					tempdouble = new_h / height;
					new_w = Math.round(new_w / tempdouble);
					new_h = Math.round(new_h / tempdouble);
				}
			}
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src, 0, 0, new_w, new_h, null); // 绘制缩小后的图
			FileOutputStream newimage = new FileOutputStream(file); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
			param.setQuality((float) (100 / 100.0), true);// 设置图片质量,100最大,默认70
			encoder.encode(tag, param);
			encoder.encode(tag); // 将JPEG编码
			newimage.close();
		}
	}

	/**
	 * 判断文件类型是否是合法的,就是判断allowTypes中是否包含contentType
	 * 
	 * @param contentType
	 *            文件类型
	 * @param allowTypes
	 *            文件类型列表
	 * @return 是否合法
	 */
	public static boolean isValid(String contentType, String[] allowTypes) {
		if (null == contentType || "".equals(contentType)) { return false; }
		for (String type : allowTypes) {
			if (contentType.equals(type)) { return true; }
		}
		return false;
	}

	public static String create(String path, String filename, byte[] fileinfo) {
		String filepath=path + filename;
		FileUtils.mkDirectory(path);
		File file = new File(filepath);
		try {
			FileOutputStream os = new FileOutputStream(file);
			os.write(fileinfo);
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return filepath;
	}

	public static byte[] readFileToBytes(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		byte[] temp = new byte[1024];
		int size = 0;
		while ((size = in.read(temp)) != -1) {
			out.write(temp, 0, size);
		}
		in.close();
		byte[] content = out.toByteArray();
		return content;
	}

	public static void main(String[] s) {

		try {
			byte[] b = readFileToBytes("c:/aa.txt");
			System.out.println(b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
