package com.wecloud.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
public class AuthorizationTokenConfig {

    @Resource
    private TokenStore tokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    // 令牌转换器，用于增强令牌安全性，这里使用非对称加密算法
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 定义签名key
        converter.setSigningKey("-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIICXQIBAAKBgQDqPjGYGn9JbWK6pJcC0ba9oz1TgIaUiycz19tjXnOqqQMvrmjy\n" +
                "9FuFWBg7GnaC+L1uNhehfVrKmViSgD5FTIp/uPiWkZjb0DgnzBKmEKqjk4O7lm64\n" +
                "jwDnOOVfEeiVppk7wFzZw5P0hKpRgjrU+gZ5bGyPiPSPyGYdWD346gCvDQIDAQAB\n" +
                "AoGANeDm0uPbXcYPhLs5ceBPXmi3AScprW6hboMKtx1BUSKNdVDyWlm62F22Oxf/\n" +
                "5Om2VqyR/GbjNIEXlqDxUcrEeyInT/7bfRICFKkOiPqFrpd2aoVvq+DmeZvwNnv4\n" +
                "0XdR4+ZxvASXTtQnRENl0XlRN+3kAoIVSL7fUKZjN73Oq8ECQQD0A7+F8RYXlJ5A\n" +
                "ZjsnLdU4yaGDMlVwvwbor0BE9DkJfubQMTby7ExBpFzXMhzc5+smuL//OQ6o0369\n" +
                "BndYSOtBAkEA9b+TZ7oQV8WQNXzbdG2O2YkyTJDdtLVs+jouM2AvdGB0Fko0kA5Z\n" +
                "IuLlhWUdf2G1kol2SMx6J0/AC73WGYVMzQJBAIhY/8/MItgSR0sc1LjTt2z/3av9\n" +
                "mBhj5WxnYLYm8SgJLNfwaA/dUc7sFKR6b/5P6Om+Gip2q2ZSp2LgFhfSxwECQQDG\n" +
                "cAPfZmd7+JQSHNg4k58eGaL8IJ5mJXnyNLjJ04l+N8SFmzYGfQ+NFBqyNIw1J/ku\n" +
                "csGI11RsGgHBeXFA+lqhAkB7KmmqqmvtsqyA4V9AxMERJh2vQ91uWk26INXNUuEB\n" +
                "Z6Ao8qSRfXtAcLJCGA6izLs5sh2RjqPS7jnshLvSxywE\n" +
                "-----END RSA PRIVATE KEY-----");
        // 定义校验key
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

    // 授权令牌服务
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true); // 是否可刷新令牌
        tokenServices.setTokenStore(tokenStore); // 设置令牌存储策略
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter); // 令牌转换器，用于增强令牌安全性
        tokenServices.setAccessTokenValiditySeconds(2 * 60 * 60); // 令牌默认有效期2小时
        tokenServices.setRefreshTokenValiditySeconds(24 * 60 * 60); // 刷新令牌默认有效期3天 return service;
        return tokenServices;
    }
}
