package com.dsb.pay.wxpay.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.dsb.pay.wxpay.config.WxpayConfig;
import com.dsb.pay.wxpay.service.WxpayService;
import com.dsb.pay.wxpay.util.HttpUtil;
import com.dsb.pay.wxpay.util.PayCommonUtil;
import com.dsb.pay.wxpay.util.XMLUtil;

public class WxpayServiceImpl implements WxpayService {

	private Logger logger;

	public String weixin_pay() throws Exception {
		// 账号信息
		String appid = WxpayConfig.APP_ID; // appid
		// String appsecret = WxpayConfig.APP_SECRET; // appsecret
		String mch_id = WxpayConfig.MCH_ID; // 商业号
		String key = WxpayConfig.API_KEY; // key

		String currTime = PayCommonUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = PayCommonUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		String order_price = "1.00"; // 价格 注意：价格的单位是分
		String body = "goodssssss"; // 商品名称
		String out_trade_no = "11338"; // 订单号

		// 获取发起电脑 ip
		String spbill_create_ip = WxpayConfig.CREATE_IP;
		// 回调接口
		String notify_url = WxpayConfig.NOTIFY_URL;
		String trade_type = "NATIVE";

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
		packageParams.put("sign", sign);

		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		System.out.println(requestXML);

		String resXml = HttpUtil.postData(WxpayConfig.UFDODER_URL, requestXML);

		Map map = XMLUtil.doXMLParse(resXml);
		// String return_code = (String) map.get("return_code");
		// String prepay_id = (String) map.get("prepay_id");
		String urlCode = (String) map.get("code_url");

		return urlCode;
	}

	public void weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		String key = WxpayConfig.API_KEY; // key

//		logger.info(packageParams);
		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				////////// 执行自己的业务逻辑////////////////
				String mch_id = (String) packageParams.get("mch_id");
				String openid = (String) packageParams.get("openid");
				String is_subscribe = (String) packageParams.get("is_subscribe");
				String out_trade_no = (String) packageParams.get("out_trade_no");

				String total_fee = (String) packageParams.get("total_fee");

				logger.info("mch_id:" + mch_id);
				logger.info("openid:" + openid);
				logger.info("is_subscribe:" + is_subscribe);
				logger.info("out_trade_no:" + out_trade_no);
				logger.info("total_fee:" + total_fee);

				////////// 执行自己的业务逻辑////////////////

				logger.info("支付成功");
				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			logger.info("通知签名验证失败");
		}

	}
	
}
