package com.wecloud.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class TokenConfig {

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(new InMemoryClientDetailsService()); // 客户端信息服务
        tokenServices.setSupportRefreshToken(true); // 是否可刷新令牌
        tokenServices.setTokenStore(new InMemoryTokenStore()); // 设置令牌存储策略
        tokenServices.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        tokenServices.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天 return service;
        return tokenServices;
    }
}
