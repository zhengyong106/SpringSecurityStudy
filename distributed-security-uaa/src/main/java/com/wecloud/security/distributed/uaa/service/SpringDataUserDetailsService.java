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

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    private List<Map<String, String>> userList = new ArrayList<>();

    public SpringDataUserDetailsService() {
        Map<String, String> user1 = new HashMap<>();
        user1.put("username", "admin");
        user1.put("password", BCrypt.hashpw("admin",  BCrypt.gensalt()));
        user1.put("permission", BCrypt.hashpw("admin",  BCrypt.gensalt()));

        Map<String, String> user2 = new HashMap<>();
        user2.put("username", "guest");
        user2.put("password", "guest");
        user2.put("permission", "guest");

        userList.add(user1);
        userList.add(user2);
    }

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "用户名不能为空");

        for (Map<String, String> map : userList) {
            if (username.equals(map.get("username"))) {
                return User.withUsername(map.get("username")).password(map.get("password")).authorities(map.get("permission")).build();
            }
        }
        return null;
    }
}
