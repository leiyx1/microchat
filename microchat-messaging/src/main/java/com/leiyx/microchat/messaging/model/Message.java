package com.leiyx.microchat.messaging.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leiyx.microchat.messaging.event.MessageEvent;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private String type;
    private String senderUsername;
    private String receiverUsername;
    private String content;
    private Instant timestamp = null;

    public Message() {}

    public Message(MessageEvent messageEvent) {
        this.type = messageEvent.getType();
        this.senderUsername = messageEvent.getSenderUsername();
        this.receiverUsername = messageEvent.getReceiverUsername();
        this.content = messageEvent.getContent();
        this.timestamp = messageEvent.getTimestamp();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
