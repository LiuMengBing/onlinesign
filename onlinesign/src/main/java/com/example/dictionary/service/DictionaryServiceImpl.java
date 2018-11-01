package com.example.dictionary.service;

import com.example.common.exception.CustomException;
import com.example.dictionary.bean.Dictionary;
import com.example.dictionary.bean.DictionaryTree;
import com.example.dictionary.bean.form.AddDictionaryForm;
import com.example.dictionary.bean.form.UpdateDictionaryForm;
import com.example.dictionary.dao.DictionaryMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

//    @Autowired
//    private RedisTemplate redisTemplate;

    /**
     * 添加字典及字典项
     * @param addDictionaryForm
     * @return
     */
    @Override
    public int insert(AddDictionaryForm addDictionaryForm)  throws CustomException {
        Dictionary dictionary = new Dictionary();

        //根据parentId获取当前字典的字典项的最大key
        Integer parentId = addDictionaryForm.getParentId();
        if(parentId != 0){
            Dictionary pDict = dictionaryMapper.selectByPrimaryKey(addDictionaryForm.getParentId());
            if(pDict == null){
                throw new CustomException(403,"未找到字典");
            }
        }

        Dictionary dict = dictionaryMapper.selectOneByParentId(parentId);
        Integer key = 0;
        if(dict == null){ //没有子节点
            key = 1;
        }else {
            if(dict.getReadonly())
                throw new CustomException(403,"只读字典项无法添加");
            if(addDictionaryForm.getType().equals(0)){
                key = dict.getKey()+1;
            }else if(addDictionaryForm.getType().equals(1)){
                key = dict.getKey()*2;
            }else {
                throw new CustomException(400,"请输入正确的type:0 key自增1；1 key翻倍");
            }
        }

        //准备要添加的数据
        dictionary.setKey(key);
        dictionary.setValue(addDictionaryForm.getValue());
        dictionary.setParentId(parentId);
        dictionary.setSort(addDictionaryForm.getSort());
        dictionary.setNote(addDictionaryForm.getNote());
        dictionary.setCreateBy("lmb");
        dictionary.setUpdateBy("lmb");

        int i = dictionaryMapper.insert(dictionary);
//        redisTemplate.opsForValue().increment("dic:version",1);
//        redisTemplate.delete("dic::DictionaryService:selectByParentId:"+ parentId +"/");
//        redisTemplate.delete("dic::DictionaryService:selectByParentId:0/");

        return  i;
    }

    /**
     * 修改字典及字典项
     * @param updateDictionaryForm
     * @return
     * @throws CustomException
     */
    @Override
    public int updateByPrimaryKey(UpdateDictionaryForm updateDictionaryForm) throws CustomException{

//        //获取登录人员信息
//        Employee e = authService.getMe();

        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(updateDictionaryForm.getId());
        if(dictionary == null)
            throw new CustomException(400,"字典信息未找到");
        if(dictionary.getReadonly())
            throw new CustomException(403,"只读字典项无法修改");
        Integer parentId = dictionary.getParentId();
        Dictionary param = new Dictionary();
        //准备要修改的数据
        param.setId(updateDictionaryForm.getId());
        param.setValue(updateDictionaryForm.getValue());
        param.setSort(updateDictionaryForm.getSort());
        param.setNote(updateDictionaryForm.getNote());
        param.setIsDel(updateDictionaryForm.getState());
//        param.setUpdateBy(e.getEmpname());

        int i = dictionaryMapper.updateByPrimaryKey(param);
//        redisTemplate.opsForValue().increment("dic:version",1);
//        redisTemplate.delete("dic::DictionaryService:selectByParentId:"+parentId+"/");
//        redisTemplate.delete("dic::DictionaryService:selectByParentId:0/");

        return i;
    }

    /**
     * 查询字典及字典项树
     * @param parentId
     * @return
     */
    @Override
    @Cacheable("dic")
    public DictionaryTree selectByParentId(Integer parentId){
        DictionaryTree dictionaryTree = new DictionaryTree();
        //若没有传parentId，默认为0
        if(parentId == null) {
            parentId = 0;
        }

        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(parentId);
        if(dictionary == null && parentId != 0){
            return new DictionaryTree();
        }
        if(parentId == 0){
            dictionaryTree.setId(0);
            dictionaryTree.setChildren(getTreeList(parentId,dictionaryMapper.selectAll()));
        }else {
            dictionaryTree.setId(dictionary.getId());
            dictionaryTree.setParentId(dictionary.getParentId());
            dictionaryTree.setSort(dictionary.getSort());
            dictionaryTree.setNote(dictionary.getNote());
            dictionaryTree.setKey(dictionary.getKey());
            dictionaryTree.setValue(dictionary.getValue());
            dictionaryTree.setChildren(getTreeList(parentId,dictionaryMapper.selectAll()));
        }
        return dictionaryTree;
    }

    /**
     * 根据id数组获取多个字典项
     * @param ids
     * @return
     * @throws CustomException
     */
    @Override
    public Map<String, Object> selectByIds(Integer[] ids) throws CustomException{
        if(ids.length == 0){
            throw new CustomException(500,"ids数组不能为空");
        }

        Map<String,Object> result = new HashMap<>();
        for(Integer id : ids)
            result.put(id.toString(),selectByParentId(id));
        return result;
    }


    /**
     * 使用递归方法建树
     * @param dictionaryList
     * @return
     */
    public static List<DictionaryTree> getTreeList(Integer id, List<Dictionary> dictionaryList) {
        List<DictionaryTree> trees = new ArrayList<>();
        //最高级节点，只返回一层下级节点
        if(id == 0) {
            for (Dictionary dictionary : dictionaryList) {
                if (dictionary.getParentId().equals(id)) {
                    DictionaryTree tree = new DictionaryTree();

                    tree.setChildren(new ArrayList<>());
                    tree.setId(dictionary.getId());
                    tree.setKey(dictionary.getKey());
                    tree.setNote(dictionary.getNote());
                    tree.setParentId(dictionary.getParentId());
                    tree.setSort(dictionary.getSort());
                    tree.setValue(dictionary.getValue());
                    tree.setReadonly(dictionary.getReadonly());

                    trees.add(tree);
                }
            }
        }else {
            for (Dictionary dictionary : dictionaryList) {
                //找到父级节点下的所有子节点
                if (dictionary.getParentId().equals(id)) {
                    DictionaryTree tree = new DictionaryTree();

                    tree.setChildren(new ArrayList<>());
                    tree.setId(dictionary.getId());
                    tree.setKey(dictionary.getKey());
                    tree.setNote(dictionary.getNote());
                    tree.setParentId(dictionary.getParentId());
                    tree.setSort(dictionary.getSort());
                    tree.setValue(dictionary.getValue());
                    tree.setReadonly(dictionary.getReadonly());

                    tree.setChildren(findChildren(tree,dictionaryList).getChildren());

                    trees.add(tree);
                }
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param dictionaryTree,dictionaryList
     * @return
     */
    public static DictionaryTree findChildren(DictionaryTree dictionaryTree,List<Dictionary> dictionaryList) {
        for (Dictionary dictionary : dictionaryList) {
            //找到子节点下的所有子节点
            if(dictionary.getParentId().equals(dictionaryTree.getId())) {
                DictionaryTree tree = new DictionaryTree();
                tree.setId(dictionary.getId());
                tree.setKey(dictionary.getKey());
                tree.setNote(dictionary.getNote());
                tree.setParentId(dictionary.getParentId());
                tree.setSort(dictionary.getSort());
                tree.setValue(dictionary.getValue());
                tree.setChildren(new ArrayList<>());

                dictionaryTree.getChildren().add(findChildren(tree,dictionaryList));
            }
        }
        return dictionaryTree;
    }
}
