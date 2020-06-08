package com.wecloud.security.distributed.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerTokenConfig {

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    // 令牌转换器，用于增强令牌安全性，这里使用非对称加密算法
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey("-----BEGIN PUBLIC KEY-----\n" +
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqPjGYGn9JbWK6pJcC0ba9oz1T\n" +
                "gIaUiycz19tjXnOqqQMvrmjy9FuFWBg7GnaC+L1uNhehfVrKmViSgD5FTIp/uPiW\n" +
                "kZjb0DgnzBKmEKqjk4O7lm64jwDnOOVfEeiVppk7wFzZw5P0hKpRgjrU+gZ5bGyP\n" +
                "iPSPyGYdWD346gCvDQIDAQAB\n" +
                "-----END PUBLIC KEY-----");
        return converter;
    }

    // 令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }
}
