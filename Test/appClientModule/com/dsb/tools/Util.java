package com.dsb.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Util {

	public static SimpleDateFormat DATE_TIME_MS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	public static SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	public static SimpleDateFormat MONTH = new SimpleDateFormat("MM");
	
	/**
	 * 得到当前系统的时间
	 * 
	 * @return 返回字符串 格式 yyyy-MM-dd HH:mm:ss SSS
	 */
	public static String getTableTS() {
		return getDateTimeMs(new Date(System.currentTimeMillis()), Util.DATE_TIME_MS);
	}

	/**
	 * 返回带毫秒的日期时间字符串
	 * 
	 * @param date
	 * @param sdf
	 *            SimpleDateFormat 有静态常量 显示格式
	 * @return
	 */
	public static String getDateTimeMs(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 将字符串转化为Date
	 * 
	 * @param date
	 *            返回Date格式 yyyy-MM-dd
	 * @return
	 */
	public static Date doConvertToDate(String date) {
		Date result = null;
		try {
			result = DATE.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 返回带日期时间字符串 格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {

		return DATE_TIME.format(date);
	}

	/**
	 * 返回当前年份yyyy
	 * @return
	 */
	public static String getCurrentYear() {
		Date date = new Date();
		return YEAR.format(date);
	}	/**
	 * 返回当前月份MM
	 * @return
	 */
	public static String getCurrentMonth() {
		Date date = new Date();
		return MONTH.format(date);
	}
	
	/**
	 * 返回带日期字符串 格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		if (date == null) {
			return "";
		}
		return DATE.format(date);
	}
	
	//校验日期格式 例 2016-05-06 14:31:57 yyyy-MM-dd HH:mm:ss 结果为 true 如果使用 yyyy-MM-dd 则只校验这前10位
	public static boolean isValidDate(String str, String strFormat) {
		boolean convertSuccess = true;
		// 指定日期格式
		SimpleDateFormat format = new SimpleDateFormat(strFormat);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 返回带日期和时间的字符串
	 * 
	 * @param date
	 * @param sdf
	 *            SimpleDateFormat 有静态常量 显示格式
	 * @return
	 */
	public static String getOpTime(Date date, SimpleDateFormat sdf) {
		if (date == null) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 将字符串转换为日期类型
	 * 
	 * @param date
	 *            日期字符串yyyy-MM-dd
	 * @return
	 */
	public static Date parseDate(String date) {
		Date d = null;
		try {
			d = DATE.parse(date);
		} catch (ParseException e) {
		}
		return d;
	}

	/**
	 * 将字符串转换为日期时间类型
	 * 
	 * @param date
	 *            日期日期字符串yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date parseDateTime(String date) {
		if (date == null || "".equals(date))
			return null;
		Date destDate = null;
		try {
			destDate = DATE_TIME.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return destDate;
	}

	/**
	 * 根据条件获取指定年份和月份
	 * 
	 * @author 尚鸿运
	 * @version 2010-10-18
	 * @param monthNumber
	 *            要增加或减少的月份数
	 * @return
	 */
	public static String getDefaultDate(int monthNumber) {
		DecimalFormat df = new DecimalFormat("00");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthNumber); // 得到当前月份+monthNumberge个月

		int year = calendar.get(Calendar.YEAR);// 获取当前年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取当前月份

		return year + "-" + df.format(month);
	}

	/**
	 * 根据条件获取指定日期
	 * 
	 * @author 尚鸿运
	 * @version 2010-10-18
	 * @param monthNumber
	 *            要增加或减少的月份数
	 * @param dayNumber
	 *            要增加或减少的天数
	 * @return
	 */
	public static String getDefaultDate(int monthNumber, int dayNumber) {
		DecimalFormat df = new DecimalFormat("00");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthNumber); // 得到当前月份+monthNumberge个月
		calendar.add(Calendar.DATE, dayNumber); // 得到当前日期+monthNumber天
		int year = calendar.get(Calendar.YEAR);// 获取当前年份
		int month = calendar.get(Calendar.MONTH) + 1;// 获取当前月份
		int day = calendar.get(Calendar.DATE);// 获取当前日期
		
		return year + "-" + df.format(month) + "-" + df.format(day);
	}

	public static void main(String[] args) {
		System.out.println(formatFloat(12345623432.123456));
	}

	/**
	 * java方式获取32位UUID
	 * 
	 * @author 尚鸿运
	 * @version 2010-9-16
	 * @return
	 */
	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 值是否为空
	 * 
	 * @author 尚鸿运
	 * @version 2010-9-19
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {

		return null != value && !value.trim().equals("") && !value.equals("undefined") && !value.trim().equals("null");
	}

	/**
	 * 格式化浮点数
	 * @param f
	 * @return
	 */
	public static String formatFloat(double f) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
		String sj = df.format(f);
		return sj;
	}
	
	/**
	 * 比较两个字符串时间的大小
	 * @param t1     时间1
	 * @param t2     时间2
	 * @param parrten 时间格式 :yyyy-MM-dd
	 * @return 返回long =0相等，>0 t1>t2，<0 t1<t2
	 */
	public static long compareStringTime(String t1, String t2, String parrten) {
		SimpleDateFormat formatter = new SimpleDateFormat(parrten);
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = formatter.parse(t1, pos);
		Date dt2 = formatter.parse(t2, pos1);
		long l = dt1.getTime() - dt2.getTime();
		return l;
	}
	
	/**
	 * 生成MD5编码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String createMD5(String str) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcByte = str.getBytes();
		md5.update(srcByte);
		byte[] resultByte = md5.digest();
		String result = new String(resultByte);

		result = "";
		for (int i = 0; i < resultByte.length; i++) {
			result += Integer.toHexString(
					(0x000000ff & resultByte[i]) | 0xffffff00).substring(6);
		}

		return result;
	}

	/**
	 * 把组数拼接成IN语句(字符型)
	 * @author GZZ
	 * @param arr
	 * @return
	 */
	public static String ArrayToIn(String[] arr) {// 字符IN字符窜
		StringBuffer sb = new StringBuffer("IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append("'" + arr[i] + "',");
			} else {
				sb.append("'" + arr[i]+"'");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 把组数拼接成IN语句(数值型)
	 * @author GZZ
	 * @param arr
	 * @return
	 */
	public static String ArrayToInNum(String[] arr) {// 字符IN字符窜
		StringBuffer sb = new StringBuffer("IN (");
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append(arr[i] + ",");
			} else {
				sb.append(arr[i]);
			}
		}
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 级联创建目录
	 * @param path
	 */
	public static void mkdir(String path) {
		File dir = new File(path);
		
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}
	
	/**
	 * 获取全部文件名 文件名+扩展名
	 * @param filePath
	 */
	public static String getFullFileName(String filePath) {
		filePath.replace("\\", "/");
		String[] pathArray = filePath.split("\\/");
		
		return pathArray[pathArray.length - 1];
	}
	
	/**
	 * 获取文件名
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		String fileName = getFullFileName(filePath);
		
		String[] fileArray = fileName.split("\\.");
		return fileArray[0];
	}

	/**
	 * 获取导出文件路径
	 * @param paramMap
	 * @param exportTemplatePath
	 * @return
	 *//*
	public static String getExportFileName(Map<String, Object> paramMap, ServletContext context, String exportTemplatePath) {
		String resultFileName = null;
		Configuration freemarkerCfg = new Configuration();  
		
		//加载模版  
		freemarkerCfg.setServletContextForTemplateLoading(context, "/");  
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8"); 
		
		try {  
		    //指定模版路径  
		    Template template = freemarkerCfg.getTemplate(exportTemplatePath, "UTF-8");  
		    template.setEncoding("UTF-8");
		    
		    //生成静态文件目录
		    String year = getCurrentYear();
		    String month = getCurrentMonth();
		    
		    String filePath = context.getRealPath("/report/export" ) + "/" + year + "/" + month + "/";;
		    String fileName =  getFileName(exportTemplatePath) + ".xls";
		    mkdir(filePath);
		    
		    resultFileName = filePath + fileName;
		    File exportFile = new File(resultFileName);  
		    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportFile), "UTF-8"));  
            
		    template.process(paramMap, out); 
            
            out.flush();  
            out.close();  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}

		return resultFileName;
	}
*/	
	
	public static String getWeek(int week){
		String w = "";
		switch (week) {
		case 0:
			w="星期日";
			break;
		case 1:
			w="星期一";
			break;
		case 2:
			w="星期二";
			break;
		case 3:
			w="星期三";
			break;
		case 4:
			w="星期四";
			break;
		case 5:
			w="星期五";
			break;
		case 6:
			w="星期六";
			break;			
		default:
			break;
		}
		return w;
	}
	
	/**
	 * 计算税收
	 * @param salary
	 */
	public static double salaryTax(double salary){
		double tax = 0;
		double moneyTax = salary - 2000; //大于2000才交税
		if(moneyTax > 0){
			if(moneyTax < 500){
				tax = moneyTax * 0.05;
			}else if(moneyTax < 2000){
				tax = moneyTax * 0.1 - 25;
			}else if(moneyTax < 2000){
				tax = moneyTax * 0.1 - 25;
			}else if(moneyTax < 5000){
				tax = moneyTax * 0.15 - 125;
			}else if(moneyTax < 20000){
				tax = moneyTax * 0.2 - 375;
			}else if(moneyTax < 40000){
				tax = moneyTax * 0.25 - 1375;
			}else if(moneyTax < 60000){
				tax = moneyTax * 0.30 - 3375;
			}else if(moneyTax < 80000){
				tax = moneyTax * 0.35 - 6375;
			}else if(moneyTax < 100000){
				tax = moneyTax * 0.40 - 10375;
			}else{
				tax = moneyTax * 0.45 - 15375;
			}
		}
		return tax;
	}

	/**
	 * 反转数组的顺序
	 * 
	 * @param input
	 * @return
	 */
	public static <T> T[] reversalArray(T[] input) {
		for (int i = 0, j = input.length - 1; i < j; i++, j--) {
			T tmp = input[i];
			input[i] = input[j];
			input[j] = tmp;
		}
		return input;
	}

	/**
	 * 合并多个数组为一个
	 * 
	 * @return
	 */
	public static <T> T[] combineElements(T[] arr1, T[] arr2) {
		if (arr1 == null || arr2 == null) {
			// 如果arr1,arr2为null返回null，如果单个为null,返回非null元素
			T[] temp = arr2 == null ? arr1 : arr2;
			return temp;
		}
		List<T> temp = new ArrayList<T>(arr1.length + arr2.length);
		temp.addAll(Arrays.asList(arr1));
		temp.addAll(Arrays.asList(arr2));
		return temp.toArray(arr1);
	}
	
	/**
	 * 将map对象转换成普通的java bean
	 * 
	 * @param map
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T getBeanFromMap(Map map, Class<T> clazz) throws Exception {
		T bean = clazz.newInstance();
		Iterator<?> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			try {
				String key = iter.next().toString();
				Object val = map.get(key);
				StringBuilder methodName = new StringBuilder("set");
				methodName.append(key.substring(0, 1).toUpperCase()).append(key.substring(1));
				Method method = clazz.getMethod(methodName.toString(), val.getClass());
				method.invoke(bean, val);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
		return bean;
	}
	
	/**
	 * 检查字符串是否包含中文
	 * @param str
	 * @return
	 */
	public boolean isContainsHanyu(String str) {
		boolean flag = false;

		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);

		if (matcher.find()) {
			flag = true;
		}

		return flag;
	}
	
	/**
	 * 获取当前时间戳表示
	 * 
	 * @return
	 */
	public static Timestamp getTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime() / 1000 * 1000);
	}
	
	/**
	 * 深度复制,复制的整个对象图.
	 */
	public static Serializable deeplyCopy(Serializable src) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			byte[] data = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	/**
	 * 将MAP对象，以KEY的ANSI排序，将key-value，组装成partner=2088&id=xxx形式返回
	 * 
	 * @param params
	 * @return
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}
	
	/**
	 * 生成指定长度的随机字符串
	 * 
	 * @param len
	 * @return
	 */
	public static String randomNumberString(int len) {
		StringBuffer randomCode = new StringBuffer();
		String[] codeSequence = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"s", "y", "z" };
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			String strRand = String.valueOf(codeSequence[random
					.nextInt(codeSequence.length)]);
			randomCode.append(strRand);
		}
		return randomCode.toString();
	}

	
