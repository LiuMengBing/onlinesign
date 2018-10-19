package com.example.msgnotice.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liumengbing on 2018/10/18 0018.
 */
@Controller
public class LoginController {

    LoginMemberService loginService = new LoginMemberServiceImpl();


    @RequestMapping(value = "/toLogin")
    @ResponseBody
    public StandardResult toLogin() {
        MemberLoginForm form = new MemberLoginForm();
        form.setUser("usertest");
        return loginService.toLogin(form);
    }

    @RequestMapping(value = "/tishi")
    @ResponseBody
    public StandardResult tishi() {
        return loginService.tishi();
    }

    /**
     * 测试ajax短轮询
     * @return
     */
    @RequestMapping(value = "/testAjaxShortPolling")
    @ResponseBody
    public String testAjaxShortPolling() {
        return loginService.testAjaxShortPolling();
    }
    /**
     * 测试ajax长轮询
     * @return
     */
    @RequestMapping(value = "/testAjaxLongPolling")
    @ResponseBody
    public String testAjaxLongPolling() {
        return loginService.testAjaxLongPolling();
    }

}
