package com.example.configuremanager.pagehelper.service;

import com.example.configuremanager.pagehelper.bean.Message;
import com.example.configuremanager.pagehelper.bean.SelectMsgList;
import com.example.configuremanager.pagehelper.dao.MessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liumengbing on 2018/10/22.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper msgMapper;

    @Override
    public List<SelectMsgList> selectList(SelectMsgList msg) {
        return msgMapper.selectList(msg);
    }

    @Override
    public int insertMsg(Message msg) {
        return msgMapper.insertSelective(msg);
    }


}
