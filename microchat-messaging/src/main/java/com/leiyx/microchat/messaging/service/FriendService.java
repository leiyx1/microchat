package com.leiyx.microchat.messaging.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface FriendService {
    @Cacheable(value = "friendship", key = "#id.toString() + ':' + #friendId.toString()")
    @GetExchange(value = "/api/internal/v1/friends/{id}/{friendId}")
    boolean isFriend(@PathVariable String id, @PathVariable String friendId);

    @Cacheable(value = "block", key = "#id.toString() + ':' + #friendId.toString()")
    @GetExchange(value = "/api/internal/v1/blocks/{id}/{friendId}")
    boolean isBlock(@PathVariable String id, @PathVariable String friendId);
}
