package com.wecloud.security.distributed.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Resource
    private ResourceServerTokenServices resourceServerTokenServices;

    // 资源服务器令牌服务
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8888/oauth/check_token"); // 设置远程令牌认证URL路径
        services.setClientId("c1"); // 设置资源服务器id，需要和授权服务器配置一致
        services.setClientSecret("secret"); // 设置资源服务器密钥，需要和授权服务器配置一致
        return services;
    }

    // 资源服务器配置
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("res1") // 配置资源id，这里的资源id和授权服务器中的资源id一致
                .tokenServices(resourceServerTokenServices) // 设置资源服务器令牌服务
                .stateless(true); // 设置资源为无状态认证（基于令牌）
    }

    // 资源服务器安全拦截机制
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated(); // 拦截所有请求进行授权认证
    }
}
