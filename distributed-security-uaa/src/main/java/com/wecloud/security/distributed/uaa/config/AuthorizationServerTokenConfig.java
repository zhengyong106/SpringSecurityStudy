package com.wecloud.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
public class AuthorizationServerTokenConfig {

    @Resource
    private TokenStore tokenStore;

    // 令牌转换器，用于增强令牌安全性
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    // 令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    // 授权令牌服务
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true); // 是否可刷新令牌
        tokenServices.setTokenStore(tokenStore); // 设置令牌存储策略
        tokenServices.setAccessTokenValiditySeconds(2 * 60 * 60); // 令牌默认有效期2小时
        tokenServices.setRefreshTokenValiditySeconds(24 * 60 * 60); // 刷新令牌默认有效期3天 return service;
        return tokenServices;
    }
}
