package com.wecloud.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

    @Resource
    private DataSource dataSource;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    public AuthorizationServerTokenServices authorizationServerTokenServices;

    // 授权码服务
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    // 授权服务端点配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager) // 配置认证管理器
                .tokenServices(authorizationServerTokenServices) // 配置令牌服务
                .authorizationCodeServices(authorizationCodeServices) // 配置授权码服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // 允许的服务端点令牌请求方式
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 基于数据库访问客户端详情
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()") // 开放 oauth/token_key
                .checkTokenAccess("permitAll()") // 开放 oauth/check_token公开
                .allowFormAuthenticationForClients(); // 允许通过client_id 和 client_secret进行授权
    }
}
