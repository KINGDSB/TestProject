package com.dsb.tools;
//package com.dhome.common.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.NoSuchElementException;
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
///**
// * Properties文件载入工具类，可载入多个properties文件
// * 
// * 相同的属性在最后载入的文件中将会覆盖之前的值，但以System的properties值有限
// * 
// */
//public class PropLoader {
//
//	private static Logger logger = LoggerFactory.getLogger(PropLoader.class);
//
//	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
//
//	private static final Properties properties;
//
//	static {
//		properties = loadProperties("config.properties");
//	}
//
//	public PropLoader() {
//
//	}
//
//	public Properties getProperties() {
//		return properties;
//	}
//
//	/**
//	 * 取出Property，但以System的Property优先，娶不到返回空字符串。
//	 */
//	private static String getValue(String key) {
//		String systemProperty = System.getProperty(key);
//		if (systemProperty != null) {
//			return systemProperty;
//		}
//		if (properties.containsKey(key)) {
//			return properties.getProperty(key);
//		}
//		return "";
//	}
//
//	/**
//	 * 取出String类型的Property，但以System的Property优先，如果都为Null则抛出异常
//	 */
//	public static String getProperty(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return value;
//	}
//
//	/**
//	 * 取出String类型的Property，但以System的Property优先，如果都为null则返回Default的值
//	 */
//	public static String getProperty(String key, String defaultValue) {
//		String value = getValue(key);
//		return value != null ? value : defaultValue;
//	}
//
//	/**
//	 * 取出Integer类型的Property，但以System的Property优先。如果都为null或内容错误则抛出异常。
//	 */
//	public static Integer getInteger(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Integer.valueOf(value);
//	}
//
//	public static Integer getInteger(String key, Integer defaultValue) {
//		String value = getValue(key);
//		return value != null ? Integer.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * 取出Double类型的Property，但以System的Property优先。如果都为null或内容错误则抛出异常。
//	 */
//	public static Double getDouble(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Double.valueOf(value);
//	}
//
//	public static Double getDouble(String key, Double defaultValue) {
//		String value = getValue(key);
//		return value != null ? Double.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * 取出Boolean类型的Property，但以System的Property优先。如果都为null或内容错误则抛出异常。
//	 */
//	public static Boolean getBoolean(String key) {
//		String value = getValue(key);
//		if (value == null) {
//			throw new NoSuchElementException();
//		}
//		return Boolean.valueOf(value);
//	}
//
//	public static Boolean getBoolean(String key, Boolean defaultValue) {
//		String value = getValue(key);
//		return value != null ? Boolean.valueOf(value) : defaultValue;
//	}
//
//	/**
//	 * 载入多个文件，文件路径使用Spring Resource格式
//	 */
//	private static Properties loadProperties(String... resourcePaths) {
//		Properties props = new Properties();
//
//		for (String location : resourcePaths) {
//
//			logger.debug("Loading properties file from path：" + location);
//
//			InputStream is = null;
//			try {
//				Resource resource = resourceLoader.getResource(location);
//				is = resource.getInputStream();
//				props.load(is);
//			} catch (IOException e) {
//				logger.error("could not load properties from path：" + location + "," + e.getMessage(), e);
//			} finally {
//				if (is != null) {
//					try {
//						is.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return props;
//	}
//}
