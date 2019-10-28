package com.dsb.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Download {
	
	public static void main(String[] args) {
		/*
        try {
        	FileOutputStream fileOutputStream = new FileOutputStream(new File("F:/tmp.zip"));     
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());     
            ZipOutputStream zos = new ZipOutputStream(cos);

        	int num = 700000000;
            for (int i = 0, len = 61; i < len; i++) {
            	
                URL url = new URL("http://youku.com-q-youku.com/20190620/1361_202fd2ad/1000k/hls/c0e1d85d"+(num+i)+".ts");
                zos.putNextEntry(new ZipEntry(num+i + ".ts"));
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
        */
		System.out.println(System.currentTimeMillis());
	}

}
