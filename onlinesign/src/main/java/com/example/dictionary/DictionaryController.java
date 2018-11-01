package com.example.dictionary;

import com.example.common.ResponseModel;
import com.example.common.exception.CustomException;
import com.example.dictionary.bean.DictionaryTree;
import com.example.dictionary.bean.form.AddDictionaryForm;
import com.example.dictionary.bean.form.UpdateDictionaryForm;
import com.example.dictionary.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Api(tags = "数据字典")
@RequestMapping("dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 添加字典或字典项
     * @param addDictionaryForm
     * @return
     */
    @PostMapping
    @ApiOperation("添加字典或字典项")
    public ResponseModel addDictionary(@Validated AddDictionaryForm addDictionaryForm) throws CustomException {
        int i = dictionaryService.insert(addDictionaryForm);
        if(i == 1){
            return ResponseModel.newFailure(200,"添加成功");
        }else {
            return ResponseModel.newFailure(500,"添加失败");
        }
    }

    /**
     * 更新字典或字典项
     * @param updateDictionaryForm
     * @return
     */
    @PutMapping
    @ApiOperation("更新字典或字典项")
    public ResponseModel updateDictionary(@Validated UpdateDictionaryForm updateDictionaryForm)throws CustomException{
        int i = dictionaryService.updateByPrimaryKey(updateDictionaryForm);
        if(i == 1){
            return ResponseModel.newFailure(200,"修改成功");
        }else {
            return ResponseModel.newFailure(500,"修改失败");
        }
    }

    /**
     * 获取字典或字典项列表
     * @param parentId
     * @return
     */
    @GetMapping
    @ApiOperation("字典或字典项列表")
    public ResponseModel searchDictionary(Integer parentId){
        DictionaryTree dictionaryList = dictionaryService.selectByParentId(parentId);
        return ResponseModel.newSuccess(dictionaryList,"成功");
    }

    /**
     * 根据id数组获取多个字典项
     * @param ids
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据id数组获取多个字典项")
    public ResponseModel getDictionaryList(Integer[] ids) throws CustomException{
        Map<String,Object> dictionarys = dictionaryService.selectByIds(ids);
        return ResponseModel.newSuccess(dictionarys,"成功");
    }
}
