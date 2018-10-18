/**
 * Copyright 2015-2016 momodr.com & Co., Ltd.
 */
package com.example.msgnotice.login;

/**
 * @author 
 * @version 1.0 2017年4月8日
 */
public class StandardResult{
	
	private int code;
	
	private String msg;
	
	private Object result;
	
	
	// =====================================================================
		
	public StandardResult(){}
	
	public StandardResult(int code, String msg, Object result){
		this.code = code;
		this.msg = msg;
		this.result = result;
	}
		
	// =====================================================================
	
	// SUCCESS
	public static StandardResult ok(){
		return StandardResult.ok("ok", null);
	}
	
	public static StandardResult ok(String msg){
		return StandardResult.ok(msg, null);
	}
	
	public static StandardResult ok(Object result){
		return StandardResult.ok( "ok", result);
	}
	
	public static StandardResult ok(String msg, Object result){
		return new StandardResult(SResultConstant.SUCCESS, msg, result);
	}
	
	// FAILURE
	public static StandardResult error(){
		return StandardResult.error(SResultConstant.FAILURE, "failure", null);
	}
	
	public static StandardResult error(String msg){
		return StandardResult.error(SResultConstant.FAILURE, msg, null);
	}
	
	public static StandardResult error(int code, String msg){
		return StandardResult.error(code, msg, null);
	}
	
	public static StandardResult error(Object result){
		return StandardResult.error(SResultConstant.FAILURE, "failure", result);
	}
	
	public static StandardResult error(int code, String msg, Object result){
		return new StandardResult(code, msg, result);
	}
	
	
	// =====================================================================

	public Object getResult() {
		return result;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}


}
