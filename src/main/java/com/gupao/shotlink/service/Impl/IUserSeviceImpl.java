package com.gupao.shotlink.service.Impl;

import com.gupao.shotlink.common.reponse.BaseResponse;
import com.gupao.shotlink.common.utils.RedisService;
import com.gupao.shotlink.repository.User;
import com.gupao.shotlink.repository.mapper.UserMapper;
import com.gupao.shotlink.service.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserSeviceImpl implements IUserSevice {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public User getUserInfo(String id) {
        return userMapper.selectById(id);

    }

    @Override
    public void getRedis() {
        String all_codes = redisService.getString("indexes::all_codes");
        System.out.println(all_codes);
    }
}
