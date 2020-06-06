package com.wecloud.security.distributed.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping("/admin/order")
    public String admin() {
        return "admin order";
    }

    @GetMapping("/user/order")
    public String user() {
        return "user order";
    }
}
