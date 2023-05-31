package com.gupao.shotlink.controller;

import com.gupao.shotlink.common.utils.RandomUtil;
import com.gupao.shotlink.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * @create  2022-05-22 15:12
 */
@Api(tags = "阿里云短信服务")
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;    //注入redis

    //发送短信验证码
    @ApiOperation(value = "发送短信验证码")
    @GetMapping(value = "/send/{phone}")
    public Boolean code(@PathVariable String phone) {
        //1、从redis中获取验证码，如果获取到就直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return false;

        //2、如果获取不到，就进行阿里云发送
        code = RandomUtil.getFourBitRandom();//生成验证码的随机值
        Map<String,Object> param = new HashMap<>();
        param.put("code", "http://www.baidu.com");

        //调用方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //往redis中设置数据：key、value、过期值、过期时间单位  MINUTES代表分钟
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return true;
        } else {
            return false;
        }
    }
}


