package com.mall.util.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 文件上传操作工具
 * @author caedmon
 * @date 2010-6-12 下午02:36:14 
 */
public class UploadUtil {

	public static String IMAGE_MID = "_mid";
	public static String IMAGE_SMALL = "_small";
	
	/**
	 * 删除文件
	 * @param path 
	 * 				文件路径及名称(如d:/report_temp/report.txt)
	 * @return boolean
	 */
	public static boolean delFile(String path) {
		File myDelFile = new java.io.File(path);
		if (!myDelFile.exists()) { return true; }
		return myDelFile.delete();
	}
	
	/**
	 * 根据路径创建一系列的目录
	 * @param path 
					目录路径
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
	 * 上传文件
	 * @param uploadFileName 
	 * 				上传的文件名称
	 * @param savePath 
	 * 				文件的保存路径
	 * @param uploadFile 
	 * 				被上传的文件
	 * @return String
	 * @throws IOException 
	 */
	public static String upload(String uploadFileName, String savePath, File uploadFile) throws IOException {
		String newFileName = getRandomName(uploadFileName, savePath);
		FileOutputStream fos=null;
		FileInputStream fis=null;
		try {
			 fos = new FileOutputStream(savePath + newFileName);
			 fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			fos.close();
			fis.close();
		}
		return newFileName;
	}
	
	/**
	 * 
	 * 上传文件组
	 *  uploadFileName
					文件名
	 *  uploadFileName
					组名
	 *  uploadFile
					文件对象
	 */
	public static String uploadForGroup(String fileName,String filePath, String groupName, File uploadFile) {
		String newFileName = fileName.replaceAll("\\.", groupName+".");
		try {
			FileOutputStream fos = new FileOutputStream(filePath + "/" + newFileName);
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
		return newFileName;
	}
	
	/**
	 * 获得随机文件名,保证在同一个文件夹下不同名 生成文件规则
	 * @param fileName
	 * 				文件名
	 * @param dir
	 * 				文件的目录
	 * @return String
	 */
	public static String getRandomName(String fileName, String dir) {
		String[] split = fileName.split("\\.");	// 将文件名已.的形式拆分
		String extendFile = "." + split[split.length - 1].toLowerCase(); // 获文件的有效后缀

		Random random = new Random();
		int add = random.nextInt(1000000); // 产生随机数1000000以内
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = split[0] + add + extendFile;
		}
		return ret;
	}
	
	/**
	 * 判断文件是否存在
	 * @param fileName
	 * 				文件名
	 * @param dir
	 * 				文件的目录
	 * @return boolean
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return files.exists() ? true : false;
	}
	
	/**
	 * 判断文件类型是否是合法的,就是判断allowTypes中是否包含contentType
	 * @param contentType
	 * 				需要判断的文件类型后缀名
	 * @param allowTypes
	 * 				合法的文件类型后缀名
	 * @return
	 */
	public static boolean isValid(String contentType, String[] allowTypes) {
		if (null == contentType || "".equals(contentType)) { return false; }
		for (String type : allowTypes) {
			if (contentType.equals(type)) { return true; }
		}
		return false;
	}
	
	/**
	 * 获取文件前缀
	 * @author caedmon
	 * @date 2010-7-28 下午02:25:22
	 * @param fileName
	 * @return
	 */
	public static String getPrefix(String filename) {
		return filename.substring(0, filename.lastIndexOf('.'));
	}
	
	/**
	 * 获取文件前缀路径
	 * @author caedmon
	 * @date 2010-7-28 下午02:25:22
	 * @param fileUri
	 * @return
	 */
	public static String getUriPrefix(String fileUri) {
		return fileUri.substring(0, fileUri.lastIndexOf("/"));
	}
	
	/**
	 * 获取文件名
	 * @author caedmon
	 * @date 2010-7-28 下午02:25:14
	 * @param fileUri 文件路径
	 * @return
	 */
	public static String getFileName(String fileUri) {
		return fileUri.substring(fileUri.lastIndexOf("/") + 1, fileUri.length());
	}
	
	/**
	 * 获得文件后缀
	 * @param filename
	 * @return String
	 */
	public static String getExt(String filename) {  
		return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();  
	}  
	
	/**
	 * 创建缩略图
	 * @param file
	 * @param width
	 * @param height
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
	 * 创建文件
	 * @param path
	 * @param filename
	 * @param fileinfo
	 * @return String
	 */
	public static String createFile(String path, String filename, byte[] fileinfo) {
		String filepath=path + filename;		
		File file = new File(filepath);
		mkDirectory(file.getParent());
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

	/**
	 * 得到文件字节数组
	 * @param path
	 * @return byte[]
	 * @throws IOException 
	 */
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
	
	/**
	 * 测试入口
	 * @param args void
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String savePath = "D:/upload/";
		//创建目录
		mkDirectory(savePath);
		//上传文件
		File uploadFile = new File(
				"C:/Documents and Settings/Administrator/My Documents/My Pictures/7_4bd3c4c4bab22.jpg");
		String fileName = upload(uploadFile.getName(), savePath, uploadFile);
		try {
			//创建缩略图
			float a = 100;
			float b = 100;
			String filePath = createFile(savePath,getRandomName(fileName, savePath),readFileToBytes(uploadFile.getPath()));
			File minifile = new File(filePath);
			createMiniPic(minifile,a,b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
