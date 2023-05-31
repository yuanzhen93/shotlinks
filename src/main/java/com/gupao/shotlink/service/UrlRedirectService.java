package com.gupao.shotlink.service;

import java.io.IOException;
import java.net.ProtocolException;

public interface UrlRedirectService {

    public String createShortUrl(String originUrl);

    public String redictUrl(String shortUrl) throws IOException;

}
