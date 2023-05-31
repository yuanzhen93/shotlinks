package com.gupao.shotlink.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
        @Autowired
        private StringRedisTemplate stringRedisTemplate;
        /**
         * set redis: string类型
         * @param key key
         * @param value value
         */
        public void setString(String key, String value){
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            valueOperations.set(key, value);
        }
        /**
         * get redis: string类型
         * @param key key
         * @return
         */
        public String getString(String key){
            return stringRedisTemplate.opsForValue().get(key);
        }
}


