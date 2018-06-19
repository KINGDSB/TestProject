package com.dsb.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: FileUtils.java
 * @Package com.navidata.util
 * @Description: 文件工具类
 * @author dsb
 * @date 2018年6月19日 下午3:13:14
 */
public class FileUtils {

    /**
     * @Title download 
     * @Description 把图片打包下载
     * @param request
     * @param response
     */
    public static void download(HttpServletRequest request, HttpServletResponse response) {

        try {
            String downloadFilename = "中文.zip";// 文件的名称
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");// 转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            String[] files = new String[] { "http://lxfile.honey-lovely.com/operative/jpg/20180616_5b24e31f5584f.jpg", "http://lxfile.honey-lovely.com/operative/jpg/20180616_5b24e332a9627.jpg" };
            for (int i = 0; i < files.length; i++) {
                URL url = new URL(files[i]);
                zos.putNextEntry(new ZipEntry(i + ".jpg"));
                // FileInputStream fis = new FileInputStream(new
                // File(files[i]));
                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
