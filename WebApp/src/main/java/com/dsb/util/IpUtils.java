package com.dsb.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * 获取外网IP
 * @author Join
 *
 */
public class IpUtils {

	/**
	 * 获取外网的IP(要访问Url，要放到后台线程里处理)
	 * @return
	 */
	public static String  getNetIp(){
		String ip = "";
		String chinaz = "http://ip.chinaz.com";
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
		    in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			while((read=in.readLine())!=null){
				inputLine.append(read+"\r\n");
			}
			//System.out.println(inputLine.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if(m.find()){
			String ipstr = m.group(1);
			ip = ipstr;
			System.out.println(ipstr);
		}
		return ip;
	}

	/**
	 * 获取本地IP
	 * @param request
	 * @return
	 */
	public static String getLocalIP(HttpServletRequest request){
		return request.getLocalAddr();
	}
	
	
	/**
	 * 获取本地服务器端口号
	 * @param request
	 * @return
	 */
	public static Integer getLocalPort(HttpServletRequest request){
		return request.getLocalPort();
	}
	
	/**
	 * 获取客户端ip地址和端口号
	 * @param request
	 * @return
	 */
	public static String getClientIPAndProt(HttpServletRequest request){
		
		return "http://"+request.getRemoteAddr()+":"+request.getRemotePort();
	}
	
	/**
	 * @Title getIp 
	 * @Description 获取请求ip地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
