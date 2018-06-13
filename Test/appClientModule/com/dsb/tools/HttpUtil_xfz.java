package com.dsb.tools;
//package tools;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 
// * http
// * 
// * @author xfz
// *
// */
//public class HttpUtil_xfz {
//	protected static Logger logger = LoggerFactory.getLogger(HttpUtil_xfz.class);
//
//	public static final Map<String, String> httpHeader = new HashMap<String, String>();
//	static {
//		httpHeader.put("Content-Type", "application/json; charset=UTF-8");
//		httpHeader.put("accept", "application/json; charset=UTF-8");
//
//		httpHeader.put("messageDigest", "NOAUTH");
//		httpHeader.put("authorityType", "NOAUTH");
//	}
//
//	public static String getStreamString(InputStream is, String inCharset) throws Exception {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
//			HttpUtil_xfz.writeFromInpuStreamToOutputStream(null, is, baos);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw ex;
//		}
//		return baos.toString(inCharset);
//	}
//
//	@SuppressWarnings("rawtypes")
//	public static String post(String address, String src, Map<String, String> httpHeader) throws IOException {
//		InputStream is = null;
//		OutputStream os = null;
//		String result = null;
//		StringBuffer header = new StringBuffer();
//		try {
//			URL url = new URL(address);
//			String outCharset = "UTF-8";
//			String inCharset = "UTF-8";
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			// 设置cookie
//			// conn.setRequestProperty("cookie",
//			// "JSESSIONID=C2C922CC5967F57A628D4D4E5F55FCD0");
//			if (httpHeader != null && httpHeader.size() > 0) {
//				Iterator it = httpHeader.keySet().iterator();
//				while (it.hasNext()) {
//					String key = (String) it.next();
//					String value = httpHeader.get(key);
//					conn.setRequestProperty(key, value);
//
//					header.append(key).append("=").append("[" + value + "]").append(",");
//				}
//			}
//
//			// conn
//			// 设置User-Agent，防止有些网站或Web服务器不支持
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; CIBA)");
//			// 设置超时时间
//			conn.setConnectTimeout(92000);
//			conn.setReadTimeout(92000);
//
//			// 设置连接既可以写入也可以读取（post）
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//
//			// 开始写入数据
//			os = conn.getOutputStream();
//			os.write(src.getBytes(outCharset));
//			os.flush();
//			// 开始读取数据
//			is = conn.getInputStream();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			HttpUtil_xfz.writeFromInpuStreamToOutputStream(null, is, baos);
//			is.close();
//			result = baos.toString(inCharset);
//			return result;
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (Exception ex) {
//
//				}
//			}
//			if (os != null) {
//				try {
//					os.close();
//				} catch (Exception ex) {
//
//				}
//			}
//
//			System.out.println("--------------Inbound  Message--------------");
//			System.out.println("ID: 1");
//			System.out.println("Address: " + address);
////			System.out.println("ServiceName: " + JSON.parseObject(src, BaseReq.class).getServiceName());
//			System.out.println("Http-Method: POST");
//			System.out.println("Headers: " + header.toString().substring(0, header.toString().length() - 1));
//			System.out.println("Payload: " + src);
//
//			System.out.println("\n--------------Outbound Message--------------");
//			System.out.println("ID: 1");
//			System.out.println("Response-Code: 200 ");
//			System.out.println("Headers: " + header.toString().substring(0, header.toString().length() - 1));
//			System.out.println("Payload: " + result);
//
//			// logger.debug("post() \n链接：{}\n参数：{}\n 响应：{}", new
//			// Object[]{address, src, result});
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	public static HttpResult doPost(String address, String data, Map<String, String> httpHeader) {
//		OutputStreamWriter wr = null;
//		InputStream is = null;
//		HttpResult result = new HttpResult();
//		try {
//			URL url = new URL(address);
//			data = data == null ? "" : data;
//			String outCharset = "UTF-8";
//			String inCharset = "UTF-8";
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			// 设置cookie
//			// conn.setRequestProperty("cookie",
//			// "JSESSIONID=C2C922CC5967F57A628D4D4E5F55FCD0");
//			if (httpHeader != null && httpHeader.size() > 0) {
//				Iterator it = httpHeader.keySet().iterator();
//				while (it.hasNext()) {
//					String key = (String) it.next();
//					String value = httpHeader.get(key);
//					conn.setRequestProperty(key, value);
//				}
//			}
//			// conn
//			// 设置User-Agent，防止有些网站或Web服务器不支持
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; CIBA)");
//			// 设置超时时间
//			conn.setConnectTimeout(12000);
//			conn.setReadTimeout(12000);
//			// conn.setRequestMethod("GET");
//			// 设置连接既可以写入也可以读取（post）
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//
//			// 开始写入数据
//			wr = new OutputStreamWriter(conn.getOutputStream(), outCharset);
//			wr.write(data);
//			wr.flush();
//
//			// 开始读取数据
//			is = conn.getInputStream();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			HttpUtil_xfz.writeFromInpuStreamToOutputStream(null, is, baos);
//			String responseCookie = conn.getHeaderField("Set-Cookie");// 取到所用的Cookie
//			result.setCookie(responseCookie);
//			result.setResponse(baos.toString(inCharset));
//			return result;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("通过POST读取http出错:" + address, ex);
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (Exception ex) {
//
//				}
//			}
//			if (wr != null) {
//				try {
//					wr.close();
//				} catch (Exception ex) {
//
//				}
//			}
//			logger.debug("doPost()，链接：{}\n参数：{}\n 响应：{}", new Object[] { address, data, result.getResponse() });
//		}
//	}
//
//	public static HttpResult doGet(String address, Map<String, String> httpHeader, Map<String, Object> params) {
//		StringBuffer param = new StringBuffer();
//		int i = 0;
//		for (String key : params.keySet()) {
//			if (i == 0)
//				param.append("?");
//			else
//				param.append("&");
//			param.append(key).append("=").append(params.get(key));
//			i++;
//		}
//		address += param.toString();
//		return doGet(address, httpHeader);
//	}
//
//	@SuppressWarnings("rawtypes")
//	public static HttpResult doGet(String address, Map<String, String> httpHeader) {
//		InputStream is = null;
//		HttpResult result = new HttpResult();
//		try {
//			URL url = new URL(address);
//			// String outCharset = "UTF-8";
//			String inCharset = "GB2312";
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			if (httpHeader != null && httpHeader.size() > 0) {
//				Iterator it = httpHeader.keySet().iterator();
//				while (it.hasNext()) {
//					String key = (String) it.next();
//					String value = httpHeader.get(key);
//					conn.setRequestProperty(key, value);
//				}
//			}
//			// conn
//			// 设置User-Agent，防止有些网站或Web服务器不支持
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; CIBA)");
//			// 设置超时时间
//			conn.setConnectTimeout(12000);
//			conn.setReadTimeout(12000);
//			conn.setRequestMethod("GET");
//			// 开始读取数据
//			is = conn.getInputStream();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			HttpUtil_xfz.writeFromInpuStreamToOutputStream(null, is, baos);
//			String responseCookie = conn.getHeaderField("Set-Cookie");// 取到所用的Cookie
//			result.setCookie(responseCookie);
//			result.setResponse(baos.toString(inCharset));
//			return result;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("通过GET读取http出错:" + address, ex);
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (Exception ex) {
//
//				}
//			}
//
//			logger.debug("doGet()，链接：{}\n 响应：{}", new Object[] { address, result.getResponse() });
//		}
//	}
//
//	public static long writeFromInpuStreamToOutputStream(byte[] buffer, InputStream is, OutputStream os) {
//		long count = 0;
//		if (buffer == null) {
//			buffer = new byte[1024];
//		}
//		try {
//			while (true) {
//				int length = is.read(buffer);
//				if (length == 0)
//					continue;
//				if (length == -1)
//					break;
//				os.write(buffer, 0, length);
//				count += length;
//			}
//		} catch (IOException e) {
//			throw new RuntimeException("读流出错", e);
//		}
//		return count;
//	}
//
//	public static class FileData {
//		private String formName;
//		private String fileName;
//		private String contentType;
//		private File file;
//
//		public String getFormName() {
//			return formName;
//		}
//
//		public void setFormName(String formName) {
//			this.formName = formName;
//		}
//
//		public String getFileName() {
//			return fileName;
//		}
//
//		public void setFileName(String fileName) {
//			this.fileName = fileName;
//		}
//
//		public String getContentType() {
//			return contentType;
//		}
//
//		public void setContentType(String contentType) {
//			this.contentType = contentType;
//		}
//
//		public File getFile() {
//			return file;
//		}
//
//		public void setFile(File file) {
//			this.file = file;
//		}
//
//	}
//
//	public static class HttpResult {
//		private String response;
//		private String cookie;
//
//		public String getResponse() {
//			return response;
//		}
//
//		public void setResponse(String response) {
//			this.response = response;
//		}
//
//		public String getCookie() {
//			return cookie;
//		}
//
//		public void setCookie(String cookie) {
//			this.cookie = cookie;
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//
//		Map<String, String> httpHeader = new HashMap<String, String>();
//		// httpHeader.put("Content-Type", "application/xml");
//		httpHeader.put("Content-Type", "application/json; charset=UTF-8");
//		httpHeader.put("accept", "application/json; charset=UTF-8");
//		httpHeader.put("X-AUTH-HEADER", "D81F1EA5AFE8264FACD9A5FF77B25C63");
//		String json = "{\"pageNum\":1,\"pageSize\":20,\"orderColumn\":\"\",\"isDesc\":false,\"mebId\":73184925}";
//		String str = HttpUtil_xfz.post("http://10.100.112.123:8090/coupon-services/rest/couponServices/queryCoupons", json,
//				httpHeader);
//		System.out.println(str);
//
//	}
//
//}
