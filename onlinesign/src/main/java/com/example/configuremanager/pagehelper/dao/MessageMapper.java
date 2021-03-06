package com.example.configuremanager.pagehelper.dao;

import com.example.configuremanager.pagehelper.bean.Message;
import com.example.configuremanager.pagehelper.bean.SelectMsgList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liumengbing on 2018/10/26.
 */
@Mapper
public interface MessageMapper {

    /**
     * 测试pageHelper分页
     * @param msg
     * @return
     */
    List<SelectMsgList> selectList(SelectMsgList msg);

    int insertSelective(Message record);
}
