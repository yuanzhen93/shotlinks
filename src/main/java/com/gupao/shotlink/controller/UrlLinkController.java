package com.gupao.shotlink.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gupao.shotlink.common.utils.RedisService;
import com.gupao.shotlink.repository.UrlMapping;
import com.gupao.shotlink.repository.mapper.UrlMappingMapper;
import com.gupao.shotlink.service.Impl.UrlRedictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlLinkController {

    @Autowired
    private UrlRedictServiceImpl redictService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private UrlMappingMapper mapper;

    private Map<String, String> urlMap = new HashMap<>(); // 存储短链接和长链接的映射关系

    @PostMapping("/getShortUrl")
    public String getUrl(@RequestParam("url") String url){
        return redictService.createShortUrl(url);
    }

    @GetMapping("{shortUrl}")
    public void redirectUrl(@PathVariable("shortUrl") String shortUrl, HttpServletResponse response) throws IOException {
        String longUrl = redisService.getString(shortUrl);
        //redis中没有去数据库查询
        if(StringUtils.isEmpty(longUrl)){
            UrlMapping urlMapping = mapper.selectOne(new LambdaQueryWrapper<UrlMapping>().eq(UrlMapping::getShortUrl, shortUrl));
            if(!ObjectUtils.isEmpty(urlMapping)){
                longUrl = urlMapping.getOriginalUrl();
            }
        }
        response.setHeader("Prama","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("refresh","0;URL="+longUrl);
        response.setStatus(307);
        response.setHeader("location",longUrl);
        response.getWriter().flush();


    }

}
