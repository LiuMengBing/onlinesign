package com.example.msgnotice.login;

import java.util.Date;
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
		//WSServer.pushBySys(msg);
		return StandardResult.ok();
	}

	@Override
	public String testAjaxShortPolling() {
		/**
		 * 业务处理（查询数据库返回）
		 *
		 * 以消息提示为例：
		 * 查询数据库内当前登录用户是否有未读状态的消息并返回
		 */
		String returnStr = "response time: " + new Date() ;
		return returnStr;
	}

	@Override
	public String testAjaxLongPolling() {
		String returnStr = null;
		try {
			while (true) {
				Thread.sleep(3000);
				/**
				 * 业务处理（查询数据库返回）
				 *
				 * 以消息提示为例：
				 * 查询数据库内当前登录用户是否有未读状态的消息，
				 * 如果有未读状态的消息则返回；
				 * 没有则继续阻塞连接直到有未读状态的消息或者阻塞超时。
				 */
				returnStr = "response time: " + new Date() ;
				break;
			}
		}catch(Exception e){
			System.out.print("出现错误：" + e);
		}
		return returnStr;
	}


}
