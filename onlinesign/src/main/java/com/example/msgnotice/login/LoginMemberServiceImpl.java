package com.example.msgnotice.login;

import java.util.HashMap;
import java.util.Map;

import com.example.msgnotice.Msg;
import com.example.msgnotice.WSServer;
import com.example.msgnotice.WebUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginMemberServiceImpl implements LoginMemberService{
	
	@Override
//	@Transactional(readOnly = false)
	public StandardResult toLogin(MemberLoginForm loginForm) {
		WebUtil.getSession().setAttribute("user", "usertest");
		Map<String,Object> map=new HashMap<>();
		map.put("sessionId", WebUtil.getSession().getId());
		map.put("user", "usertest");
		System.out.println("调用登录方法："+WebUtil.getSession().getId()+"usertest");
		return StandardResult.ok(map);
	}
	
	@Override
//	@Transactional(readOnly = false)
	public StandardResult tishi() {
		Msg msg=new Msg();
		msg.setToUid("ppp");
		WSServer.pushBySys(msg);
		return StandardResult.ok();
	}
	

}
