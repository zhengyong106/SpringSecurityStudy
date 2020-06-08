package com.wecloud.security.distributed.uaa.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String DEFAULT_SELECT_STATEMENT = "select username, password, roles, enabled from users where username = ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    //根据用户名查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> user = jdbcTemplate.queryForMap(DEFAULT_SELECT_STATEMENT, username);

        String password = (String) user.get("password");
        String rolesStr = (String) user.get("roles");
        rolesStr = StringUtils.strip(rolesStr, "[");
        rolesStr = StringUtils.strip(rolesStr,"]");
        String[] rolesArray = rolesStr.split(",");
        String[] newArray = new String[rolesArray.length];

        for (int i = 0; i < rolesArray.length; i++) {
            newArray[i] = StringUtils.strip(rolesArray[i]);
        }

        return User.withUsername(username)
                .password(password)
                .roles(newArray)
                .build();
    }
}
