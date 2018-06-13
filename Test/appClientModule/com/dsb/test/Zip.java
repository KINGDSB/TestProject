package com.dsb.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zip {
	
	public boolean test(){
//		// long starTime = System.currentTimeMillis();
//		try {
//			File file = new File(ZipFile);
//			File[] fileZip = file.listFiles();
//			for (int i = 0; i < fileZip.length; i++) {
//				ZipInputStream Zip = new ZipInputStream(new FileInputStream(
//						fileZip[i]));
//				BufferedInputStream buff = new BufferedInputStream(Zip);
//				String parent = descDir;
//				File font = null;
//				ZipEntry entry;
//				try {
//					while ((entry = Zip.getNextEntry()) != null
//							&& !entry.isDirectory()) {
//						font = new File(parent, entry.getName());
//						if (!font.exists()) {
//							new File(font.getParent()).mkdirs();
//						}
//						FileOutputStream out = new FileOutputStream(font);
//						BufferedOutputStream bout = new BufferedOutputStream(
//								out);
//						int b;
//						while ((b = buff.read()) != -1) {
//							bout.write(b);
//						}
//						bout.close();
//						out.close();
//					}
//					buff.close();
//					Zip.close();
//					fileZip[i].delete();
//				} catch (IOException e) {
//					// TODO: handle exception
//					log.error("解压缩失败");
//					return false;
//				}
//			}
//			return true;
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			
			return false;
//		}
//		// long endTime=System.currentTimeMillis();
	}
}
