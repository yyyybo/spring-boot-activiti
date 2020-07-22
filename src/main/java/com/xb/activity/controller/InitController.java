package com.xb.activity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * InitController
 *
 * @author 莫问
 * @date 2019-11-19
 */
@Controller
public class InitController {

    @GetMapping("/")
    public String sayHello() {
        return "login";
    }

    @RequestMapping("{url}.shtml")
    public String page(@PathVariable("url") String url) {
        return url;
    }

    @RequestMapping("{module}/{url}.shtml")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url;
    }

    @RequestMapping("{module}/{sub}/{url}.shtml")
    public String page(@PathVariable("module") String module, @PathVariable("sub") String sub,
        @PathVariable("url") String url) {
        return module + "/" + sub + "/" + url;
    }
}
