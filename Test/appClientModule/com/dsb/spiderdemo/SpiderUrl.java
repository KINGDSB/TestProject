package com.dsb.spiderdemo;

/**
 * 
 * 功能概要：爬虫工作的url
 *
 * @author hwz
 */
public class SpiderUrl {

	/** http(s) url */
	private String url;

	/** 该url是入口url的第几层 */
	private int deep;

	public SpiderUrl(String url, int deep) {
		this.url = url;
		this.deep = deep;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDeep() {
		return deep;
	}

	public void setDeep(int deep) {
		this.deep = deep;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SpiderUrl)) {
			return false;
		}
		SpiderUrl oth = (SpiderUrl) obj;
		return this.url.equals(oth.getUrl());
	}

	@Override
	public int hashCode() {
		return url.hashCode();
	}

	@Override
	public String toString() {
		return getClass().toString() + "[url:" + url + ",deep:" + deep + "]";
	}
}