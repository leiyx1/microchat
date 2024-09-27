package com.leiyx.microchat.messaging.service;

import com.leiyx.microchat.messaging.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

public interface UserService {
    @GetExchange("admin/realms/microchat/users")
    List<User> getUsersByUsername(@RequestParam Map<String, String> params);

    @GetExchange("admin/realms/microchat/users/{id}")
    User getUserById(@PathVariable String id);
}
