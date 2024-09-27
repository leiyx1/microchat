package com.leiyx.microchat.friend.dao;

import com.leiyx.microchat.friend.entity.Block;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface BlockRepository extends Repository<Block, Integer> {
    boolean existsByUserIdAndBlockedUserId(UUID userId, UUID blockedUserId);
    Long save(Block block);
    Long deleteByUserIdAndBlockedUserId(UUID userId, UUID blockedUserId);
}
