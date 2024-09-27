package com.leiyx.microchat.messaging.event;

import com.leiyx.microchat.messaging.model.Message;

import java.time.Instant;

public class MessageEvent {
    private String type;
    private String senderUsername;
    private String senderId;
    private String receiverUsername;
    private String receiverId;
    private String content;
    private Instant timestamp = null;

    public MessageEvent(Message message, String senderId, String receiverId) {
        this.type = message.getType();
        this.senderUsername = message.getSenderUsername();
        this.senderId = senderId;
        this.receiverUsername = message.getReceiverUsername();
        this.receiverId = receiverId;
        this.content = message.getContent();
        this.timestamp = message.getTimestamp();
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
        return "MessageEvent{" +
                "type='" + type + '\'' +
                ", senderUsername='" + senderUsername + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
