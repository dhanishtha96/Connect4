package com.group.connect4.restUtils;

public class ThreadLocalClass {

	@SuppressWarnings("rawtypes")
	public static final ThreadLocal threadLocal = new ThreadLocal(); 
	
	@SuppressWarnings("unchecked")
	public static void setID(String requestIdString) { 
		threadLocal.set(requestIdString); 
	}
	
	public static String getID() {
		return (String)threadLocal.get();
	}
}
