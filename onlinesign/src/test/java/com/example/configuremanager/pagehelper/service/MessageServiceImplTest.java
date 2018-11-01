package com.example.configuremanager.pagehelper.service;

import com.example.configuremanager.pagehelper.bean.Message;
import com.example.configuremanager.pagehelper.bean.SelectMsgList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by liumengbing on 2018/10/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Test
    public void selectList() throws Exception {
        SelectMsgList selectMsgList = new SelectMsgList();
        selectMsgList.setMsgContent("测试");
        messageService.selectList(selectMsgList);
    }

    @Test
    public void insertMsg() throws Exception {
        Message msg = new Message();
//        MsgStatus msgStatus = new MsgStatus(0);
//        msg.setMsgStatus(msgStatus);
        msg.setMsgContent("测试枚举类型");
        messageService.insertMsg(msg);
    }

}