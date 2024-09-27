package com.leiyx.microchat.messaging.service;

import com.leiyx.microchat.messaging.event.MessageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    StreamBridge streamBridge;

    @Override
    public void sendMessage(MessageEvent messageEvent) {
        streamBridge.send("message-event", "hello");
    }
}
