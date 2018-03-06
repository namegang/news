package com.news.utils;

import com.news.utils.comm.ResultInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;



public class FileUpload {
	public static ResultInfo uploadFile(String root, String pathname, String fileName, InputStream inputStream) {
		ResultInfo resultInfo = new ResultInfo();
		genFolder(root + pathname);
		if (ConfigProp.getConfig("useFtp").equals("false")) {

			try {
				FileOutputStream os = new FileOutputStream(root + pathname + fileName);
				int b = 0;
				while ((b = inputStream.read()) != -1) { // 读取文件
					os.write(b);
				}
				os.flush(); // 关闭流
				inputStream.close();
				os.close();
				resultInfo.setMsg(pathname + fileName);
			} catch (Exception ex) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg(ex.getMessage());
			}
		} else {
			HashMap<String, Object> result = FtpUploadUtil.uploadFile(root + pathname, fileName, inputStream);
			resultInfo.setSuccess((boolean) result.get("success"));
			resultInfo.setMsg(String.valueOf(result.get("url")));
		}
		return resultInfo;
	}

	/**
	 * 自动生成目录文件夹
	 * 
	 * @param path
	 */
	public static void genFolder(String path) {
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}

	}
}
