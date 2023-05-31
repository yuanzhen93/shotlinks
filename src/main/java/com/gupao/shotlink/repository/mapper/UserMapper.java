package com.gupao.shotlink.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.shotlink.repository.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
