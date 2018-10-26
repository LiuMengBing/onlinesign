package com.example.configuremanager.pagehelper;

import com.example.common.ResponseModel;
import com.example.configuremanager.pagehelper.bean.SelectMsgList;
import com.example.configuremanager.pagehelper.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liumengbing on 2018/10/22.
 */
@RestController
@RequestMapping("/message")
@Api(value = "messagecontroller", description = "消息管理相关接口")
public class MessageController {

    @Resource
    private MessageService msgService;

    @PostMapping("/selectList")
    @ApiOperation("pageHelper分页获取消息列表")
    public ResponseModel selectList(SelectMsgList msg){

        Integer pageNum = msg.getPageNum()!=null?msg.getPageNum():1;//页码数
        Integer pageSize = msg.getPageSize()!=null?msg.getPageSize():10;//每页数量
        Page page = PageHelper.startPage(pageNum, pageSize, true);

        ResponseModel respModel = new ResponseModel();
        List<SelectMsgList> list = msgService.selectList(msg);

        Map<String,Object> data = new HashMap<>();
        data.put("total", page.getTotal());//总数
        data.put("nowPage", pageNum);//当前页
        data.put("data", list);//数据集

        if(list.size() >= 0){
            respModel.setStatus(200);
            respModel.setMessage("查询成功");
            respModel.setData(data);
        }else{
            respModel.setStatus(500);
            respModel.setMessage("失败");
        }
        return respModel;
    }



}