package com.example.configuremanager.springexceptionhandler.exception;

import com.example.configuremanager.springexceptionhandler.exception.BizException;
import com.example.configuremanager.springexceptionhandler.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by liumengbing on 2018/11/01.
 */
@RestController
@RequestMapping("/exception")
@Api(value = "exceptioncontroller", description = "异常测试接口")
public class ExceptionController {

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ApiOperation("异常测试接口")
    public ModelAndView getPages(@PathVariable(value = "type") String type) throws Exception{
        if ("error".equals(type)) {
            // 由RequestExceptionHandler处理
            throw new BizException(1000, "测试BizException");
            /**前端看到的返回：
             {
             "status": 1000,
             "message": "测试BizException",
             "data": null
             }
             */
        } else if ("io-error".equals(type)) {
            // 由RequestExceptionHandler处理
            throw new IOException();
            /**前端看到的返回：
             {
             "status": 500,
             "message": null,
             "data": null
             }
             */
        } else {
            // 由RequestExceptionHandler处理
            throw new CustomException(1002,"测试CustomException");
            /**前端看到的返回：
             {
             "status": 1002,
             "message": "测试CustomException",
             "data": null
             }
             */
        }
    }
}
