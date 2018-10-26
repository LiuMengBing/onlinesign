package com.example.configuremanager.pagehelper.dao;

import com.example.configuremanager.pagehelper.bean.SelectMsgList;

import java.util.List;

/**
 * Created by liumengbing on 2018/10/26.
 */
public interface MessageMapper {

    /**
     * 测试pageHelper分页
     * @param msg
     * @return
     */
    List<SelectMsgList> selectList(SelectMsgList msg);
}
