package com.leiyx.microchat.messaging.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leiyx.microchat.messaging.event.FriendEvent;
import com.leiyx.microchat.messaging.event.FriendEventType;
import com.leiyx.microchat.messaging.event.MessageEvent;
import com.leiyx.microchat.messaging.model.Message;
import com.leiyx.microchat.messaging.websocket.MessagingSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class StreamConfiguration {

    @Bean
    public Consumer<FriendEvent> friendEvent(CacheManager cacheManager) {
        return friendEvent -> {
            if (friendEvent.getType() == FriendEventType.ADD
             || friendEvent.getType() == FriendEventType.DELETE) {
                cacheManager.getCache("friendship").evict(friendEvent.getUserId() + ":" + friendEvent.getFriendId());
            } else if (friendEvent.getType() == FriendEventType.BLOCK) {
                cacheManager.getCache("block").evict(friendEvent.getUserId() + ":" + friendEvent.getFriendId());
            }
        };
    }

    @Bean
    public Consumer<MessageEvent> messageEvent(ObjectMapper mapper) {
        return messageEvent -> {
            Optional<ChannelHandlerContext> outCtx = MessagingSession.getChannelHandlerContext(messageEvent.getReceiverId());

            if (outCtx.isPresent() && outCtx.get().channel().isActive()) {
                Message message = new Message(messageEvent);
                try {
                    outCtx.get().writeAndFlush(new TextWebSocketFrame(mapper.writeValueAsString(message)));
                } catch (JsonProcessingException ignored) {
                }
            }
        };
    }

}
