package com.example.configuremanager.pagehelper.bean;

import com.example.configuremanager.pagehelper.bean.enums.MsgStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息表实体类 s_message
 * Created by liumengbing on 2018/10/25.
 */
public class Message implements Serializable{
    private Integer id;

    private String msgModule;

    private String msgType;

    private String msgContent;

    private Integer msgStatus;

    private String sendTo;

    private Date sendTime;

    private String sendor;

    private Date updateTime;

    private String updator;

    private int isDel;//是否删除（0否 1是）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsgModule() {
        return msgModule;
    }

    public void setMsgModule(String msgModule) {
        this.msgModule = msgModule == null ? null : msgModule.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo == null ? null : sendTo.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendor() {
        return sendor;
    }

    public void setSendor(String sendor) {
        this.sendor = sendor == null ? null : sendor.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
}