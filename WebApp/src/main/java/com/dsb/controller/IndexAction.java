package com.dsb.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dsb.common.ApiResult;
import com.dsb.util.EhCacheService;
import com.dsb.util.EhcacheUtil;
import com.dsb.util.FileUploadUtil;

@Controller
@RequestMapping(value="/home")
public class IndexAction {

	@Autowired
	private EhCacheService ehCacheService;

	@RequestMapping(value="/getData")
	public String getData(Model model) throws InterruptedException{
		model.addAttribute("word0", "hahahahahahha\n这里是word0 ");
		return "data";
	}
	
	@RequestMapping(value="/testEhcache")
	public String testEhcache() throws InterruptedException{
		
		System.out.println("第一次调用：" + ehCacheService.getTimestamp("param"));
		Thread.sleep(2000);
		System.out.println("2秒之后调用：" + ehCacheService.getTimestamp("param"));
		Thread.sleep(11000);
		System.out.println("再过11秒之后调用：" + ehCacheService.getTimestamp("param"));
		
		return null;
	}
	
	@RequestMapping(value="/ehcachePut")
	public String ehcachePut() throws InterruptedException{
		
		EhcacheUtil ehcahe = EhcacheUtil.getInstance();
		ehcahe.put("cacheTest", "userId", "token");
		
		return null;
	}
	
	@RequestMapping(value="/ehcacheOut")
	public String ehcacheOut() throws InterruptedException{

		EhcacheUtil ehcahe = EhcacheUtil.getInstance();
		System.out.println(ehcahe.get("cacheTest", "userId"));
		
		return null;
	}

	/**
	 * 测试接口
	 * 
	 * @param jsonObject
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/testInterface", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getNewTargetList(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		ApiResult result = new ApiResult();
		
		result.setSuccess();
		
		result.addReturnInfo("key1", "value1");
		
		JSONArray list = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("obj1key1", "obj1value1");
		list.add(obj1);
		result.setReturnListInfo(list);
		
		return result.getJsonResult();
	}
	
	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("/appFileUpload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request) {
//		ApiResult result = new ApiResult();
		
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String path = "";
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String resourcePath = "/upload/file/";
		String saveName = "";
		String suffer = "";
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				String fileName = FileUploadUtil.rename(file.getOriginalFilename());
				int end = fileName.lastIndexOf(".");
				saveName = fileName.substring(0, end);
				suffer = fileName.substring(end, fileName.length());
				// 上传
				try {
					File dir = new File(realPath + resourcePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					File file1 = new File(dir, saveName + "_task" + suffer);
					file.transferTo(file1);
				} catch (Exception e) {
					e.printStackTrace();
//					result.setReturnCode(ApiResultCode.RETURNCODE_SYSTEM_ERROR, ApiResultCode.RETURNMSG_SYSTEM_ERROR);
//					return result.getJsonResult();
				}

			}

		} else {
//			result.setReturnCode(ApiResultCode.RETURNCODE_BUSINESS_ERROR, "上传文件类型不符合");
		}

		String url = request.getSession().getServletContext().getContextPath() + resourcePath + saveName + "_task" + suffer;
		
//		result.setSuccess();
//		result.addReturnInfo("fileUrl", url);
//		return result.getJsonResult();
		return "";
	}
}
