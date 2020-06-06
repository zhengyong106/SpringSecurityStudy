package com.wecloud.security.distributed.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer implements AuthorizationServerConfigurer {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    public AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(authorizationServerTokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     *  /oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://fanyi.youdao.com
     *  /oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://fanyi.youdao.com
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in‐memory存储
                .withClient("c1")// 客户端id
                .secret(BCrypt.hashpw("secret", BCrypt.gensalt())) // 客户端秘钥
                .resourceIds("res1") // 资源列表
                .authorizedGrantTypes("authorization_code", "password","client_credentials","implicit","refresh_token") // 该client允许的授权类型
                .scopes("all")// 允许的授权范围
                .autoApprove(false) //加上验证回调地址
                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()") // oauth/token_key是公开
                .checkTokenAccess("permitAll()") // oauth/check_token公开
                .allowFormAuthenticationForClients(); // 表单认证（申请令牌）
    }
}
