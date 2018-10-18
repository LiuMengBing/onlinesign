/**
 * Copyright 2015-2016 momodr.com & Co., Ltd.
 */
package com.example.msgnotice;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {
	
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!StringUtils.isEmpty(ip)) {
			ip = ip.split(",")[0];
		}
		return ip;
	}
	
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes reqAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return reqAttrs.getRequest();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static String getRequestPath(){
		String contextPath = getRequest().getServletPath();
		return contextPath;
	}
	
	public static ServletContext getServletContext(){
		return getRequest().getServletContext();
	}
	public static String getXmdz(){
		return Thread.currentThread().getContextClassLoader().getResource("").getPath();
	}
	
	

}
