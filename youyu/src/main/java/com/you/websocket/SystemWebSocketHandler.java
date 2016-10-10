/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.you.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.Session;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 *
 * @author dayu
 */
@Component
public class SystemWebSocketHandler implements WebSocketHandler {
	private Session session;
	private WebSocketSession websession;
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<SystemWebSocketHandler> webSocketSet = new CopyOnWriteArraySet<SystemWebSocketHandler>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connect to the websocket success......");
        System.out.println("go to the solution");
        System.out.println(this.getClass().getName());
        this.websession = session;
        webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        session.sendMessage(new TextMessage("Server:connected OK!"));
    }
    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
    	
    	TextMessage returnMessage = new TextMessage(wsm.getPayload()
				+ " received at server");
        for(SystemWebSocketHandler item: webSocketSet){
			try {
				System.out.println(webSocketSet.size()+"hhhhhhh");
				item.websession.sendMessage(returnMessage);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
        System.out.println(wsm.getPayload()+"!!!!!!!!");
		//wss.sendMessage(returnMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if(wss.isOpen()){
            wss.close();
        }
       System.out.println("websocket connection closed......");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        System.out.println("websocket connection closed......");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    public static synchronized void addOnlineCount() {
    	SystemWebSocketHandler.onlineCount++;
	}
    public static synchronized int getOnlineCount() {
		return onlineCount;
	}
    public void sendMess(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}
}
