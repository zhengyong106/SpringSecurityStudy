package com.wecloud.security.distributed.uaa.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final List<Map<String, String>> userList = new ArrayList<>();

    public UserDetailsServiceImpl() {
        Map<String, String> user1 = new HashMap<>();
        user1.put("username", "admin");
        user1.put("password", BCrypt.hashpw("admin",  BCrypt.gensalt()));
        user1.put("role", "admin");

        Map<String, String> user2 = new HashMap<>();
        user2.put("username", "user");
        user2.put("password", BCrypt.hashpw("user",  BCrypt.gensalt()));
        user2.put("role", "user");

        userList.add(user1);
        userList.add(user2);
    }

    //根据用户名查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "用户名不能为空");

        for (Map<String, String> map : userList) {
            if (username.equals(map.get("username"))) {
                return User.withUsername(map.get("username"))
                        .password(map.get("password"))
                        .roles(map.get("role"))
                        .build();
            }
        }
        return null;
    }
}