//	/**
//	 * 为防止事物缓存太大,拆分保存
//	 * @param saveValues
//	 * @throws Exception
//	 */
//	private void saveUserRecommends(Object[] saveValues)throws Exception {
//		if (saveValues!=null && saveValues.length > 0) {
//			ICmUserRecommendDAO userRecommendDAO = (ICmUserRecommendDAO) ServiceFactory.getService(ICmUserRecommendDAO.class);
//			
//			if (saveValues.length <= 500) {
//				userRecommendDAO.saveCmUserRecommends(saveValues);
//				return;
//			}else{
//				ICmUserRecommendValue[] temp = new ICmUserRecommendValue[500];
//				ICmUserRecommendValue[] other = new ICmUserRecommendValue[saveValues.length - 500];
//				
//				for (int j = 0; j < saveValues.length; j++) {
//					
//					if (j < 500) {
//						temp[j] = saveValues[j];
//					}else{
//						other[j-500] = saveValues[j];
//					}
//					
//				}
//				//前500直接存入
//				userRecommendDAO.saveCmUserRecommends(temp);
//				//大于500的部分继续递归
//				saveUserRecommends(other);
//			}
//		}
//	}
	
	private static int MAX_CONTEXT_LENHTH = 64 * 1024;

	/**
	 * 获取二进制的报文体，并转换成字符串
	 */
	public static String getContext(HttpServletRequest request) throws ServletException, IOException, Exception {
		byte[] buffer = new byte[MAX_CONTEXT_LENHTH];

		InputStream in = request.getInputStream();
		int length = in.read(buffer);
		if (length <= 0) {
			throw new IOException("请求报文为空，系统无法处理！");
		}
		// String encode = request.getCharacterEncoding();
		// if( StringUtils.isBlank(encode) ) {
		// throw new IOException("未指明字符集编码，系统无法处理！");
		// }
		byte[] data = new byte[length];
		System.arraycopy(buffer, 0, data, 0, length);
		String contextutf8 = new String(data, "UTF-8");
		
		if (null != contextutf8 && !"".equals(contextutf8)) {
			contextutf8 = contextutf8.trim(); // 去掉前后空白字符，否则会报格式错误
		}
		return contextutf8;
	}
	
	
	
