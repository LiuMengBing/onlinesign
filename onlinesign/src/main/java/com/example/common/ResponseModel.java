package com.example.common;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@ApiModel("标准响应格式")
public class ResponseModel implements Serializable {
    @ApiModelProperty("响应状态码")
    private Integer status;
    @ApiModelProperty("响应信息")
    private String message;
    @ApiModelProperty("响应内容")
    private Object data;

    public ResponseModel setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public ResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseModel setData(Object data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static <E> ResponseModel newSuccess(E data) {
        return new ResponseModel().setStatus(200).setData(data);
    }
    public static <E> ResponseModel newSuccess(E data,String message) {
        return newSuccess(data).setMessage(message);
    }

    public static ResponseModel newFailure(Integer status, String msg) {
        return new ResponseModel().setStatus(status).setMessage(msg);
    }
    public static ResponseModel newFailure(HttpStatus status, String msg) {
        return newFailure(status.value(),msg);
    }
    public static ResponseModel newFailure(Integer status, String msg, Object data) {
        return newFailure(status,msg).setData(data);
    }

    public static <E> ResponseModel newPage(Page<E> page) {
        return newSuccess(PageInfo.of(page));
    }

    /**
     * 直接输出查出来的数据,注意是否含有敏感数据
     * @param supplier lambda式查询 例如()->mapper.selectAll()
     * @param pageNum 页码
     * @param pageSize 页大小
     */
    public static ResponseModel newRawData(ISelect supplier, Integer pageNum, Integer pageSize) {
        if (pageNum==null)
            pageNum = 1;
        if (pageSize==null)
            pageSize = 10;
        Page<Integer> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(supplier);
        return newPage(page);
    }


    public static ResponseModel newResponseModel(boolean result){
        if (result)
            return ResponseModel.newSuccess(result);
        else
            return ResponseModel.newFailure(100, "操作失败");
    }

}