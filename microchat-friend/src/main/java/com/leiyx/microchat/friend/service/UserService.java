package com.leiyx.microchat.friend.service;

import com.leiyx.microchat.friend.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("user-server")
public interface UserService {
    @GetMapping(value = "users/{username}")
    @Cacheable(cacheNames = "user-info")
    Optional<User> getUserByUsername(@PathVariable String username);

    @GetMapping(value = "users/{id}")
    @Cacheable(cacheNames = "user-info")
    Optional<User> getUserById(@PathVariable Integer id);
}
