package com.gupao.shotlink.service;

import java.io.IOException;
import java.net.ProtocolException;

public interface UrlRedirectService {

    /**
     * 生成短链
     *
     * @param originUrl
     * @return
     */
    public String createShortUrl(String originUrl);


}
