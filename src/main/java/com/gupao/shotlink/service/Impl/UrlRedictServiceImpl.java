package com.gupao.shotlink.service.Impl;

import com.gupao.shotlink.common.utils.RedisService;
import com.gupao.shotlink.repository.UrlMapping;
import com.gupao.shotlink.repository.mapper.UrlMappingMapper;
import com.gupao.shotlink.service.UrlRedirectService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

@Service
public class UrlRedictServiceImpl implements UrlRedirectService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UrlMappingMapper mapper;
    
    @Override
    public String createShortUrl(String originUrl) {
        String genShortUrl = "";
        String originHex = DigestUtils.md5Hex(originUrl);
        String shortUrl = redisService.getString(originHex);
        //短链空值生成短链并存储，非空时直接返回
        if(StringUtils.isEmpty(shortUrl)){
            genShortUrl = shortUrl(originUrl);
            //redis存储（长链md5--短链 短链--长链）
            redisService.setString(originHex,genShortUrl);
            redisService.setString(genShortUrl,originUrl);

            //数据库存储
            UrlMapping urlMapping = new UrlMapping();
            urlMapping.setShortUrl(genShortUrl);
            urlMapping.setOriginalUrl(originUrl);
            urlMapping.setOriginalUrlHash(originHex);
            urlMapping.setInterviewCount(0);
            urlMapping.setCreatedDate(new Date());
            mapper.insert(urlMapping);

        }else{
            return shortUrl;
        }
        return genShortUrl;
    }

    //md5生成短链
    public String shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "mengdelong" ;
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"

        };
        // 对传入网址进行 MD5 加密
        String hex = DigestUtils.md5Hex(key + url);
        String sTempSubString = hex.substring(8, 16);

        // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
        String outChars = "" ;
        for ( int j = 0; j < 6; j++) {
            // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
            long index = 0x0000003D & lHexLong;
            // 把取得的字符相加
            outChars += chars[( int ) index];
            // 每次循环按位右移 5 位
            lHexLong = lHexLong >> 5;
        }
        // 把字符串存入对应索引的输出数组

        return outChars;
    }


}
