package io.chenylnti.yhr.framework.config;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chenylnti.yhr.framework.entity.Hr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 22:08
 * @Description: 模仿springsecurity的过滤器，使能够读解析json参数
 */
//UsernamePasswordAuthenticationFilter重写这里面的方法就行
public class JsonFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断当前请求是不是post请求
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String contentType = request.getContentType();
            if (contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)|| contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
                //认为前端传来的是json格式的参数
                //提取io流转成hr对象
                try {
                    //改变参数提取的参数
                    Hr hr = new ObjectMapper().readValue(request.getInputStream(), Hr.class);
                    String username = hr.getUsername();
                    username = username != null ? username.trim() : "";
                    String password = hr.getPassword();
                    password = password != null ? password : "";
                    UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
                    this.setDetails(request, authRequest);
                    //获取认证管理器去认证
                    return this.getAuthenticationManager().authenticate(authRequest);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                //认为前端传来的是key-value格式的参数
                //父类就是干这个的
                return super.attemptAuthentication(request, response);
            }
        }
    }
}
