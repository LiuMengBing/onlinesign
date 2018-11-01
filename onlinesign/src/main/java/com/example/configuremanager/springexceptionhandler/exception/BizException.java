package com.example.configuremanager.springexceptionhandler.exception;

/**
 * <p>
 * description: 业务异常基类，所有业务异常都必须继承于此异常 .
 * </p>
 */
public class BizException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    private Object data;

    /**
     * 数据库操作,insert返回0
     */
    public static final BizException DB_INSERT_RESULT_0 = new BizException(
            1001, "数据库操作,insert返回0");

    /**
     * 数据库操作,update返回0
     */
    public static final BizException DB_UPDATE_RESULT_0 = new BizException(
            1002, "数据库操作,update返回0");

    /**
     * 数据库操作,selectOne返回null
     */
    public static final BizException DB_SELECTONE_IS_NULL = new BizException(
            1003, "数据库操作,selectOne返回null");

    /**
     * 数据库操作,list返回null
     */
    public static final BizException DB_LIST_IS_NULL = new BizException(
            1004, "数据库操作,list返回null");

    /**
     * Token 验证不通过
     */
    public static final BizException TOKEN_IS_ILLICIT = new BizException(
            1005, "Token 验证非法");
    /**
     * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
     */
    public static final BizException SESSION_IS_OUT_TIME = new BizException(
            1006, "会话超时");

    /**
     * 生成序列异常时
     */
    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(
            1007, "序列生成超时");

    public BizException(){

    }

    public BizException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.message = String.format(msgFormat, args);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BizException data(Object data){
        this.data = data;
        return this;
    }
}
