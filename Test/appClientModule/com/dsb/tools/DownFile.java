package com.dsb.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownFile {

	/**
	 * 远程下载文件并读取返回
	 * 
	 * @param filePath
	 * 		文件网络地址 如http://www.baidu.com/1.txt
	 * @return String
	 */

	public static File downloadFile(String filePath) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// File savePath = new File("D:"+File.separator+date);//创建新文件
		// File savePath = new
		// File("c:"+File.separator+"tmp"+File.separator+date);//创建新文件
		File savePath = new File(File.separator + "tmp" + File.separator + date);// 创建新文件

		// log.info("savePath:"+savePath);// linux c:/tmp/2016-05-06
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		String[] urlname = filePath.split("/");
		int len = urlname.length - 1;
		String uname = urlname[len];// 获取文件名
		File file = null;
		try {
			file = new File(savePath + File.separator + uname);// 创建新文件

			if (file != null && !file.exists()) {
				file.createNewFile();
			}
			OutputStream oputstream = new FileOutputStream(file);
			URL url = new URL(filePath);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);// 设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();
			System.out.println("file size is:" + uc.getContentLength());// 打印文件长度
			byte[] buffer = new byte[4 * 1024];
			int byteRead = -1;
			while ((byteRead = (iputstream.read(buffer))) != -1) {
				oputstream.write(buffer, 0, byteRead);
			}
			oputstream.flush();
			iputstream.close();
			oputstream.close();

			return file;

		} catch (Exception e) {
			System.out.println("读取失败！");
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 上传文件到远程服务器 有samba,ftp
	 * */
	public static String uploadFile(String file) {
		return null;
	}

	public static void main(String[] args) throws Exception {

		// DownloadFile("http://120.92.13.17/6/2016041921/d033e13f-a6f7-460a-b25b-03265e11b06e.zip");
		// DownloadFile("http://rongcloud-image.ronghub.com/image_jpeg__RC-2016-04-18_721_1460945570?e=2147483647&token=CddrKW5AbOMQaDRwc3ReDNvo3-sL_SO1fSUBKV3H:op3atNReFe1tNCHCuFIfNz6IOGQ=");

		/**
		 * 图片下载
		 * */
		download("http://rongcloud-image.ronghub.com/image_jpeg__RC-2016-04-18_721_1460945570?e=2147483647&token=CddrKW5AbOMQaDRwc3ReDNvo3-sL_SO1fSUBKV3H:op3atNReFe1tNCHCuFIfNz6IOGQ=","51bi.gif", "c:\\image\\");

	}

	/**
	 * 图片下载 直接写在本地
	 * 
	 * */
	public static void download(String urlString, String filename,String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();

		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

}
