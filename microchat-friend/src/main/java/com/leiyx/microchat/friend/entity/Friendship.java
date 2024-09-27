package com.leiyx.microchat.friend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.UUID;

class FriendshipId implements Serializable {
    private UUID userId;
    private UUID friendId;
}

@Entity
@IdClass(FriendshipId.class)
public class Friendship {
    @Id
    private UUID userId;
    @Id
    private UUID friendId;

    public Friendship() {
    }

    public Friendship(UUID userId, UUID friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
