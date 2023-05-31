package com.gupao.shotlink.service.Impl;

import com.gupao.shotlink.service.UrlRedirectService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class UrlRedictServiceImpl implements UrlRedirectService {

    @Override
    public String createShortUrl(String originUrl) {
        return shortUrl(originUrl);
    }

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

//        String[] resUrl = new String[4];
//        for ( int i = 0; i < 4; i++) {
//
//            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
//            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
//
//            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
//            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
//            String outChars = "" ;
//            for ( int j = 0; j < 6; j++) {
//                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
//                long index = 0x0000003D & lHexLong;
//                // 把取得的字符相加
//                outChars += chars[( int ) index];
//                // 每次循环按位右移 5 位
//                lHexLong = lHexLong >> 5;
//            }
//            // 把字符串存入对应索引的输出数组
//            resUrl[i] = outChars;
//        }
        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
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


    @Override
    public String redictUrl(String shortUrl) throws IOException {
        // 发送HTTP请求
        URL url = new URL(shortUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // 获取重定向URL
        String redirectURL = connection.getHeaderField("Location");

//        // 打开重定向URL
//        if (redirectURL != null) {
//            System.out.println("Redirecting to: " + redirectURL);
//            // 在浏览器中打开重定向URL
//            // 可以使用Java Desktop库打开默认浏览器，或者使用Selenium等工具进行自动化测试
//        } else {
//            System.out.println("Unable to retrieve redirect URL.");
//        }
        return redirectURL;

    }
}
