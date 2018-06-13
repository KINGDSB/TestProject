package com.dsb.util;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 来自翼博慧-xcb
 * spring低版本中可行
 * @author admin
 *
 */
public class FileUploadUtil {
	public static final List<String> ALLOW_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp");
	public static final List<String> ALLOW_VIEDO_TYPES = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp","image/gif","video/avi","video/avx","video/axv","video/.aiff","video/mov","video/mpeg","video/mpg","video/qt","video/ram","video/viv","video/mp4","video/rmvb");
	//允许上传文件类型
	public static final List<String> ALLOW_FILES_TYPES = Arrays.asList("pdf", "txt", "doc", "docx", "excel", "xls");
	
	
	// 文件重命名
	public static String rename(String fileName) {
		int i = fileName.lastIndexOf(".");
		String str = fileName.substring(i);
		return new Date().getTime() + "" + new Random().nextInt(99999999) + str;
	}

	// 校验文件类型是否是被允许的
	public static boolean allowUpload(String postfix) {
		return ALLOW_TYPES.contains(postfix);
	}
	
	// 校验文件类型是否是被允许的
	public static boolean allowUploadViedo(String postfix) {
		return ALLOW_VIEDO_TYPES.contains(postfix);
	}
	
	
	// 校验文件类型是否是被允许的
	public static boolean allowUploadFile(String postfix) {
		return ALLOW_FILES_TYPES.contains(postfix);
	}

	/**
	 * 删除文件、文件夹
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.isDirectory()) {
			File[] ff = file.listFiles();
			for (int i = 0; i < ff.length; i++) {
				deleteFile(ff[i].getPath());
			}
		}
		file.delete();

	}
	
	public static String uploadFile(MultipartFile multipartFile, HttpServletRequest request){
		
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String resourcePath = "/upload/image/";
		// 判断文件的MIMEtype
		String type = multipartFile.getContentType();
		if (type == null || !FileUploadUtil.allowUpload(type)) {
			return "10010";

		}
		System.out.println("file type:" + type);
		String fileName = FileUploadUtil.rename(multipartFile.getOriginalFilename());
		int end = fileName.lastIndexOf(".");
		String saveName = fileName.substring(0, end);
		try {
			File dir = new File(realPath + resourcePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, saveName + "_src.jpg");
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
		String srcImagePath = request.getSession().getServletContext().getContextPath() + resourcePath + saveName + "_src.jpg";
		
		return srcImagePath;
		
	}
	
	public static String uploadViedo(MultipartFile multipartFile, HttpServletRequest request){
		
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String resourcePath = "/upload/questionFile/";
		// 判断文件的MIMEtype
		String type = multipartFile.getContentType();
		if (type == null || !FileUploadUtil.allowUploadViedo(type)) {
			return "10011";

		}
		System.out.println("file type:" + type);
		String fileName = FileUploadUtil.rename(multipartFile.getOriginalFilename());
		int end = fileName.lastIndexOf(".");
		String saveName = fileName.substring(0, end);
		try {
			File dir = new File(realPath + resourcePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, saveName + "_src."+type.substring(6));
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
		String srcImagePath = request.getSession().getServletContext().getContextPath() + resourcePath + saveName + "_src."+type.substring(6);
		
		return srcImagePath;
		
	}
	
	public static String uploadSpecificFile(MultipartFile multipartFile, HttpServletRequest request){
		
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String resourcePath = "/upload/file/";
		// 判断文件的MIMEtype
		//String type = multipartFile.getContentType();
		String oriName = multipartFile.getOriginalFilename();
		if(null != oriName && !oriName.isEmpty()){
			oriName = oriName.substring(oriName.lastIndexOf(".") + 1, oriName.length());
		}
		/*if (type == null || !FileUploadUtil.allowUploadFile(type)) {
			return "10011";

		}*/
		/*if (oriName == null || !FileUploadUtil.allowUploadFile(type)) {
			return "10011";

		}*/
		//System.out.println("file type:" + type);
		if (oriName == null || !FileUploadUtil.allowUploadFile(oriName)) {
			return "";
		}
		
		String fileName = FileUploadUtil.rename(multipartFile.getOriginalFilename());
		int end = fileName.lastIndexOf(".");
		String saveName = fileName.substring(0, end);
		try {
			File dir = new File(realPath + resourcePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file = new File(dir, saveName + "_src." + oriName);
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
		String srcFilePath = request.getSession().getServletContext().getContextPath() + resourcePath + saveName + "_src." + oriName;
		
		return srcFilePath;
		
	}
	
}
