package com.lmx.pushplatform.gateway.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/developer")
public class DeveloperController {


    @PostMapping("/reg")
    public Object reg(@RequestBody Object o) {
        return null;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Object o) {
        return null;
    }
}
