//package com.dsb.util;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.X509Certificate;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.AuthSchemes;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.lexin.base.common.constants.RedisKeyConstants;
//import com.lexin.base.common.utils.CacheUtil;
//import com.lexin.base.common.utils.ResultEnum;
//import com.lexin.mm.common.exception.MmException;
//import com.lexin.mm.seatent.constant.SeatentApis;
//import com.lexin.mm.seatent.dto.SeatentResultDTO;
//import com.lexin.mm.seatent.dto.UserInfoDTO;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 海带请求工具类
// * @author Created by DSB
// * @date 2019/7/3 9:39
// */
//@Slf4j
//@Component
//public class SeatentRequest {
//
//    private static final String CHARSET = "UTF-8";
//
//    /**
//     * baseUrl
//     */
//    @Value("${seatent.baseUrl}")
//    private String baseUrl;
//    /**
//     * appkey
//     */
//    @Value("${seatent.appkey}")
//    private String appkey;
//    /**
//     * secret
//     */
//    @Value("${seatent.secret}")
//    private String secret;
//    /**
//     * username
//     */
//    @Value("${seatent.username}")
//    private String username;
//    /**
//     * password
//     * 加密后的 DigestUtils.md5Hex(明文)
//     */
//    @Value("${seatent.password}")
//    private String password;
//    /**
//     * 用户信息缓存时间
//     */
//    @Value("${seatent.userInfoExpire}")
//    private Integer userInfoExpire;
//
//    Integer connectionRequestTimeout = 60000;
//    Integer connectTimeout = 60000;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * 获取用户信息
//     * @return
//     */
//    public UserInfoDTO getUserInfo() {
//        return CacheUtil.redisGetObject(RedisKeyConstants.REDIS_DATA_CENTER_SEATENT_USER_INFO, userInfoExpire, UserInfoDTO.class, stringRedisTemplate, tmp -> this.login());
//    }
//
//    /**
//     * get
//     * @param path
//     * @param headers
//     * @param param
//     * @return
//     * @throws Exception
//     */
//    public SeatentResultDTO get(String path, Map<String, String> headers, Map<String, Object> param) throws Exception {
//        HttpClient httpClient = wrapClient(baseUrl);
//        HttpGet request = new HttpGet(buildUrl(baseUrl + path, param, true));
//        if (null != headers) {
//            headers.forEach(request::addHeader);
//        }
//        request.setConfig(setTimeOutConfig(request.getConfig()));
//        HttpResponse httpResponse = httpClient.execute(request);
//        if (null == httpResponse || httpResponse.getStatusLine().getStatusCode() != 200) {
//            throw new MmException(ResultEnum.THIRD_REQUEST_ERROR);
//        }
//        SeatentResultDTO resultDTO = parseResult(httpResponse);
//        if (resultDTO.getCode().equals(103)) {
//            // 用户信息过期 清除用户信息缓存重试一次
//            stringRedisTemplate.delete(RedisKeyConstants.REDIS_DATA_CENTER_SEATENT_USER_INFO);
//            request = new HttpGet(buildUrl(baseUrl + path, param, true));
//            if (null != headers) {
//                headers.forEach(request::addHeader);
//            }
//            request.setConfig(setTimeOutConfig(request.getConfig()));
//            httpResponse = httpClient.execute(request);
//            if (null == httpResponse || httpResponse.getStatusLine().getStatusCode() != 200) {
//                throw new MmException(ResultEnum.THIRD_REQUEST_ERROR);
//            }
//            resultDTO = parseResult(httpResponse);
//        }
//        return resultDTO;
//    }
//
//    /**
//     * post
//     * @param path
//     * @param headers
//     * @param param
//     * @param body
//     * @return
//     * @throws Exception
//     */
//    public SeatentResultDTO post(String path, Map<String, String> headers, Map<String, Object> param, JSON body) throws Exception {
//        HttpClient httpClient = wrapClient(baseUrl);
//        HttpPost request = new HttpPost(buildUrl(baseUrl + path, param, true));
//        if (null != headers) {
//            headers.forEach(request::addHeader);
//        }
//        request.setConfig(setTimeOutConfig(request.getConfig()));
//        if (null != body) {
//            request.setEntity(new StringEntity(body.toJSONString(), CHARSET));
//        }
//        HttpResponse httpResponse = httpClient.execute(request);
//        if (null == httpResponse || httpResponse.getStatusLine().getStatusCode() != 200) {
//            throw new MmException(ResultEnum.THIRD_REQUEST_ERROR);
//        }
//        SeatentResultDTO resultDTO = parseResult(httpResponse);
//        if (resultDTO.getCode().equals(103)) {
//            // 用户信息过期 清除用户信息缓存重试一次
//            stringRedisTemplate.delete(RedisKeyConstants.REDIS_DATA_CENTER_SEATENT_USER_INFO);
//            request = new HttpPost(buildUrl(baseUrl + path, param, true));
//            if (null != headers) {
//                headers.forEach(request::addHeader);
//            }
//            request.setConfig(setTimeOutConfig(request.getConfig()));
//            httpResponse = httpClient.execute(request);
//            if (null == httpResponse || httpResponse.getStatusLine().getStatusCode() != 200) {
//                throw new MmException(ResultEnum.THIRD_REQUEST_ERROR);
//            }
//            resultDTO = parseResult(httpResponse);
//        }
//        return resultDTO;
//    }
//
//    /**
//     * login
//     * @return
//     * @throws Exception
//     */
//    private UserInfoDTO login() {
//        try {
//            Map<String, Object> param = new HashMap<>(4);
//            param.put("username", username);
//            param.put("password", password);
//
//            HttpClient httpClient = wrapClient(baseUrl);
//            HttpGet request = new HttpGet(buildUrl(baseUrl + SeatentApis.LOGIN_LOGIN, param, false));
//            request.setConfig(setTimeOutConfig(request.getConfig()));
//            HttpResponse httpResponse = httpClient.execute(request);
//            if (null == httpResponse || httpResponse.getStatusLine().getStatusCode() != 200) {
//                throw new MmException(ResultEnum.THIRD_REQUEST_ERROR);
//            }
//            return JSONObject.toJavaObject(parseResult(httpResponse).getData(), UserInfoDTO.class);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    /**
//     * 获取 HttpClient
//     * @param url
//     * @return
//     */
//    private static HttpClient wrapClient(String url) {
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        if (StringUtils.isBlank(url) && url.startsWith("https")) {
//            return sslClient();
//        }
//        return httpClient;
//    }
//
//    /**
//     * 在调用SSL之前需要重写验证方法，取消检测SSL
//     * 创建ConnectionManager，添加Connection配置信息
//     * @return HttpClient 支持https
//     */
//    private static HttpClient sslClient() {
//        try {
//            // 在调用SSL之前需要重写验证方法，取消检测SSL
//            X509TrustManager trustManager = new X509TrustManager() {
//                @Override public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
//                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
//            };
//            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
//            ctx.init(null, new TrustManager[] { trustManager }, null);
//            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
//            // 创建Registry
//            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
//                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
//                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.INSTANCE)
//                    .register("https",socketFactory).build();
//            // 创建ConnectionManager，添加Connection配置信息
//            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
//                    .setDefaultRequestConfig(requestConfig).build();
//            return closeableHttpClient;
//        } catch (KeyManagementException ex) {
//            throw new RuntimeException(ex);
//        } catch (NoSuchAlgorithmException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    /**
//     * 设置 连接超时、 请求超时 、 读取超时  毫秒
//     * @param requestConfig
//     * @return
//     */
//    private RequestConfig setTimeOutConfig(RequestConfig requestConfig){
//        if (null == requestConfig) {
//            requestConfig = RequestConfig.custom().build();
//        }
//        return RequestConfig.copy(requestConfig).setConnectionRequestTimeout(connectionRequestTimeout).setConnectTimeout(connectTimeout).setSocketTimeout(10000).build();
//    }
//
//    /**
//     * buildUrl
//     * @param url
//     * @param param
//     * @param needToken 是否需要传token 登录外都要传
//     * @return
//     */
//    private String buildUrl(String url, Map<String, Object> param, boolean needToken) {
//        if (null == param) {
//            param = new HashMap<>(10);
//        }
//        param.put("appkey", appkey);
//        param.put("timestamp", System.currentTimeMillis());
//        if (needToken) {
//            UserInfoDTO userInfo = getUserInfo();
//            param.put("accountId", userInfo.getAccountId());
//            param.put("memberId", userInfo.getMemberId());
//            param.put("token", userInfo.getToken());
//        }
//        String topSign = getSign(param);
//        param.put("topSign", topSign);
//        String paramStr = param.entrySet().stream().map(entry -> entry.getKey() + "=" + encode(String.valueOf(entry.getValue()))).collect(Collectors.joining( "&"));
//
//        return url + "?" + paramStr;
//    }
//
//    /**
//     * 加密请求参数
//     * @param param
//     * @return
//     */
//    private String getSign(Map<String, Object> param){
//        // 把参数排序拼接
//        String paramStr = param.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(entry -> entry.getKey() + "=" + encode(String.valueOf(entry.getValue()))).collect(Collectors.joining( "&"));
//        // 加密
//        return DigestUtils.sha1Hex(secret+paramStr+secret).toUpperCase();
//    }
//
//    /**
//     * 转换SeatentResultDTO
//     * @param httpResponse
//     * @return
//     */
//    private static SeatentResultDTO parseResult(HttpResponse httpResponse) {
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = in.readLine()) != null) {
//                sb.append(line);
//            }
//            String result = sb.toString();
//            log.debug("parseResult -> " + result);
//            return JSONObject.parseObject(result, SeatentResultDTO.class);
//        } catch (Exception ex) {
//            log.error("parseResultEx!", ex);
//            SeatentResultDTO resultDTO = new SeatentResultDTO();
//            resultDTO.setResult(0);
//            resultDTO.setMessage("转换SeatentResultDTO异常");
//            return resultDTO;
//        }
//    }
//
//    /**
//     * @Title encode
//     * @Description url转码
//     * @param str
//     * @return
//     */
//    private static String encode(String str) {
//        if (StringUtils.isNotBlank(str)) {
//            try {
//                return URLEncoder.encode(str, CHARSET);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return "";
//    }
//}
