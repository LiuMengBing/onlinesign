package com.example.dictionary.dao;

import com.example.dictionary.bean.Dictionary;

import java.util.List;

public interface DictionaryMapper {

    /**
     *  添加字典及字典项
     * @param record
     * @return
     */
    int insert(Dictionary record);

    /**
     * 查询字典及字典项信息
     * @param id
     * @return
     */
    Dictionary selectByPrimaryKey(Integer id);

    /**
     * 根据id更新字典及字典项
     * @param record
     * @return
     */
    int updateByPrimaryKey(Dictionary record);

    /**
     * 根据父节点查询字典列表
     * @return
     */
    List<Dictionary> selectByParentId(Integer parentId);

    /**
     * 查询所有的字典及字典项
     * @return
     */
    List<Dictionary> selectAll();

    Dictionary selectOneByParentId(Integer parentId);
}