package com.wecloud.security.distributed.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // 允许跨域请求
                .authorizeRequests()
                .anyRequest().permitAll(); // 放行所有请求
    }
}
