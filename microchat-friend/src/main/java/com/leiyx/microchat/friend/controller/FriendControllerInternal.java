package com.leiyx.microchat.friend.controller;

import com.leiyx.microchat.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/internal/v1")
public class FriendControllerInternal {

    private final FriendService friendService;

    @Autowired
    public FriendControllerInternal(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping(value = "friends/{userId}/{friendId}")
    public boolean isFriend(
            @PathVariable UUID userId,
            @PathVariable UUID friendId) {
        return friendService.isFriend(userId, friendId);
    }

    @GetMapping(value = "blocks/{userId}/{friendId}")
    public boolean isBlocked(
            @PathVariable UUID userId,
            @PathVariable UUID friendId) {
        return friendService.isBlocked(userId, friendId);
    }
}
