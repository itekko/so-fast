package com.sofast.common.websocket;

import cn.hutool.json.JSONUtil;
import com.sofast.common.result.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket
 */
@ServerEndpoint(value = "/socketServer/{groupId}/{userid}")
@Component
public class SocketServer {

    private static final Logger log = LoggerFactory.getLogger(SocketServer.class);

    private static final Map<String,Map<String,Session>> contains = new ConcurrentHashMap<>();

    private Session session;
    /**
     * 用户连接时触发
     * @param session
     * @param userid
     */
    @OnOpen
    public void open(Session session,
                     @PathParam(value="groupId")String groupId,
                     @PathParam(value="userid")String userid){

        this.session = session;
        Map<String,Session> tribeMap = contains.get(groupId);
        if(tribeMap == null){
            tribeMap = new ConcurrentHashMap<>();
            contains.put(groupId,tribeMap);
        }
        tribeMap.put(userid,session);

        Result result = Result.build(10000,"上线成功!");
        sendMessage(groupId, JSONUtil.toJsonStr(result),userid);
        log.info("模块:{},用户ID:{}已上线",groupId,userid);
    }

    /**
     * 收到信息时触发
     * @param message
     */
    @OnMessage
    public void onMessage(String message,
                          @PathParam(value="groupId")String groupId,
                          @PathParam(value="userid")String userid){

        //sendMessage(groupId,message,userid);
        sendAll(groupId,message);
        log.info("模块:{},用户ID:{}===>信息:{}",groupId,userid,message);
    }

    /**
     * 连接关闭触发
     */
    @OnClose
    public void onClose(@PathParam(value="groupId")String groupId,
                        @PathParam(value="userid")String userid){
        Map<String,Session> tribeMap = contains.get(groupId);
        if(tribeMap == null){
            tribeMap = new ConcurrentHashMap<>();
            contains.put(groupId,tribeMap);
        }
        tribeMap.remove(userid);
        log.info("模块:{},用户ID:{}===>连接关闭",groupId,userid);
    }

    /**
     * 发生错误时触发
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        log.info(ExceptionUtils.getStackTrace(error));
    }

    /**
     *信息发送的方法
     * @param message
     * @param userId
     */
    public void sendMessage(String groupId,String message,String userId){
        Session s = contains.get(groupId).get(userId);
        if(s!=null){
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前连接数
     * @return
     */
    public int getOnlineNum(String groupId){
        Map<String,Session> tribeMap = contains.get(groupId);
        if(!CollectionUtils.isEmpty(tribeMap)){
            return  tribeMap.size();
        }

        return 0;
    }

    /**
     * 获取在线用户列表
     * @return
     */
    public  List<String> getOnlineUsers(String groupId){
        List<String> list = new ArrayList<>();
        Map<String,Session> tribeMap = contains.get(groupId);
        if(!CollectionUtils.isEmpty(tribeMap)){
            tribeMap.forEach((k,v)->{
                list.add(k);
            });
        }
        return list;
    }

    /**
     * 信息群发
     * @param msg
     */
    public void sendAll(String groupId, String msg) {
        Map<String,Session> tribeMap = contains.get(groupId);
        if(!CollectionUtils.isEmpty(tribeMap)){
            for (Map.Entry<String, Session> entry : tribeMap.entrySet()) {
                try {
                    entry.getValue().getBasicRemote().sendText(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 多个人发送给指定的几个用户
     * @param msg
     * @param userids  用户
     * @param groupId 模块
     */
    public  void SendMany(String groupId,String msg,String [] userids) {
        for (String userid : userids) {
            sendMessage(groupId,msg, userid);
        }

    }

    public void remove(String groupId,String userId){
        Map<String,Session> tribeMap = contains.get(groupId);
        if(tribeMap == null){
            tribeMap = new ConcurrentHashMap<>();
            contains.put(groupId,tribeMap);
        }
        tribeMap.remove(userId);
        log.info("模块:{},用户ID:{}===>连接被移除",groupId,userId);
    }

    /**
     * @Title: dismissTribe
     * @Description:
     * @param groupId void
     * @author ekko
     * @date 2018年12月13日下午4:47:16
     */
    public void dismissTribe(String groupId) {
        contains.remove(groupId);
        log.info("模块:{}===>连接被移除",groupId);
    }
}
