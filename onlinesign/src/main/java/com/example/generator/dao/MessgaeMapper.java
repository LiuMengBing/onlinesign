package com.example.generator.dao;

import com.example.generator.bean.Messgae;

public interface MessgaeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Messgae record);

    int insertSelective(Messgae record);

    Messgae selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Messgae record);

    int updateByPrimaryKey(Messgae record);
}