package com.example.configuremanager.pagehelper.service;

import com.example.configuremanager.pagehelper.bean.Message;
import com.example.configuremanager.pagehelper.bean.SelectMsgList;

import java.util.List;

/**
 * Created by liumengbing on 2018/10/22.
 */
public interface MessageService {

    List<SelectMsgList> selectList(SelectMsgList msg);

    int insertMsg(Message msg);

}

