package io.chenylnti.yhr.framework.config;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.service.IHrService;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 16:52
 * @Description:
 */
@Configuration
public class SecurityConfig {

    @Autowired
    IHrService hrService;


    /*
    当我们登录成功之后，security会自动将用户信息存入到两个地方
    1.httpsession
    2.securityContextHolder 这个本质上是一个ThreadLocal，这个的特点是在哪个线程中存入的数据，就在哪个线程读取，换一个线程就读取不出来了
    一般的流程是这样：
    登录成功之后，虽然信息存入到了SecurityContextHolder中，在spring security后续的执行流程中，凡是需要获取当前用户信息的时候
    都从SecurityContextHolder中获取，而不会从httpsession获取（因为并非所有系统都会选择把信息存入到HttpSession，例如有时候是jwt登录，信息会在Redis中）
    ，当登录请求结束的时候，会将securityContextHolder中的信息清除（登录是一个线程，你不清除就清除不了了，因为下一个请求是别的线程）。
    下一个请求到达的时候，会先从httpsession中读取登录用户信息，然后存入到securityContextHolder中，这样后续的流程中，就可以使用
    securityContextHolder中的信息了
    所以现在必须明确的指明需要存到哪个地方去
         */




    //给jsonfilter配置AuthenticationManager
    @Bean
    AuthenticationManager authenticationManager(){
        //这个东西可以根据用户名密码去数据库里校验
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(hrService);
        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
        return providerManager;
    }
    //把jsonfilter加入springsecurity的过滤链
    @Bean
    JsonFilter jsonFilter() {
        JsonFilter jsonFilter = new JsonFilter();
        //处理的url地址，实际上就是登录地址
        jsonFilter.setFilterProcessesUrl("/login");
        //配置了这个下面的successhandler和failurehandler就失效了，需要重新在这写
        jsonFilter.setAuthenticationSuccessHandler( (req, resp, auth) -> {
            resp.setContentType("application/json; charset=utf-8");
            Hr principal = (Hr) auth.getPrincipal();
            principal.setPassword(null);
            resp.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("登录成功",principal)));
        });
        jsonFilter.setAuthenticationFailureHandler( (req, resp, e) -> {
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
                    }  resp.getWriter().write(new ObjectMapper().writeValueAsString(error));
        });
        jsonFilter.setAuthenticationManager(authenticationManager());
        //把登录信息存到httpsession中
        jsonFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
        return jsonFilter;
    }
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
                .logout(logout->{
                    //注销地址
                    logout.logoutUrl("/logout")
                            .logoutSuccessHandler((req, resp, auth)->{
                                //注销成功
                                resp.setContentType("application/json; charset=utf-8");
                                Hr principal = (Hr) auth.getPrincipal();
                                principal.setPassword(null);
                                resp.getWriter().write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功",principal)));
                            });
                })
        ;
        //把jsonfilter加到验证key-value的过滤器的前面
        http.addFilterBefore(jsonFilter(), UsernamePasswordAuthenticationFilter.class);
        //这个实际上是在默认的过滤器基础上配置
        return http.build();
    }

}
