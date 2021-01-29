package com.example.software.project.development.management.platform.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2020-02-25
 */
@Component
public class CookieUtil {
    public static void setCookie(String cookieName, HttpServletResponse response, String content, Integer maxAge) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(content, "utf-8"));
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
        response.addHeader("Set-Cookie",cookieName+"="+content+"; Path=/");
    }

    public static String getCookie(String cookieName, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null == cookies){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(cookieName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
