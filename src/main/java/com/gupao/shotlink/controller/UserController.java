package com.gupao.shotlink.controller;

import com.gupao.shotlink.common.annotation.ResponseResult;
import com.gupao.shotlink.repository.User;
import com.gupao.shotlink.service.IUserSevice;
import com.gupao.shotlink.service.Impl.UrlRedictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/user")
@ResponseResult
public class UserController {

    @Autowired
    private IUserSevice userSevice;
    @Autowired
    private UrlRedictServiceImpl redictService;

    private Map<String, String> urlMap = new HashMap<>(); // 存储短链接和长链接的映射关系

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable("id") String id){
        User userInfo = userSevice.getUserInfo(id);
        return userInfo;

    }

    @GetMapping("/getRedis")
    public void getRedis(){
        userSevice.getRedis();
    }

    @GetMapping("/getShortUrl/{url}")
    public ResponseEntity<Map<String,String>> getUrl(@PathVariable("url") String url){
        String shortUrl = "http://demo/"+redictService.createShortUrl(url);
        urlMap.put(shortUrl,url);
        HashMap<String, String> reponse = new HashMap<>();
        reponse.put("shortLink",shortUrl);
        return new ResponseEntity<>(reponse,HttpStatus.OK);

    }



    @GetMapping("/api/redirect")
    public ResponseEntity<Map<String, String>> redirectUrl(@RequestParam("url") String shortUrl) {
        String longUrl = urlMap.get(shortUrl);

        if (longUrl != null) {
            Map<String, String> response = new HashMap<>();
            response.put("longUrl", longUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
