package com.dsb.spiderdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 功能概要：爬虫工作类，主要实现类
 *
 * @author hwz
 */
public class SimpleSpider implements Runnable {

	private String threadName;

	private SpiderUrl url;

	private SpiderQueue workQueue;

	public SimpleSpider(SpiderQueue workQueue, String threadName) {
		this.workQueue = workQueue;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		System.out.println(threadName + " start run");
		// 连续空闲10次循环，结束任务
		int idle = 0;
		while (idle < 10) {
			url = workQueue.poll();
			if (url != null) {
				// url 解析
				parseUrl(url);
				idle = 0;
			} else {
				System.out.println(threadName + " idle...,times=" + idle++);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(threadName + " end run...");
	}

	/**
	 * url解析
	 * 
	 * @param url
	 * @return void
	 */
	private void parseUrl(SpiderUrl url) {
		if (url == null) {
			return;
		}
		try {
			int deep = url.getDeep() + 1;
			URL netUrl = new URL(url.getUrl());
			URLConnection connection = netUrl.openConnection();
			String contentType = connection.getContentType();
			// 获取内容
			String resource = getResource(connection);
			// 获取标题
			String title = getTitle(resource);
			// 获取链接
			List<String> urls = getUrls(resource);
			System.out.println(threadName + ",parseUrl url=" + url + ",contentType=" + contentType + ",title=" + title
					+ ",urls=" + urls);
			// 控制爬取链接层数，如果获取到的url全部加入工作队列，将会是指数级增加，最后程序挂掉
			if (deep < 3) {
				SpiderUrl newUrl;
				for (String u : urls) {
					newUrl = new SpiderUrl(u, deep);
					if (!workQueue.isExsit(newUrl)) {
						workQueue.add(newUrl);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取http url 内容
	 * 
	 * @param connection
	 * @return
	 * @return String
	 */
	private String getResource(URLConnection connection) {
		if (connection == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inputStream = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
			int input;
			while ((input = isr.read()) != -1) {
				sb.append((char) input);
			}
		} catch (IOException e) {
			System.out.println(threadName + ",get resource error,connection=" + connection);
		}
		return sb.toString();
	}

	/**
	 * 从url内容获取标题
	 * 
	 * @param content
	 * @return
	 * @return String
	 */
	private String getTitle(String content) {
		if (content == null) {
			return null;
		}
		Pattern pattern = Pattern.compile("(<title>.{1,}</title>)");
		Matcher matcher = pattern.matcher(content);
		String title = null;
		if (matcher.find()) {
			title = matcher.group(0).replaceAll("<title>", "").replaceAll("</title>", "");
		}
		return title;
	}

	/**
	 * 从url内容中获取存在的url链接
	 * 
	 * @param content
	 * @return
	 * @return List<String>
	 */
	private List<String> getUrls(String content) {
		if (content == null) {
			return null;
		}
		Pattern pattern = Pattern.compile("(<a.{1,}?href=['\"]?[a-zA-z]+:\\/\\/[^\\s]*?[\\s>]{1})");
		Matcher matcher = pattern.matcher(content);
		String a;
		String lastChar;
		List<String> links = new ArrayList<String>();
		while (matcher.find()) {
			a = matcher.group(0).replaceAll("<a.{1,}?href=['\"]?", "");
			a = a.trim();
			lastChar = a.substring(a.length() - 1);
			if (lastChar.equals("'") || lastChar.equals("\"") || lastChar.equals(">")) {
				a = a.substring(0, a.length() - 1);
			}
			links.add(a);
		}
		return links;
	}
}