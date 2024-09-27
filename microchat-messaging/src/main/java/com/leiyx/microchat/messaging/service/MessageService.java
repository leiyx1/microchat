package com.leiyx.microchat.messaging.service;

import com.leiyx.microchat.messaging.event.MessageEvent;

public interface MessageService {
    public void sendMessage(MessageEvent messageEvent);
}
