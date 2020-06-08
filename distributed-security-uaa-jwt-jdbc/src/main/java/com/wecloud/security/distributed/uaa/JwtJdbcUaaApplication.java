package com.wecloud.security.distributed.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtJdbcUaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtJdbcUaaApplication.class, args);
    }
}
