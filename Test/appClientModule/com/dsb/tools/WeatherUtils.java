package com.dsb.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.sf.json.JSONObject;

/**
 * 获取天气预报工具类
 * 
 * @author qw
 *
 */
public class WeatherUtils {

	static InputStream inStream;
	static Element root;

	public static void main(String[] args) {
		getWeather("广州");
	}

	/**
	 * 根据城市获取该城市天气状况
	 * 
	 * @param city（中文城市名）
	 * @return （返回字符串：‘白天：阴 最高温度：29℃ 夜间：多云 最低温度：21℃ '）
	 */
	public static String getWeather(String city) {
		String result = "";
		URL url = null;
		try {
			String cityEncode = URLEncoder.encode(city, "GBK");
			String link = "http://php.weather.sina.com.cn/xml.php?city=" + cityEncode
					+ "&password=DJOYnieT8234jlsK&day=0";
			url = new URL(link);
			String[] nodes = { "city", "status1", "temperature1", "status2", "temperature2" };
			getConnection(url);
			Map<String, String> map = getValue(nodes);

			result = "白天：" + map.get(nodes[1]) + "   最高温度：" + map.get(nodes[2]) + "℃    夜间：" + map.get(nodes[3])
					+ "   最低温度：" + map.get(nodes[4]) + "℃ ";
			System.out.println(result);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void getConnection(URL url) {

		InputStream in = null;

		try {

			in = url.openStream();

		} catch (IOException e1) {

			e1.printStackTrace();

		}

		if (in != null) {

			inStream = in;

			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();

			try {

				DocumentBuilder domBuilder = domfac.newDocumentBuilder();

				Document doc = domBuilder.parse(inStream);

				root = doc.getDocumentElement();
				// System.out.println(doc.getTextContent());
				// System.out.println(root.getTextContent());
				/*
				 * NodeList list =root.getChildNodes(); for(int i=0;
				 * i<root.getChildNodes().getLength(); i++){ Node n =
				 * list.item(i);
				 * System.out.println(n.getNodeName()+"-----"+n.getNodeValue());
				 * if(n.hasChildNodes()){
				 * System.out.println(i+"------------------------"); NodeList cn
				 * = n.getChildNodes(); for(int j =0 ;j<cn.getLength();j++){
				 * Node nn = cn.item(j);
				 * System.out.println(nn.getNodeName()+"--------====-----"+nn.
				 * getTextContent()); } } }
				 */

			} catch (ParserConfigurationException e) {

				e.printStackTrace();

			} catch (SAXException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

	/**
	 * 
	 * @param nodes
	 * @return 单个节点多个值以分号分隔
	 */
	public static Map<String, String> getValue(String[] nodes) {

		if (inStream == null || root == null) {

			return null;

		}

		Map<String, String> map = new HashMap<String, String>();

		// 初始化每个节点的值为null
		for (int i = 0; i < nodes.length; i++) {

			map.put(nodes[i], null);

		}

		// 遍历第一节点
		NodeList topNodes = root.getChildNodes();

		if (topNodes != null) {

			for (int i = 0; i < topNodes.getLength(); i++) {

				Node book = topNodes.item(i);

				if (book.getNodeType() == Node.ELEMENT_NODE) {

					for (int j = 0; j < nodes.length; j++) {

						for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {

							if (node.getNodeType() == Node.ELEMENT_NODE) {

								if (node.getNodeName().equals(nodes[j])) {

									String val = node.getTextContent();

									String temp = map.get(nodes[j]);

									if (temp != null && !temp.equals("")) {

										temp = temp + ";" + val;

									} else {

										temp = val;

									}

									map.put(nodes[j], temp);

								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	public Element getRoot() {
		return root;
	}

	public void setRoot(Element root) {
		this.root = root;
	}

	public InputStream getInStream() {
		return inStream;
	}

	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}

	/**
	 * 根据城市获取该城市天气状况
	 * 
	 * @param city（中文城市名）
	 * @return （返回字符串：‘当前天气：晴 30℃  最高温度：29℃   最低温度：21℃ '）
	 * @throws UnsupportedEncodingException
	 */
	public static String getWeather2(String city) throws UnsupportedEncodingException {

		String cityEncode = URLEncoder.encode(city, "GBK");

		String httpUrl = "http://apis.baidu.com/apistore/weatherservice/cityname";
		String httpArg = "cityname=" + cityEncode;

		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", "2e866f4b49f8db922708b4d26e794d65");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();

			JSONObject jsonObject = JSONObject.fromObject(result);
			
			String temp = "当前天气：" + jsonObject.getJSONObject("retData").getString("weather") + " " + jsonObject.getJSONObject("retData").getString("temp") + "℃ " + 
			"最高温度：" + jsonObject.getJSONObject("retData").getString("h_tmp") + "℃ "+
			"最低温度：" + jsonObject.getJSONObject("retData").getString("l_tmp") + "℃ ";
			
			result = temp;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
