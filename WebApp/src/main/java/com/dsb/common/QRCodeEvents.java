package com.dsb.common;

import java.io.File;
import java.util.Hashtable;

import com.dsb.util.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeEvents {

	public static void main(String[] args) throws Exception {
		String text = "你好";
		int width = 100;
		int height = 100;
		String format = "png";
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		File outputFile = new File("D://new.png");

		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		
//		ServletOutputStream stream = resp.getOutputStream();
//		MatrixToImageWriter.writeToStream(matrix, format, stream);

	}
	
	/**
	
	servlet
	
	String keycode = req.getParameter(KEY);
		
		if (keycode != null && !"".equals(keycode)) {
			ServletOutputStream stream = null;
			try {
				int size=129;
				String msize = req.getParameter(SIZE);
				if (msize != null && !"".equals(msize.trim())) {
					try{
						size=Integer.valueOf(msize);
					} catch (NumberFormatException e) {
						//TODO output to log
					}
				}
				stream = resp.getOutputStream();
				QRCodeWriter writer = new QRCodeWriter();
				BitMatrix m = writer.encode(keycode, BarcodeFormat.QR_CODE, size, size);
				MatrixToImageWriter.writeToStream(m, IMAGETYPE, stream);
			} catch (WriterException e) {
				e.printStackTrace();
			} finally {
				if (stream != null) {
					stream.flush();
					stream.close();
				}
			}
		}
	
	 */
}