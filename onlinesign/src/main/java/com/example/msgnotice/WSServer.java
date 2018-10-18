package com.example.msgnotice;

import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * @author : lmb</br>
 * @DESC : <p>注解{@link ServerEndpoint}声明websocket 服务端</p></br>
 * @date : 2017/5/25  9:43</br>
 */
@Component
@ServerEndpoint(value = "/chat")
public class WSServer {

    // 在线人数 线程安全
    private static int onlineCount = 0;

    // 连接集合 userId => server 键值对 线程安全
    static public final ConcurrentMap<String, WSServer> map = new ConcurrentHashMap<>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    // 当前会话的httpsession
    private HttpSession httpSession;


    /**
     * @param session websocket连接sesson
     * @param config  {@link com.github.websocket.configuration.HttpSessionConfigurator}
     * @DESC <p>注解{@link OnOpen} 声明客户端连接进入的方法</p>
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {

        // 得到httpSession
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        // 获取session对象 SObject(这个就是java web登入后的保存的session对象，此处为用户信息，包含了userId)
        String user = (String) this.httpSession.getAttribute("user");

        this.session = session;
        System.out.println(user+"-------"+this.session.getId());
        
        //针对一个用户只能有一个链接
        if(map.get(user)!=null){
        	// 移除连接
            map.remove(user);
            // 连接数-1
            subOnlineCount();	
        }

        // 将连接session对象存入map
        map.put(user, this);

        // 连接数+1
        addOnlineCount();

        System.out.print("有新的连接，当前连接数为：" + getOnlineCount());
    }


    /**
     * <p>{@link OnClose} 关闭连接</p>
     */
    @OnClose
    public void onClose() {

        /**
         * 获取当前连接信息 {@code CommonConstant.USER_LOGIN_SESSION} 为Http session 名
         */

        String user = (String) this.httpSession.getAttribute("user");

        // 移除连接
        map.remove(user);

        // 连接数-1
        subOnlineCount();

        System.out.print("有一连接断开，当前连接数为：" + getOnlineCount());
    }

    /**
     * <p>{@link OnMessage} 消息监听处理方法</p>
     *
     * @param message 消息对象{@link com.github.websocket.msg.Msg}的JSON对象
     * @throws IOException 异常
     */
    @OnMessage
    public void onMessage(String message) throws IOException {

        // 将消息转Msg对象
        Msg msg = JSON.parseObject(message, Msg.class);

        //TODO 可以对msg做些处理...

        // 根据Msg消息对象获取定点发送人的userId
        WSServer _client = map.get(msg.getToUid());

        // 定点发送
        if (StringUtils.isEmpty(msg.getToUid())) {
            if (null != _client) {
                // 是否连接判断
                if (_client.session.isOpen())
                    // 消息发送
                    _client.session.getBasicRemote().sendText(JSON.toJSONString(msg));
            }
        }

        // 群发
        if (StringUtils.isEmpty(msg.getToUid())) {
            // 群发已连接用户
            for (WSServer client : map.values()) {
                client.session.getBasicRemote().sendText(JSON.toJSONString(msg));
            }
        }

    }

    /**
     * <p>{@link OnError} websocket系统异常处理</p>
     *
     * @param t 异常
     */
    @OnError
    public void onError(Throwable t) {
        System.out.print(t);
        t.printStackTrace();
    }

    /**
     * <p>系统主动推送 这是个静态方法在web启动后可在程序的其他合适的地方和时间调用，这就实现了系统的主动推送</p>
     *
     * @param msg 消息对象{@link com.github.websocket.msg.Msg}的JSON对象
     */
    static public void pushBySys(Msg msg) {

        //TODO 也可以实现定点推送
    	//msg传输的时候会带上需要发送消息给谁msg.getToUid()
    	//通过map去获取那个用户所在的session
    	WSServer ws=map.get(msg.getToUid());
    	try {
			if(ws!=null){
				ws.session.getBasicRemote().sendText("123456");
			}
		} catch (IOException e1) {
            System.out.print(e1);
			e1.printStackTrace();
		}
    	
        // 群发
        /*for (WSServer client : map.values()) {
            try {
                client.session.getBasicRemote().sendText(JSON.toJSONString(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    // 获取连接数
    private static synchronized int getOnlineCount() {
        return WSServer.onlineCount;
    }

    // 增加连接数
    private static synchronized void addOnlineCount() {
        WSServer.onlineCount++;
    }

    // 减少连接数
    private static synchronized void subOnlineCount() {
        WSServer.onlineCount--;
    }

}