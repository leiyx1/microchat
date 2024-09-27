package com.leiyx.microchat.messaging.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.leiyx.microchat.messaging.configuration.KeepAsJsonDeserializer;

public class Signal {
    String type;
    String receiverId;
    String senderId;
    @JsonDeserialize(using = KeepAsJsonDeserializer.class)
    String signal;

    public Signal() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "type='" + type + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", signal='" + signal + '\'' +
                '}';
    }
}
