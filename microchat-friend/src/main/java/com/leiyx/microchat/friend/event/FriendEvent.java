package com.leiyx.microchat.friend.event;

import java.util.UUID;

public class FriendEvent {
    private FriendEventType type;
    private UUID userId;
    private UUID friendId;

    public FriendEvent(FriendEventType type, UUID userId, UUID friendId) {
        this.type = type;
        this.userId = userId;
        this.friendId = friendId;
    }

    public FriendEventType getType() {
        return type;
    }

    public void setType(FriendEventType type) {
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFriendId() {
        return friendId;
    }

    public void setFriendId(UUID friendId) {
        this.friendId = friendId;
    }
}
