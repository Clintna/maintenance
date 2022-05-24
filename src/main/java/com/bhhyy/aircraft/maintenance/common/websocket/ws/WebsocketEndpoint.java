package com.bhhyy.aircraft.maintenance.common.websocket.ws;

import com.alibaba.fastjson.JSONObject;
import com.bhhyy.aircraft.maintenance.common.websocket.pojo.Message;
import com.bhhyy.aircraft.maintenance.common.websocket.pojo.ReqMessageBody;
import com.bhhyy.aircraft.maintenance.common.websocket.util.MessageUtils;
import com.bhhyy.aircraft.maintenance.staff.bean.StUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jinglin
 * @date 2022年5月18日10:48:57
 */


@ServerEndpoint(value = "/wstest", configurator = GetHttpSessionConfig.class)
@Component
@Slf4j
public class WebsocketEndpoint {

    private final static Logger LOGGER = LogManager.getLogger(WebsocketEndpoint.class);

    /**
     * 用来储存在线用户的容器
     */
    public static Map<String, WebsocketEndpoint> onlineUsers = new ConcurrentHashMap<>();

    /**
     * 用来给客户端发送消息
     */
    private Session session;

    /**
     * 用来获取在登录成功后，放在httpsession域中存放的username
     */
    private HttpSession httpSession;

    /*建立时调用*/
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {

        this.session = session;
        //获取httpsession对象
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        //获取httpsession域中存放的username对应的值
        StUserInfo loginUserInfo = (StUserInfo) httpSession.getAttribute("loginUserInfo");
        log.info("用户{}登录成功,时间：{}", loginUserInfo.getUserName(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //存放到onlineUsers中保存
        onlineUsers.put(loginUserInfo.getUserName(), this);
        //系统消息推送所有在线用户给客户端
        //封装系统推送消息,前端onmessage接收的数据
//        String message = MessageUtils.getMessage(true, null, getAllOnlineUsername());
//        broadcastMsgToAllOnlineUsers(message);
    }

    /**
     * .
     *
     * @param reqMessageBody 给客户端发送消息
     * @return void
     */
    public static void broadcastMsgToAllOnlineUsers(ReqMessageBody reqMessageBody) {
        //所有登录用户名称
        Set<String> names = onlineUsers.keySet();
        for (String name : names) {
            WebsocketEndpoint chatEndpoint = onlineUsers.get(name);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                basicRemote.sendText(JSONObject.toJSONString(reqMessageBody));
            } catch (IOException e) {
                log.error("广播发送系统消息失败！e:{}", (Object) e.getStackTrace());
                e.printStackTrace();
            }
        }
    }

    /**
     * @return java.lang.Object
     */
    private Object getAllOnlineUsername() {
        return WebsocketEndpoint.onlineUsers.keySet();
    }


    /**
     * 接收到客户端发送的数据时调用.
     *
     * @param message 客户端发送的数据
     * @param session session对象
     * @return void
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            //获取接收信息的用户
            String toName = msg.getToName();
            //获取发送的消息
            String msgData = msg.getMessage();
            //获取当前登录的用户
            String username = (String) httpSession.getAttribute("username");
            //封装发送的消息
            ReqMessageBody sendMsg = MessageUtils.getMessage(false, username, msgData);
            //发送消息
            onlineUsers.get(toName).session.getBasicRemote().sendText(JSONObject.toJSONString(sendMsg));
        } catch (JsonProcessingException e) {
            log.error("接收客户端的消息，转换出错了！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 关闭时调用
     */
    @OnClose
    public void onClose(Session session) {
        String username = (String) httpSession.getAttribute("username");
        //
        WebsocketEndpoint remove = onlineUsers.remove(username);
        //广播
        ReqMessageBody message = MessageUtils.getMessage(true, null, getAllOnlineUsername());
        broadcastMsgToAllOnlineUsers(message);
    }

}
