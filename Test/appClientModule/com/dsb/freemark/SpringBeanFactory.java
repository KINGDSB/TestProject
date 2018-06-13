//package com.dsb.freemark;
//
//import java.util.Iterator;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//public class SpringBeanFactory
//{
//	
//	/**
//	 * spring配置文件
//	 */
//	protected final static String[] SPRING_CONFIG_FILES = {
//			"beans.xml"
//	};
//	private static ApplicationContext ctx = null;
//	
//	
//	public static Object getBean(String name) {
//		initContext();
//        return ctx.getBean(name);
//    }
//	
//    public static Object getBean(String name,ServletContext servletContext) {
//        if (servletContext==null && ctx == null) return null;
//        if (ctx == null) {
//            ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        }
//        return ctx.getBean(name);
//    }
//    
//    public static <T> T getBean(Class<T> clazz,ServletContext servletContext) {
//    	if (servletContext==null && ctx == null) return null;
//        if (ctx == null) {
//            ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        }
//        return getBean(clazz);
//    }
//    /**
//	 * 通过接口从Spring窗口中检索对象，并返回与所传入接口相同的类型
//	 * 说明：当所传入接口有多个实现时，返回检索到的第一个实现类对象
//	 * 此方法供java的测试用例调用
//	 * @param <T>
//	 * @param clazz 接口类型
//	 * @return 返回接口类型
//	 */
//	@SuppressWarnings("unchecked")
//	public static <T> T getBean(Class<T> clazz) {
//		initContext();
//		Map<String, T> maps = ctx.getBeansOfType(clazz);
//		Iterator<String> iterator = maps.keySet().iterator();
//		T object = null;
//		if(iterator.hasNext()){
//			Object next = iterator.next();
//			object = maps.get(next);
//		}
//		return object;
//	}
//	private static void initContext(){
//		if(ctx==null){
//			System.out.println("初始化ClassPathXmlApplicationContext...");
//			ctx = new ClassPathXmlApplicationContext(SPRING_CONFIG_FILES);
//		}
//	}
//    public static void autowireService(Object bean) {
//        ((AbstractApplicationContext) ctx).getBeanFactory()
//         .autowireBeanProperties(bean,
//            AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
//    }
//
//    /**
//     * Web服务器启动时，初始化上下文
//     * @param servletContext
//     */
//	public static void init(ServletContext servletContext) {
//		if(servletContext!=null && ctx==null){
//			System.out.println("初始化context...");
//			ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//		}
//	}
//
//}
