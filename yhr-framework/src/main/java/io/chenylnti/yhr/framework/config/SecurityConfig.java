package io.chenylnti.yhr.framework.config;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.entity.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 16:52
 * @Description:
 */
@Configuration
public class SecurityConfig {
//     这个就是spring security 过滤器链，spring security中的所有功能都是通过这个链来提供的
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //拦截所有请求，但是不经过任何过滤器
//        return new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"));
        http.authorizeHttpRequests(p->p.anyRequest().authenticated())
                .formLogin(f->f.usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .successHandler((req, resp, auth) -> {
                           resp.setContentType("application/json; charset=utf-8");
                            Hr principal = (Hr) auth.getPrincipal();
                            principal.setPassword(null);
                            resp.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("登录成功",principal)));
                        }).failureHandler((req, resp, e) -> {
                            resp.setContentType("application/json; charset=utf-8");
//                          登录失败就有多种情况了
                            RespBean error = RespBean.error("登录失败");
                            if (e instanceof BadCredentialsException) {
                               error.setMessage("用户名或密码输入错误,登录失败");
                            } else if (e instanceof DisabledException){
                                error.setMessage("账户被禁用，登录失败");
                            } else if(e instanceof LockedException){
                                error.setMessage("账户被锁定，登录失败");
                            } else if(e instanceof AccountExpiredException){
                                error.setMessage("账户过期，登录失败");
                            } else if(e instanceof CredentialsExpiredException){
                                error.setMessage("密码过期，登录失败");
                            }
                            resp.getWriter().write(new ObjectMapper().writeValueAsString(error));
                        }))
                .csrf(c->c.disable())
                //异常处理器，没登录时不应该返回页面也应该返回一个respbean
                .exceptionHandling(e->e.authenticationEntryPoint((req,resp,ex)->{
                    resp.setContentType("application/json; charset=utf-8");
                    resp.setStatus(401);
                    RespBean error = RespBean.error("尚未登录，请先登录");
                    resp.getWriter().write(new ObjectMapper().writeValueAsString(error));
                }))
        ;
        //这个实际上是在默认的过滤器基础上配置
        return http.build();
    }

}
