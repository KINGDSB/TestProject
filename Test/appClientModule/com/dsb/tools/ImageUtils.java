package com.dsb.tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import org.w3c.dom.Element;  


public class ImageUtils {
	// 缩略图
	public static void Thumbnail(String infile, String outfile, int width,
			int height, int quality) throws IOException, InterruptedException {

		BufferedImage thumbImage = null;
		FileOutputStream out = null;
		Image image = null;
		image = Toolkit.getDefaultToolkit().createImage(infile);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);
		int thumbWidth = width;
		int thumbHeight = height;
		double thumbRatio = (double) thumbWidth / (double) thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double) imageWidth / (double) imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int) (thumbWidth / imageRatio);
		} else {
			thumbWidth = (int) (thumbHeight * imageRatio);
		}
		thumbImage = new BufferedImage(thumbWidth, thumbHeight,
				BufferedImage.SCALE_SMOOTH);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
		out = new FileOutputStream(outfile);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
//		quality = Math.max(0, Math.min(quality, 100));
//		param.setQuality((float) quality / 100.0f, false);
//		encoder.setJPEGEncodeParam(param);
//		encoder.encode(thumbImage);
		
		saveAsJPEG(100, thumbImage, 100.0f, out);
		
		out.close();
		thumbImage = null;
		out = null;
		image = null;
	}
	
	/** 
     * 以JPEG编码保存图片 
     * @param dpi  分辨率 
     * @param image_to_save  要处理的图像图片 
     * @param JPEGcompression  压缩比 
     * @param fos 文件输出流 
     * @throws IOException 
     */  
    public static void saveAsJPEG(Integer dpi ,BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {  
            
        //useful documentation at http://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/jpeg_metadata.html  
        //useful example program at http://johnbokma.com/java/obtaining-image-metadata.html to output JPEG data  
        
        //old jpeg class  
        //com.sun.image.codec.jpeg.JPEGImageEncoder jpegEncoder  =  com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(fos);  
        //com.sun.image.codec.jpeg.JPEGEncodeParam jpegEncodeParam  =  jpegEncoder.getDefaultJPEGEncodeParam(image_to_save);  
        
        // Image writer  
//      JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();  
        ImageWriter imageWriter  =   ImageIO.getImageWritersBySuffix("jpg").next();  
        ImageOutputStream ios  =  ImageIO.createImageOutputStream(fos);  
        imageWriter.setOutput(ios);  
        //and metadata  
        IIOMetadata imageMetaData  =  imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);  
           
           
        if(dpi !=  null && !dpi.equals("")){  
               
             //old metadata  
            //jpegEncodeParam.setDensityUnit(com.sun.image.codec.jpeg.JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);  
            //jpegEncodeParam.setXDensity(dpi);  
            //jpegEncodeParam.setYDensity(dpi);  
        
            //new metadata  
            Element tree  =  (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");  
            Element jfif  =  (Element)tree.getElementsByTagName("app0JFIF").item(0);  
            jfif.setAttribute("Xdensity", Integer.toString(dpi) );  
            jfif.setAttribute("Ydensity", Integer.toString(dpi));  
               
        }  
        
        
        if(JPEGcompression >= 0 && JPEGcompression <= 1f){  
        
            //old compression  
            //jpegEncodeParam.setQuality(JPEGcompression,false);  
        
            // new Compression  
            JPEGImageWriteParam jpegParams  =  (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();  
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);  
            jpegParams.setCompressionQuality(JPEGcompression);  
        
        }  
        
        //old write and clean  
        //jpegEncoder.encode(image_to_save, jpegEncodeParam);  
        
        //new Write and clean up  
        imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);  
        ios.close();  
        imageWriter.dispose();  
        
    }  

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 * @param width
	 *            宽度(与左相比)
	 * @param height
	 *            高度(与顶相比)
	 * @param clarity
	 *            透明度(小于1的数)越接近0越透明
	 */
	public static void waterMarkImageByIcon(String iconPath, String srcImgPath,
			String targerPath, Integer degree, Integer width, Integer height,
			float clarity) {
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = clarity; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 表示水印图片的位置
			g.drawImage(img, width, height, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				
			}
		}
	}

	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param logoText
	 *            水印文字
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 * @param width
	 *            宽度(与左相比)
	 * @param height
	 *            高度(与顶相比)
	 * @param clarity
	 *            透明度(小于1的数)越接近0越透明
	 */
	public static void waterMarkByText(String logoText, String srcImgPath,
			String targerPath, Integer degree, Integer width, Integer height,
			Float clarity) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}
			// 设置颜色
			g.setColor(Color.red);
			// 设置 Font
			g.setFont(new Font("宋体", Font.BOLD, 30));
			float alpha = clarity;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(logoText, width, height);
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				
			}
		}
	}

	public static void main(String[] args) {
		String iconPath = "d:/22.jpg";
		String srcImgPath = "d:\\2.jpg";
		String targerPath = "d:/3.jpg";
		// 给图片添加水印
		ImageUtils.waterMarkImageByIcon(iconPath, srcImgPath, targerPath, 0, 0,
				0, 1.0f);
		// 给图片添加水印,水印旋转-45
		// ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath,
		// targerPath2, -45);
	}
}
