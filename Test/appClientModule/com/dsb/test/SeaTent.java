package com.dsb.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class SeaTent {

	public static void main(String[] args) {
	    Map<String, Object> param = new HashMap<String, Object>();
	    
//        param.put("username", "17876433880");
//        param.put("password", DigestUtils.md5Hex("123456"));

        param.put("catId", "0");
        param.put("listShow", "1");
        param.put("accountId", "b013cd2fbdae485b9e18cb8398501d81");
        param.put("memberId", "b013cd2fbdae485b9e18cb8398501d81");
        param.put("token", "198f836235794fd2bce8d95e73e99c3a");
        
        System.out.println(getSign(param));
	}
	
    /**
     * 加密请求参数
     * @param param
     * @return
     */
    public static String getSign(Map<String, Object> param){
        param.put("appkey", "93029856");
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        param.put("timestamp", timestamp);
        // 把参数排序拼接
        String paramStr = param.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(entry -> entry.getKey() + "=" + encode(entry.getValue().toString())).collect(Collectors.joining( "&"));
        // 加密
        String secret = "71fb295a651044e288a9846854b8192e";
        return DigestUtils.sha1Hex(secret+paramStr+secret).toUpperCase();
    }

    /**
     * @Title encode 
     * @Description url转码
     * @param str
     * @return
     */
    public static String encode(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
