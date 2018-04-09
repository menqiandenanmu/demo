package com.mall.butler.action.m;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mall.butler.ManageContext;
import com.mall.util.common.TxtUtil;
import com.mall.util.common.action.MessageDialog;
import com.mall.util.common.lang.DateUtil;

public class UploadFileAction extends ManageBaseAction {
	private final static Log log = LogFactory.getLog(UploadFileAction.class);
	private File file;
	private String filename;
	private String folder;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3285900167767311158L;

	public String execute() {
		log.debug("上传文件开始!");
		if (file != null) {
			FileOutputStream outputStream;
			try {
				String basePath = ManageContext.UPLOAD_PATH;// 绝对路径
				String path; // 相对路径
				String fname; // 新文件名
				// 如果上传目录为空以日期为默认目录
				if (TxtUtil.isEmpty(folder)) {
					path = folder;
				} else {
					path = DateUtil.format(new Date(), "yyyyMM");
				}
				basePath = basePath + "/" + path;
				path = ManageContext.UPLOAD_VPATH + "/" + path;
				try {
					// 创建目录
					File f = new File(basePath);
					if (!f.exists())
						f.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 取文件文件括展名,以uuid为文件名。
				String[] fs = filename.split("\\.");
				if (fs.length > 0) {
					fname = UUID.randomUUID().toString() + "."
							+ fs[fs.length - 1];
				} else {
					fname = UUID.randomUUID().toString();
				}
				basePath = basePath + "/" + fname;
				path = path + "/" + fname;
				outputStream = new FileOutputStream(basePath);
				FileInputStream fileIn = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fileIn.read(buffer)) > 0) {
					outputStream.write(buffer, 0, len);
				}
				fileIn.close();
				outputStream.close();
				msgInfo.addMessage(path);
				msgInfo.setFlag(MessageDialog.SUCCESS);
				log.debug("上传文件成功:" + basePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.error(e);
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
				throw new RuntimeException(e);
			}
		} else {
			msgInfo.addMessage("文件为空!");
			msgInfo.setFlag(MessageDialog.ERROR);
			log.debug("上传文件为空!");
		}
		return JDIALOG;
	}

	public String test() {
		return "test";
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
