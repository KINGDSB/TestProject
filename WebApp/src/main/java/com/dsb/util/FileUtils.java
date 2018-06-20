package com.dsb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    
    
    /**
     * 创建ZIP文件
     * 
     * @param sourcePath
     *            文件或文件夹路径
     * @param zipPath
     *            生成的zip文件存在路径（包括文件名）
     */
    public static void createZip(String sourcePath, String zipPath) {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
//            zos.setEncoding("gbk");// 此处修改字节码方式。
            // createXmlFile(sourcePath,"293.xml");
            writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.exists()) {
            if (file.isDirectory()) {
                // 处理文件夹
                parentPath += file.getName() + File.separator;
                File[] files = file.listFiles();
                if (files.length != 0) {
                    for (File f : files) {
                        writeZip(f, parentPath, zos);
                    }
                } else { 
                    // 空目录则创建当前目录
                    try {
                        zos.putNextEntry(new ZipEntry(parentPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte[] content = new byte[1024];
                    int len;
                    while ((len = fis.read(content)) != -1) {
                        zos.write(content, 0, len);
                        zos.flush();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        createZip("E:\\base\\devdoc\\common",
                "E:\\base\\devdoc\\common_" + new Date().getTime() + ".zip");
    }
    
    /**
     * 
     * @Title downloadMultiFiles 
     * @Description 打包下载多个文件
     * @param list
     * @param request
     * @param response
     */
    public static void downloadMultiFiles(List<Map<String,Object>> list, HttpServletRequest request, HttpServletResponse response) {
        if (null == list || list.size() == 0) {
            return;
        }
        
        try {
            String fileName = "陈列图片.zip";// 文件的名称
            fileName = URLEncoder.encode(fileName + ".zip", "UTF-8");// 转换中文否则可能会产生乱码
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 设置在下载框默认显示的文件名
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

            for (int i = 0, len = list.size(); i < len; i++) {
                String cfileName = list.get(i).get("fileName").toString();
                List<String> files = Arrays.asList(list.get(i).get("files").toString().split(","));
                for (int j = 0, jlen = files.size(); j < jlen; j++) {
                    URL url = new URL(files.get(j));
                    zos.putNextEntry(new ZipEntry(i + 1 + "." + cfileName + File.separator + j + ".jpg"));
                    InputStream fis = url.openConnection().getInputStream();
                    byte[] buffer = new byte[1024];
                    int r = 0;
                    while ((r = fis.read(buffer)) != -1) {
                        zos.write(buffer, 0, r);
                    }
                    fis.close();
                }
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
