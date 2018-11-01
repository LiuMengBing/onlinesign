package com.example.dictionary.service;

import com.example.configuremanager.springexceptionhandler.exception.CustomException;
import com.example.dictionary.bean.DictionaryTree;
import com.example.dictionary.bean.form.AddDictionaryForm;
import com.example.dictionary.bean.form.UpdateDictionaryForm;

import java.util.Map;

public interface DictionaryService {

    /**
     *  添加字典及字典项
     * @param addDictionaryForm
     * @return
     */
    int insert(AddDictionaryForm addDictionaryForm) throws CustomException;

    /**
     * 根据id更新字典及字典项
     * @param updateDictionaryForm
     * @return
     */
    int updateByPrimaryKey(UpdateDictionaryForm updateDictionaryForm) throws CustomException;

    /**
     * 查询字典列表
     * @return
     */
    DictionaryTree selectByParentId(Integer parentId);

    /**
     * 根据id数组获取多个字典项
     * @param ids
     * @return
     */
    Map<String,Object> selectByIds(Integer[] ids) throws CustomException;


}
