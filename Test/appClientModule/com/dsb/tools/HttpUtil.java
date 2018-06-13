package com.dsb.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;


public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);
	private HttpClient client = null;

	public HttpUtil() {
		HttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = manager.getParams();
		params.setConnectionTimeout(20000);
		params.setSoTimeout(20000);
		params.setMaxTotalConnections(100);
		HttpClient client = new HttpClient(manager);
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		this.client = client;
	}

	public void setServer(String host, int port) {
		client.getHostConfiguration().setHost(host, port, "http");
	}

	private String execute(HttpMethod method, String charSet) throws Exception {
		String responseStr = null;
		try {
			client.executeMethod(method);
			if (method.getStatusCode() != HttpStatus.SC_OK) {
				log.warn("HTTP ����ʧ�ܣ�" + method.getStatusCode());
				throw new Exception("HTTP ����ʧ�ܣ�" + method.getResponseBodyAsString());
			}
			if (charSet != null) {
				responseStr = new String(method.getResponseBody(), charSet);
			} else {
				responseStr = method.getResponseBodyAsString();
			}
		} catch (Exception e) {
			log.error("http ����ʧ��", e);
			throw e;
		} finally {
			method.releaseConnection();
		}
		return responseStr;
	}

	/**
	 * �ύpost����
	 * 
	 * @param host
	 *            ����
	 * @param port
	 *            �˿ں�
	 * @param region
	 *            ��
	 * @param nameValPairContent
	 *            post�������
	 * @param charSet
	 *            ���ձ����ַ����룬����ʹ��Ĭ�ϱ���
	 * @return
	 * @throws Exception
	 */
	public String post(String host, int port, String region, Map<String, String> nameValPairContent, String charSet) throws Exception {
		setServer(host, port);
		PostMethod method = new PostMethod(region);
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		method.setRequestHeader("Connection", "close");
		List<NameValuePair> pairs = gerNamePairs(nameValPairContent);
		method.setRequestBody(pairs.toArray(new NameValuePair[0]));
		return execute(method, charSet);
	}

	/**
	 * httpUtil���鴦��
	 * 
	 * @param host
	 * @param port
	 * @param region
	 * @param pairs
	 * @param charSet
	 * @return
	 * @throws Exception
	 */
	public String post(String host, int port, String region, List<Map<String, String>> pairs, String charSet) throws Exception {
		setServer(host, port);
		PostMethod method = new PostMethod(region);
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
		method.setRequestHeader("Connection", "close");
		List<NameValuePair> params = gerNamePairs(pairs);
		method.setRequestBody(params.toArray(new NameValuePair[0]));
		return execute(method, charSet);
	}

	/**
	 * httpclient NameValuePair�������ɹ���
	 * 
	 * @param nameValPairContent
	 * @return
	 */
	private List<NameValuePair> gerNamePairs(Map<String, String> nameValPairContent) {
		Iterator<String> names = nameValPairContent.keySet().iterator();
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		while (names.hasNext()) {
			String name = names.next();
			NameValuePair pair = new NameValuePair();
			pair.setName(name);
			pair.setValue(nameValPairContent.get(name));
			pairs.add(pair);
		}
		return pairs;
	}

	/**
	 * httpclient NameValuePair�������ɹ���
	 * 
	 * @param nameValPairs
	 * @return
	 */
	private List<NameValuePair> gerNamePairs(List<Map<String, String>> nameValPairs) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (int i = 0; i < nameValPairs.size(); i++) {
			pairs.addAll(gerNamePairs(nameValPairs.get(i)));
		}
		return pairs;
	}

	// private List<org.apache.http.NameValuePair> gerHttpNamePairs(Map<String,
	// String> nameValPairContent) {
	// Iterator<String> names = nameValPairContent.keySet().iterator();
	// List<org.apache.http.NameValuePair> pairs = new
	// ArrayList<org.apache.http.NameValuePair>();
	// while (names.hasNext()) {
	// String name = names.next();
	// org.apache.http.NameValuePair pair = new BasicNameValuePair(name,
	// nameValPairContent.get(name));
	// pairs.add(pair);
	// }
	// return pairs;
	// }

	/**
	 * 
	 * @param host
	 * @param port
	 * @param region
	 * @param nameValPairContent
	 * @param respCharSet
	 * @param formEncode
	 * @return
	 * @throws Exception
	 */
	public String submitForm(String host, int port, String region, Map<String, String> nameValPairContent, String respCharSet, String formEncode) throws Exception {
		setServer(host, port);
		PostMethod method = new PostMethod(region);
		method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + formEncode);
		method.setRequestHeader("Connection", "close");
		List<NameValuePair> pairs = gerNamePairs(nameValPairContent);
		method.setRequestBody(pairs.toArray(new NameValuePair[0]));
		return execute(method, respCharSet);
	}

}
