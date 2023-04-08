package com.example.definitelynotvulnerableapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class UselessController {

    @Autowired
    private RedisTemplate<String, String> template;

    @RequestMapping(path = "/rest/dummy", method = RequestMethod.GET)
    public String putValueToRedis(@RequestParam("value") String value) {
        template.execute(RedisScript.of("redis.call('set','basic','"+value+"')"), Collections.emptyList(), "");
        return String.format("basic:%s\nsecret:%s", template.boundValueOps("basic").get(), template.boundValueOps("secret").get());

    }
}
