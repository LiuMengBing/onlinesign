package com.example.msgnotice;

import java.util.Date;

/**
 * Created by liumengbing on 2018/10/18 0018.
 * websocket消息模型
 *
 */
public class Msg {

    // 推送人ID
    private String fromUid;

    // 定点推送人ID
    private String toUid;

    // 定点推送单位ID
    private String toOrgId;

    // 消息体
    private String data;

    // 推送时间
    private Date createDate = new Date();

    // 消息状态
    private Integer flag;

    public Msg() {

    }

    public Msg(String fromUid, String toUid, String toOrgId, String data, Date createDate, Integer flag) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.toOrgId = toOrgId;
        this.data = data;
        this.createDate = createDate;
        this.flag = flag;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public String getToOrgId() {
        return toOrgId;
    }

    public void setToOrgId(String toOrgId) {
        this.toOrgId = toOrgId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "fromUid='" + fromUid + '\'' +
                ", toUid='" + toUid + '\'' +
                ", toOrgId='" + toOrgId + '\'' +
                ", data='" + data + '\'' +
                ", createDate=" + createDate +
                ", flag=" + flag +
                '}';
    }
}