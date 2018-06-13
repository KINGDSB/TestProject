//package com.dsb.freemark.filter;
//
//import java.io.IOException;
//import java.util.List;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.navidata.contant.Item;
//
//public class SessionCheckFilter implements Filter {
//
//	@Override
//	public void destroy() {
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		HttpSession session = httpRequest.getSession(false);
//		String uri = httpRequest.getRequestURI();
//		String contextPath = httpRequest.getContextPath();
//		System.out.println(uri + "\t" + contextPath);
//		String referer = ((HttpServletRequest) request).getHeader("referer");  
//		System.out.println("前一个链接是："+referer);
//		if (uri.equals(contextPath + "/")) {
//			httpResponse.sendRedirect(contextPath + "/login.jsp");
//		} else if (uri.equals(contextPath + "/user/login")
//				|| uri.equals(contextPath + "/login.jsp")
//				|| uri.endsWith(".css") 
//				|| uri.endsWith(".js")
//				|| uri.endsWith(".jpg")
//				|| uri.endsWith(".png")
//				|| uri.contains("api")
//				|| uri.contains("reviewmsg")
//				|| uri.contains("test1")
//				|| uri.contains("captchaController")
//				|| uri.contains("loginCheck")
//				|| uri.contains("recordchangemsg")
//				|| uri.contains("classrecord")
//				|| uri.contains("imageup")
//				|| uri.contains("upload")
//				|| uri.contains("static")
//				) {
//			chain.doFilter(request, response);
//		} else if (session != null && session.getAttribute("loginUser") != null) {
//			List<Item> permissions = (List<Item>) session.getAttribute("loginUserPermission");
//			boolean flag = false;
//			for (Item item : permissions) {
//				if (uri.equals(contextPath + item.getValue())) {
//					flag = true;
//					break;
//				}
//			}
//			if (flag && referer != null) {
//				chain.doFilter(request, response);
//			} else {
//				
//				if(uri.equals(contextPath + "/user/left") && referer.equals(contextPath+"/user/login")){
//					chain.doFilter(request, response);
//				}else if(uri.equals(contextPath + "/user/top") && referer.endsWith(contextPath+"/user/login")){
//					chain.doFilter(request, response);
//				}else if(uri.equals(contextPath + "/user/left") && referer.endsWith(contextPath+"/user/login")){
//					chain.doFilter(request, response);
//				}else if(uri.equals(contextPath + "/user/first") && referer.endsWith(contextPath+"/user/login")){
//					chain.doFilter(request, response);
//				}else if(uri.endsWith("listData") && referer.endsWith("list")){
//					chain.doFilter(request, response);
//				}else{
//					System.out.println("你没有权限访问该资源");
//					chain.doFilter(request, response);
//				}
//				
//				
//			}
//
//		} else {
//			System.out.println("没有登陆");
//			
//			httpRequest.setAttribute("errorMsg", "您还没有登陆");
//			httpResponse.sendRedirect(contextPath+"/login.jsp");
//			
//		}
//
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//
//	}
//
//}
