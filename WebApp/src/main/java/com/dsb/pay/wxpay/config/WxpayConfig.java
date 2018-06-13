package com.dsb.pay.wxpay.config;

/**
 * 微信配置信息
 * @author admin
 *
 */
public class WxpayConfig {
	
	public static final String APP_ID = "wx67283e5ff6d99dcb";
	public static final String MCH_ID = "1233638302";
	// 微信的API密码是：Fuxiang5237Fuxiang5237Fuxiang523
	public static final String API_KEY = "Fuxiang5237Fuxiang5237Fuxiang523";
	
	// ↓↓↓↓↓↓↓ 非空
	public static final String CREATE_IP = "127.0.0.1";
	
	// 回调通知地址
	public static final String NOTIFY_URL = "http://商户网址/st-zdz/pay/wxpayNotify";
	public static final String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 
	
}
