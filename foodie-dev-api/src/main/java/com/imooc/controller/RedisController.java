package com.imooc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/set")
    public String set(String key, String value){

        redisTemplate.opsForValue().set(key,value);

        return "OK";
    }

    @GetMapping("/get")
    public String get(String key){

        return redisTemplate.opsForValue().get(key).toString();
    }

    @GetMapping("/delete")
    public String delete(String key){

        return redisTemplate.delete(key).toString();
    }


}