//	// 阿里云
//	/**
//	 * 截取
//	 * 
//	 * @param bucket
//	 * @param key
//	 * @param size
//	 */
//	private void doHandle(String bucket, String key, String size,
//			Map<String, String> result) {
//
//		int dotIndex = key.lastIndexOf(".");
//		// 后缀，转为小写，仅支持[bmp,jpg,jpeg,wbmp,png]
//		String suffix = "";
//		try {
//			suffix = key.substring(dotIndex + 1).toLowerCase();
//			boolean boo = Arrays.asList("bmp", "jpg", "jpeg", "wbmp", "png").contains(suffix);
//			if (!boo) {
////				log.error("不支持的图片格式: " + suffix);
//				result.put("error", "不支持的图片格式: " + suffix);
//				return;
//			}
//		} catch (Exception e) {
////			log.equals("获取文件后缀异常: " + e.toString());
//			result.put("error", "获取文件后缀异常");
//			return;
//		}
//
//		String[] sizeList = size.split(",");
//		String successSize = "";
//
//		// 多种尺寸循环处理
//		for (String str : sizeList) {
//			// 1.获取原图
//			OSSObject object = null;
//			try {
//				object = OSSUtils.getObject(bucket, key);
//			} catch (Exception e) {
//
//			}
//			if (null == object) {
////				log.error("源文件不存在[bucket: " + bucket + ", key: " + key + "]");
//				result.put("error", "源文件不存在");
//				return;
//			}
//
////			if (StringUtils.isBlank(str)) {
////				continue;
////			}
//
//			int width = 0, height = 0;
//			try {
//				String[] array = str.split("x");
//				width = Integer.parseInt(array[0].trim());
//				height = Integer.parseInt(array[1].trim());
//			} catch (Exception e) {
////				log.error("图片尺寸参数不正确: " + e.toString());
//				continue;
//			}
//
//			// 截取的图片链接
//			String newKeyCut = key.substring(0, dotIndex) + "_" + width + "_"
//					+ height + "_cut." + suffix;
//			String newKeyCompress = key.substring(0, dotIndex) + "_" + width
//					+ "_" + height + "_compress." + suffix;
//
//			// 2.如果需要处理的对象存在则不做处理，直接处理下一个
//			OSSObject newObject = null;
//			try {
//				newObject = OSSUtils.getObject(bucket, newKeyCompress);
//			} catch (Exception e) {
//
//			}
//			if (newObject != null) {
////				log.info("目标文件已存在[bucket: " + bucket + ", key: "
//						+ newKeyCompress + "]");
//				// 请求结果OSSObject实例。使用完之后需要手动关闭其中的ObjectContent释放请求连接。
//				try {
//					newObject.getObjectContent().close();
//				} catch (IOException e) {
////					log.error(e.toString());
//				}
//				continue;
//			}
//
//			// 3.将object转换成image，生成文件上传oss
//			InputStream stream = null;
//			File cutFile = null;
//			File compressFile = null;
//			try {
//				stream = object.getObjectContent();
//				BufferedImage image = ImageIO.read(stream);
//
//				int imageWidth = image.getWidth(null);
//				int imageHeight = image.getHeight(null);
//
//				// 取短边从中心点向外扩散截取
//				int newWidth = width < imageWidth ? width : imageWidth;
//				int newHeight = height < imageHeight ? height : imageHeight;
//				int x = (imageWidth - newWidth) / 2;
//				int y = (imageHeight - newHeight) / 2;
//
//				// 随机生成预处理文件名，防止多个进程使用相同的文件
//				String preCutFileName = System.currentTimeMillis() + "_"
//						+ Math.round(Math.random() * 1000) + "_cut." + suffix;
//				String preCompressFileName = System.currentTimeMillis() + "_"
//						+ Math.round(Math.random() * 1000) + "_compress."
//						+ suffix;
//
//				// 4.截取
//				image = image.getSubimage(x, y, newWidth, newHeight);
//				cutFile = new File(preCutFileName);
//				ImageIO.write(image, suffix, cutFile);
//
//				// 5.压缩
//				compressFile = compressPic(image, newWidth, newHeight,
//						preCompressFileName);
//
//				// 6.将截取和压缩的图片上传到OOS
//				boolean cutResult = OSSUtils.putObject(bucket, newKeyCut,
//						cutFile);
//				boolean compressResult = OSSUtils.putObject(bucket,
//						newKeyCompress, compressFile);
//				if (cutResult && compressResult) {
//					successSize += (str + ",");
//				}
//			} catch (Exception e) {
////				log.error(e.toString());
//			} finally {
//				// 7.销毁处理
//				try {
//					if (stream != null) {
//						stream.close();
//						stream = null;
//					}
//
//					if (cutFile != null) {
//						cutFile.delete();
//						cutFile = null;
//					}
//
//					if (compressFile != null) {
//						compressFile.delete();
//						compressFile = null;
//					}
//				} catch (Exception e) {
////					log.error(e.toString());
//				}
//			}
//		}
//
//		if (successSize.endsWith(",")) {
//			successSize = successSize
//					.substring(0, successSize.lastIndexOf(","));
//			result.put("successSize", successSize);
//		}
//
////		log.info("处理成功的尺寸: [" + successSize + "]");
//	}

