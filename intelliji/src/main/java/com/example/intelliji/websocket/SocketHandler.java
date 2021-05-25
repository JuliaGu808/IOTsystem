package com.example.intelliji.websocket;

import com.example.intelliji.dao.DeviceDao;
import com.example.intelliji.model.DeviceEsp32Dht11;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import org.springframework.web.socket.CloseStatus;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component

public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    List<WebSocketSession> deviceSessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        Map value = new Gson().fromJson(message.getPayload(), Map.class);
        System.out.println("handleTextMessage "+message.getPayload());
        DeviceEsp32Dht11 device = DeviceDao.DeviceConvert(value);
        DeviceDao.addData(device);
        deviceSessions.add(session);
        sessions.remove(session);
        for(WebSocketSession webSocketSession: sessions){
            webSocketSession.sendMessage(new TextMessage(""+device));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        System.out.println("afterConnectioneEstablished");
        sessions.add(session);
        System.out.println(sessions.toString());
        System.out.println("Antal aktiva sessioner:" + sessions.size());
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
    {
        exception.printStackTrace();
        System.out.println(session.getAttributes().toString());
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
    {
        System.out.println(status.toString());
        sessions.remove(session); //Ta bort stängd session ur sessions listan.
    }



}

