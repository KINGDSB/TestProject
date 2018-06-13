package com.dsb.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SmsUtil {

	public static void main(String[] args) {
		String url = "http://120.55.197.77:1210/Services/MsgSend.asmx/SendMsg";

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userCode", "mkdzcf"));
		nvps.add(new BasicNameValuePair("userPass", "mkdzcf258"));
		// 验证码为+5个任意字符+*+【只定制】
		nvps.add(new BasicNameValuePair("Channel", "0"));

		nvps.add(new BasicNameValuePair("Msg", "验证码为12345_(:зゝ∠)_(╯‵□′)╯︵┻━┻【只定制】"));
		nvps.add(new BasicNameValuePair("DesNo", "17764026103"));
		String post1 = httpPost(url, nvps); // post请求

		nvps.add(new BasicNameValuePair("Msg", "验证码为123_(:зゝ∠)_【只定制】"));
		nvps.add(new BasicNameValuePair("DesNo", "13501935854"));
		String post2 = httpPost(url, nvps); // post请求
		
		// 【只定制】验证码为12345_(:зゝ∠)_(╯‵□′)╯︵┻━┻【只定制】,验证码为123_(:зゝ∠)_
		

//		String getparam = "userCode=用户名&userPass=密码&DesNo=手机号&Msg=短信内容【签名】&Channel=0";
//		String result = httpGet(url, getparam); // get请求
		
//		getReturnMsg("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<string xmlns=\"http://tempuri.org/\">2314819240824939514</string>");
		
	}

	public static String httpPost(String url, List<NameValuePair> params) {
		String result = "";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				result = convertStreamToString(instreams);
				System.out.println(result);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String httpGet(String url, String params) {
		String result = "";
		try {
			HttpClient client = new DefaultHttpClient();
			if (params != "") {
				url = url + "?" + params;
			}
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				result = convertStreamToString(instreams);
				System.out.println(result);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	private static String getReturnMsg(String xml){
		String rtnMsg = "";

		try {
			
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			rtnMsg = document.getStringValue();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rtnMsg;
	}
}