//	/**
//	 * 压缩
//	 * 
//	 * @param image
//	 * @param width
//	 * @param height
//	 * @param fileName
//	 * @return
//	 * @throws Exception
//	 */
//	public File compressPic(
//		BufferedImage image, 
//		int width,
//		int height,
//		String fileName
//	) throws Exception {
//		
//		if (null == image) {
//			return null;
//		}
//
//		BufferedImage tag = new BufferedImage(width, height,
//				BufferedImage.TYPE_INT_RGB);
//		// Image.SCALE_SMOOTH 缩略算法,生成缩略图片的平滑度的，优先级比速度高，生成的图片质量比较好，但速度慢
//		tag.getGraphics().drawImage(
//				image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
//				0, null);
//		FileOutputStream out = new FileOutputStream(fileName);
//		// JPEGImageEncoder可适用于其他图片类型的转换
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//		// 固定压缩比例 0.75 high quality 0.5 medium quality 0.25 low quality
//		jep.setQuality(0.75f, true);
//		encoder.encode(tag, jep);
//
//		return new File(fileName);
//	}
	
	/**
	 * 分表用
	 * @param baseTable
	 * @param id
	 * @return
	 */
	public String getSplitName(String baseTable, long id) {
		return String.format("baseTable_%d", id % 20 + 1);
	}
	
	
	private static final String ENCODING = "UTF-8";
	
	/**
	 * 将一个层级的XML字符串转换MAP
	 * 
	 * @param xml
	 * @return
	 */
	public static Map<String, String> parseXML(String xml) {
//		log.debug("parseXML:" + xml);
		Map<String, String> map = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(xml
					.getBytes(ENCODING)));
			Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
			while (iterator.hasNext()) {
				Element e = iterator.next();
				map.put(e.getName(), e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 使用HttpClient将字符数据post到url上
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String postData(String url, String data) {
		String result = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);
			postRequest.setHeader("messageDigest", MessageDigestUtil.MD5Encode(data));
			StringEntity input = new StringEntity(data, ENCODING);
			input.setContentEncoding(ENCODING);
			input.setContentType("text/plain");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()), ENCODING));
			String output = "";
			StringBuffer sb = new StringBuffer();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将单位为分的费用，格式化为元
	 * 
	 * @param fee
	 * @return
	 */
	public static String formatFee(long fee) {
		String result = "";
		DecimalFormat df = new DecimalFormat("0.##");
		result = df.format(fee / 100.0);
		return result;
	}

	
	private void load(String jarName){
//		String jarName = "C.jar";
		try {
			File file = new File(jarName);
			URL url = file.toURL();
			URLClassLoader loader = new URLClassLoader(new URL[]{url});
			Class aClass = loader.loadClass("C");
			Object obj = aClass.newInstance();
			aClass.getMethods();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	public static Map<String,String> ReadCookieMap(HttpServletRequest request){ 
	    Map<String,String> cookieMap = new HashMap<String,String>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie.getValue());
	        }
	    }
	    return cookieMap;
	}
	
}
