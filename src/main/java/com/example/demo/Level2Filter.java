package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: mall
 * @BelongsPackage: com.example.web.filter
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-08-16 23:32
 * @Description: 二级域名过滤
 */
@WebFilter(filterName = "level2Filter", urlPatterns = "/*")
public class Level2Filter implements Filter {

    private String domainName = "tjsd.cn";

    private static Map<String, String> nameMap = new HashMap<>();

    private static Map<String, String> map = new HashMap<>();


    static {
        nameMap.put("1", "今朝大药房");
        nameMap.put("2", "天天大药房");
        nameMap.put("3", "京东自营大药房");
        map.put("mall", "1");
        map.put("jz", "2");
        map.put("jd", "3");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取域名
        String serverName = request.getServerName();
        //如果为localhost或者为商城二级域名
        if(serverName.equals("localhost") || serverName.equals("mall.tjsd.cn")){
            filterChain.doFilter(request, response);
            return;
        }
        //
        //获取访问路径
        String uri = request.getRequestURI();

        //如果后面带有路径的,则不拦截
//        if (uri.length() > 1) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        //获取主域名
        String domainName = serverName.substring(serverName.indexOf(".") + 1);
        System.out.println("主域名:" + domainName);
        if (!domainName.equalsIgnoreCase(this.domainName)) {
            filterChain.doFilter(request, response);
            return;
        }

        //获取二级域名
        String secDomainName = serverName.substring(0, serverName.indexOf("."));

//        if(retainDomainName.contains(secDomainName)){
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            return;
//        }

        //根据二级域名获取店铺id
        String shopId = map.get(secDomainName);

        request.setAttribute("shopId",shopId);
        if (null == shopId) {
            filterChain.doFilter(request, response);
            return;
        }

        if(StringUtils.isBlank(uri)||"/".equals(uri)){
            uri = "/shop/" + shopId;
        }
        //根据获取到的店铺ID拼接成能够访问店铺首页的URL,然后进行转发
        request.getRequestDispatcher(uri).forward(request, response);

    }
}
