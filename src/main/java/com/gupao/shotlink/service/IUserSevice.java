package com.gupao.shotlink.service;

import com.gupao.shotlink.repository.User;
import org.springframework.stereotype.Service;

public interface IUserSevice {

    public User getUserInfo(String id);

    public void getRedis();

}
