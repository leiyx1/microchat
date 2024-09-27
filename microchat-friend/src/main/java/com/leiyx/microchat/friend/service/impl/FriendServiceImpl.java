package com.leiyx.microchat.friend.service.impl;

import com.leiyx.microchat.friend.dao.BlockRepository;
import com.leiyx.microchat.friend.dao.FriendRequestRepository;
import com.leiyx.microchat.friend.dao.FriendshipRepository;
import com.leiyx.microchat.friend.entity.*;
import com.leiyx.microchat.friend.event.FriendEvent;
import com.leiyx.microchat.friend.event.FriendEventType;
import com.leiyx.microchat.friend.service.FriendService;
import com.leiyx.microchat.friend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private UserService userService;

    @Autowired
    StreamBridge streamBridge;

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private BlockRepository blockRepository;


    @Override
    public List<User> getFriends(UUID userId) {
        return friendshipRepository.getFriendshipByUserId(userId)
                .stream()
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public boolean isFriend(UUID userId, UUID friendId) {
        return friendshipRepository.existsByUserIdAndFriendId(userId, friendId);
    }

    @Override
    public boolean deleteFriend(UUID userId, UUID friendId) {
        friendshipRepository.deleteByUserIdAndFriendId(userId, friendId);
        streamBridge.send("friend-event", new FriendEvent(FriendEventType.DELETE, userId, friendId));
        return true;
    }

    public boolean addFriend(UUID userId, UUID friendId) {
        // If is blocked, don't create friend request
        if (isBlocked(userId, friendId))
            return false;

        if (isFriend(friendId, userId)) {
            if (isFriend(userId, friendId)) {
                // If there is already a mutual friendship, don't create friend request
                return false;
            } else {
                // If you deleted a user and add back, resume friendship without creating request
                friendshipRepository.save(new Friendship(userId, friendId));
                return true;
            }
        } else {
            // Create friend request if you are not the user's friend
            return addFriendRequest(userId, friendId);
        }
    }

    private boolean addFriendRequest(UUID userId, UUID friendId) {
        friendRequestRepository.save(new FriendRequest(userId, friendId));
        return true;
    }


    public List<FriendRequest> getFriendRequests(UUID userId) {
        return friendRequestRepository.getByReceiverId(userId);
    }

    public boolean acceptFriendRequest(UUID userId, UUID requestId) {
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        System.out.println(friendRequest);
        if (friendRequest == null)
            return false;

        if (!friendRequest.getReceiverId().equals(userId))
            return false;

        if (!friendRequest.getStatus().equals(FriendRequestStatus.PENDING))
            return false;

        friendRequest.setStatus(FriendRequestStatus.APPROVED);
        friendRequestRepository.save(friendRequest);
        friendshipRepository.save(new Friendship(userId, friendRequest.getSenderId()));
        if (!isFriend(friendRequest.getSenderId(), userId))
            friendshipRepository.save(new Friendship(friendRequest.getSenderId(), userId));
        return true;
    }

    public boolean declineFriendRequest(UUID userId, UUID requestId) {
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        if (friendRequest == null)
            return false;

        if (!friendRequest.getReceiverId().equals(userId))
            return false;

        if (!friendRequest.getStatus().equals(FriendRequestStatus.PENDING))
            return false;

        friendRequest.setStatus(FriendRequestStatus.DECLINED);
        friendRequestRepository.save(friendRequest);
        return true;
    }

    public List<User> getBlockedUsers(UUID userId) {
        return List.of();
    }

    public boolean isBlocked(UUID userId, UUID friendId) {
        return blockRepository.existsByUserIdAndBlockedUserId(userId, friendId);
    }

    @Override
    public boolean blockUser(UUID userId, UUID friendId) {
        return blockRepository.save(new Block(userId, friendId)) > 0;
    }

    @Override
    public boolean unblockUser(UUID userId, UUID friendId) {
        return blockRepository.deleteByUserIdAndBlockedUserId(userId, friendId) > 0;
    }
}
