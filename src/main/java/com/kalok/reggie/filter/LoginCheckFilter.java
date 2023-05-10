package com.kalok.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.kalok.reggie.common.R;
import com.kalok.reggie.vo.EmployeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述:拦截未登录用户对其他页面请求的拦截器
 * 自己做的结果:
 * 成功拦截了，但是用户登录后看到的首页是缺胳膊少腿的
 * 对比老师的不足:
 * 1.对于无需拦截的请求理解错误，且处理无需拦截的请求时用了比较笨的方法，没有使用工具类进行匹配
 * 2.拦截时采用的是跳转回登录页面，而没有结合前端代码给前端返回其所需的数据
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*") //用于指定拦截路径和被加入IOC容器的注解
public class LoginCheckFilter implements Filter {
    //Spring提供的路径匹配工具类,可以匹配带通配符的路径
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    //不需要拦截的请求
    public static String[] paths = {"/employee/login","/employee/logout","/backend/**","/front/**"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //转成子类后再用
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        //获取请求的URI
        String requestURI = request.getRequestURI();
        log.info("intercept : {}",requestURI);
        boolean checkRes = checkPaths(requestURI);
        //1.用户的请求无需拦截,放行
        if(checkRes){
            filterChain.doFilter(request,response);
            return;
        }
        //2.用户已登录,放行
        Object empId = session.getAttribute("employee");
        if(empId != null){
            filterChain.doFilter(request,response);
            return;
        }
        //3.上述两个条件均不成立,给前端返回JSON数据,告知用户未登录(这部分需结合前端的request.js文件)
        //使用PrintWriter将错误的JSON数据返回给用户
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 检查请求的URI是否为无需拦截的路径
     * @param requestUri
     * @return
     */
    private boolean checkPaths(String requestUri){
        for (String path : paths) {
            if(PATH_MATCHER.match(path,requestUri)){
                return true;
            }
        }
        return false;
    }
}
