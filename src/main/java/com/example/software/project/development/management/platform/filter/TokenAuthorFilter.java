package com.example.software.project.development.management.platform.filter;

/**
 * token验证拦截
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2020-02-23
 */

import com.example.software.project.development.management.platform.response.GlobalVariable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@WebFilter(urlPatterns="/**",filterName="loginFilter")
@Slf4j
public class TokenAuthorFilter implements Filter {
    private static String GLOBAL_URL = "/schoolAdmin/WebAdmin-master";
    /**
     * 排除不拦截的url
     */
    private static final String[] EXCLUTE_PATH = { "/lib/","/static/","favicon.ico","151688086754999.png","user.json",".css",".png",".jpg","login","/js","test","baodao",".js"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        res.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        res.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        // 获取请求url地址，不拦截excludePathPatterns中的url
        String url = req.getRequestURI();
        if("/software/project/development/management/server/".equals(url)){
            // 如果是直接访问，可以直接跳转
            chain.doFilter(request, response);
            return;
        }
        //log.info(url);
        for(String str:EXCLUTE_PATH){
            if(url.contains(str)){
                //放行，相当于第一种方法中LoginInterceptor返回值为true
                chain.doFilter(request, response);
                return;
            }
        }
        //log.info("开始拦截了................");
        //业务代码
        Cookie[] cookies =  req.getCookies();
        String receiveToken = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    receiveToken = cookie.getValue();
                    break;
                }
            }
            if(StringUtils.isNotBlank(receiveToken)){
                // 校验token
                //String checkToken = TokenUtils.checkToken(receiveToken);
                //System.out.println("checkToken Result: " + checkToken);
                //log.info(checkToken);

                if(GlobalVariable.userInfoMap.get(receiveToken)!=null){
                    log.info("有token，且有登陆信息保存，放过");
                    chain.doFilter(request, response);
                }else{
                    res.sendRedirect(req.getContextPath());
                }
                //if(receiveToken.equals(checkToken)) {
                //    System.out.println("==>token verification succeeded!");
                //}
            }else{
                res.sendRedirect(req.getContextPath());
            }
        }else{
            res.sendRedirect(req.getContextPath());
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }


}
