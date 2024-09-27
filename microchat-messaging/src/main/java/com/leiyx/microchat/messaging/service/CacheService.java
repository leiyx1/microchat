package com.leiyx.microchat.messaging.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CacheService {
    private static final Logger logger = Logger.getLogger(CacheService.class.getName());

    @CacheEvict(value = "friendship", key = "#id.toString() + ':' + #friendId.toString()")
    public void evictFriendshipCache(UUID id, UUID friendId) {
        logger.info("evict friendship cache " + id.toString() + ':' + friendId.toString());
    }

    @CacheEvict(value = "block", key = "#id.toString() + ':' + #friendId.toString()")
    public void evictBlockCache(UUID id, UUID friendId) {
        logger.info("evict block cache " + id.toString() + ':' + friendId.toString());
    }
}
