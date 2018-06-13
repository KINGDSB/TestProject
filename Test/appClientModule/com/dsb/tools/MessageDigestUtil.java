package com.dsb.tools;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * MD5生成代码处理 
 * http://blog.csdn.net/jerryvon/article/details/22602811
 **/

public class MessageDigestUtil {

	private final static String  MD5key = "abt80[Auj~2%6JV!jY~^c,_81qg>AWSh]$1jtpWwilK4L9OwDxPJn~Be.sLN8c3G"; //32位
	
	//byte字节转换成16进制的字符串MD5Utils.hexString
	private static String hexString(byte[] bytes){
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    private static byte[] MD5eccrypt(String info) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return resultBytes;
    }
    
    /**
     * ------------------前台-----------------
     * 前台逻辑:
     * 	报文A
     * 	1. 报文A+MD5的key =报文B
     * 	2. 报文B 转成BASE64 = 报文C
     * 	3. 报文C+MD5KEY 生成MD5 = 报文摘要D
     * 	4  http头加上报文摘要D, HTTP-BODY传 报文A
     * ------------------前台-----------------
     *  1. 报文摘要校验(报文A+MD5key) 
     *  2. 报文解密
     **/
	public static String MD5Encode(String plainText) throws Exception {
		plainText = plainText + MD5key;
		String info = Base64encode(plainText);
		byte[] resultBytes = MD5eccrypt(info);
		String sRtnValue = hexString(resultBytes);
		return sRtnValue;
	}
	
    //----------------------------------------------------------------------
    //          base64
    //----------------------------------------------------------------------

	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String Base64encode(String str) throws  Exception{
		String sOut = "";
		try {
	    	Base64 bs = new Base64();
	    	sOut = new String(bs.encode(str.getBytes("UTF-8")));
		} catch (Exception e) {
			throw e;
		}
		return sOut;
	}
	
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public static String Base64decode(String str) throws  Exception{
		String sOut = "";
		try {
	    	Base64 bs = new Base64();
	    	sOut = new String(bs.decode(str),"UTF-8");
		} catch (Exception e) {
			throw e;
		}
		return sOut;
	}
	
    //----------------------------------------------------------------------
    //          报文 加/解密
    //----------------------------------------------------------------------
	// 报文加密密钥
 	private final static String msgKey = "ze_@w.5{RqY2kM$Z7UG%X2kn!LRuX~O7";  //32位Key
 	// 报文加密向量
 	private final static String iv = "R2h~c#hi";   //8位向量
 	// 加解密统一使用的编码方式
 	private final static String encoding = "UTF-8";

 	/**
 	 * 3DES加密
 	 */
 	public static String doEncode(String plainText) throws Exception {
 		Key deskey = null;
 		DESedeKeySpec spec = new DESedeKeySpec(msgKey.getBytes(encoding));
 		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
 		deskey = keyfactory.generateSecret(spec);

 		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
 		IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
 		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
 		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
 		return Base64Util.encode(new String(encryptData));
 	}

