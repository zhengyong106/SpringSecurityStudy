package com.wecloud.security.distributed.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerTokenConfig {

    // 资源服务器令牌服务
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8888/oauth/check_token"); // 设置远程令牌认证URL路径
        services.setClientId("c1"); // 设置资源服务器id，需要和授权服务器配置一致
        services.setClientSecret("secret"); // 设置资源服务器密钥，需要和授权服务器配置一致
        return services;
    }

}
