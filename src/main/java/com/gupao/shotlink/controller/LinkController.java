package com.gupao.shotlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {

    @RequestMapping("/shortUrl")
    public String getHtml(){
        return "shortUrl";
    }

}