 	/**
 	 * 3DES解密
 	 */
 	public static String doDecode(String encryptText) throws Exception {
 		Key deskey = null;
 		DESedeKeySpec spec = new DESedeKeySpec(msgKey.getBytes(encoding));
 		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
 		deskey = keyfactory.generateSecret(spec);
 		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
 		IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
 		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
 		String sText = Base64Util.decode(encryptText);
 		byte[] decryptData = cipher.doFinal(sText.getBytes(encoding));
 		return new String(decryptData, encoding);
 	}
    
    
    /**
     * 先BASE64转码-->再MD5转码
     * @param info
     * @return
     * @throws Exception
     */
//    public static String Encode(String info)throws Exception{
////    	String text = new String(info.toString().getBytes("UTF-8")); 
////    	System.out.println("text1="+text);
//    	String text = info;
//    	System.out.println("text2="+text);
////		String text = Base64Util.encode(text);
////		System.out.println("input="+text);
//		byte[] resultBytes = eccrypt(text);
//		String sRtnValue = hexString(resultBytes);
//		System.out.println("sRtnValue="+sRtnValue);
//		return  sRtnValue;
//    }
//    public static String Encode(String info)throws Exception{
////      info = new String(info.toString().getBytes("UTF-8"));
////  	String text = info + key;
//    	byte[] byte1 = info.getBytes();
//		System.out.println("1.before byte1.length =" + byte1.length);
//		System.out.println("2.before base64=" + info);
//		info = Base64Util.encode(info);
//		System.out.println("3.afte base64=" + info);
//		byte[] resultBytes = eccrypt(info);
//		String sRtnValue = hexString(resultBytes);
//		System.out.println("4.afte MD5=" + sRtnValue);
//		return sRtnValue;
//  }
 	//-----------------------------------------------------------------------------
 	//   报文压缩/解压
 	// http://www.cnblogs.com/gengaixue/archive/2013/09/04/3300658.html
 	//-----------------------------------------------------------------------------

// 	
//	/**
//	 * 使用gzip进行压缩
//	 */
//	public static String gzip(String primStr) {
//		if (primStr == null || primStr.length() == 0) {
//			return primStr;
//		}
//
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//		GZIPOutputStream gzip = null;
//		try {
//			gzip = new GZIPOutputStream(out);
//			gzip.write(primStr.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (gzip != null) {
//				try {
//					gzip.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return new sun.misc.BASE64Encoder().encode(out.toByteArray());
//	}
//	
//	/**
//	 * 使用gzip进行解压缩
//	 * @throws Exception 
//	 */
//	public static String gunzip(String compressedStr) throws Exception {
//		if (compressedStr == null) {
//			return null;
//		}
//
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ByteArrayInputStream in = null;
//		GZIPInputStream ginzip = null;
//		byte[] compressed = null;
//		String decompressed = null;
//		try {
//			compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
////			compressed = Base64Util.decode(compressedStr).getBytes();
//			in = new ByteArrayInputStream(compressed);
//			ginzip = new GZIPInputStream(in);
//
//			byte[] buffer = new byte[1024];
//			int offset = -1;
//			while ((offset = ginzip.read(buffer)) != -1) {
//				out.write(buffer, 0, offset);
//			}
//			decompressed = out.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (ginzip != null) {
//				try {
//					ginzip.close();
//				} catch (IOException e) {
//				}
//			}
//			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//				}
//			}
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//				}
//			}
//		}
//
//		return decompressed;
//	}
    
    
//	public static void main(String args[]) throws Exception{
//		String msg = "{\"BusiParams\":{\"content\":\"的\",\"deviceType\":\"1\",\"userSeqId\":\"1000000206\"},\"ServiceName\":\"sendNormalPost\"}";  
////		byte[] resultBytes = eccrypt(msg);  
////		System.out.println("密文是：" + hexString(resultBytes));  
//		System.out.println("--明文是：" + msg);  
//		String ss = MD5Encode(msg);
//		System.out.println("--密文是：" + ss);
//	}
	
//    public static String Encode(String info)throws Exception{
////      info = new String(info.toString().getBytes("UTF-8"));
////  	String text = info + key;
//		String text = Base64Util.encode(info);
////      byte[] resultBytes = eccrypt(info);
////      String sRtnValue = hexString(resultBytes);
//      return  text;
//  }
	
//    /**
//     * 签名字符串
//     * @param text 需要签名的字符串
//     * @param key 密钥
//     * @param input_charset 编码格式
//     * @return 签名结果
//     */
//    public static String sign(String text) {
//    	text = text + key;
//        return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
//    }
//    
//    /**
//     * @param content
//     * @param charset
//     * @return
//     * @throws SignatureException
//     * @throws UnsupportedEncodingException 
//     */
//    private static byte[] getContentBytes(String content, String charset) {
//        if (charset == null || "".equals(charset)) {
//            return content.getBytes();
//        }
//        try {
//            return content.getBytes(charset);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
//        }
//    }
	
//    /**
//     * 签名字符串
//     * @param text 需要签名的字符串
//     * @param key 密钥
//     * @param input_charset 编码格式
//     * @return 签名结果
//     */
//    public static String sign(String text, String key, String input_charset) {
//    	text = text + key;
//        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
//    }
    
    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
//    public static boolean verify(String text, String sign, String key, String input_charset) {
//    	String mysign  = sign(text, key, input_charset);
//    	if(mysign.equals(sign)) {
//    		return true;
//    	}
//    	else {
//    		return false;
//    	}
//    }
 	
 	
 	public static void main(String[] args) {
 		try {
			System.out.println(MD5Encode("{\"ServiceName\":\"getUserFriends\",\"BusiParams\":{\"userSeqId\":\"2000000252\",\"type\":\"2\",\"isContains\":\"0\"}}"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}