package com.example.msgnotice.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/10/18 0018.
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

}
