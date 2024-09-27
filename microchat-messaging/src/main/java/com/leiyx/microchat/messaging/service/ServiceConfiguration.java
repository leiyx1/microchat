package com.leiyx.microchat.messaging.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ServiceConfiguration {
    @Bean
    public UserService userService(WebClient.Builder builder) {
        WebClient webClient = builder.baseUrl("http://microchat-keycloak:8080").build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(UserService.class);
    }

    @Bean
    public FriendService friendService(WebClient.Builder builder, EurekaClient eurekaClient) {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROCHAT-FRIEND", false);
        WebClient webClient = builder.baseUrl(instance.getHomePageUrl()).build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(FriendService.class);
    }
}
