package com.example.intelliji.websocket;

import com.example.intelliji.dao.DeviceDao;
import com.example.intelliji.model.DeviceEsp32Dht11;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
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

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {
        Map value = new Gson().fromJson(message.getPayload(), Map.class);
        System.out.println("handleTextMessage "+message.getPayload());
        DeviceEsp32Dht11 device = DeviceDao.DeviceConvert(value);
        DeviceDao.addData(device);
        for(WebSocketSession webSocketSession: sessions){
            webSocketSession.sendMessage(new TextMessage(""+device));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        System.out.println("afterConnectioneEstablished");
        sessions.add(session);
    }
}

