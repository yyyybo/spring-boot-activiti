package com.xb.activity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 *
 * @author 莫问
 * @date 2019-11-19
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public Map<String, Object> login(String username,String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        return map;
    }
}
