package com.example.common.exception;

/**
 * 自定义异常,抛出后被全局捕获返回前端
 */
public class CustomException extends Exception {
    private int code;
    private Object data;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
