package com.leiyx.microchat.friend.service;

import com.leiyx.microchat.friend.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="auth-server", url = "http://microchat-keycloak:8080/")
public interface AuthService {
    @GetMapping("admin/realms/microchat/users")
    List<User> getUsersByUsername(@RequestParam Map<String, String> params);
}
