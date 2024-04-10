package io.chenylnti.yhr.framework.config;/**
 * @author chenylnti
 * @date 2024/4/6
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import io.chenylnti.yhr.framework.entity.Hr;
import io.chenylnti.yhr.framework.entity.RespBean;
import io.chenylnti.yhr.framework.entity.Role;
import io.chenylnti.yhr.framework.entity.vo.MenuWithRole;
import io.chenylnti.yhr.framework.service.IHrService;
import io.chenylnti.yhr.framework.service.IMenuService;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author:ChEnylnti
 * @create: 2024-04-06 16:52
 * @Description:
 */
@Configuration
public class SecurityConfig {

    @Autowired
    IHrService hrService;
    @Autowired
    IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();


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
        http.authorizeHttpRequests(p->p.anyRequest().access((authentication, object) -> {
            boolean granted = false;
            boolean isMatch = false;
            //1.根据当前请求分析出来当前请求属于menu中的哪一种http://localhost:8080/personnel/ec/hello
            //1.1 获取当前请求url地址
            String requestURI = object.getRequest().getRequestURI();
            System.out.println(requestURI);
            //1.2 和menu表中的记录进行比较
            List<MenuWithRole> menuWithRoles = menuService.getAllMenusWithRole();
            Authentication auth = authentication.get();
            for (MenuWithRole menuWithRole : menuWithRoles) {
                if (antPathMatcher.match(menuWithRole.getUrl(),requestURI)) {
                    isMatch = true;
                    //如果匹配上了，说明当前请求的菜单就找到了
                    //2.根据第一步分析的结果，进而分析出来当前menu需要哪些角色才能访问（menu_role）
                    List<Role> roles = menuWithRole.getRoles();
                    System.out.println(roles);
                    //3.判断当前用户是否具备本次请求所需要的角色
                    //获取当前登录用户所具备的角色
                    Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                    for (GrantedAuthority authority : authorities) {
                        for (Role role : roles) {
                            if (authority.getAuthority().equals(role.getName())) {
                                //说明当前用户具备所需要的角色
                                granted = true;
                                break;
                            }
                        }
                    }
                }
            }
            //也有可能for循环结束了都没有匹配上
            if (!isMatch){
                //例如http://localhost:8080/menus
                //此时，判断用户是否已经登录，如果登录，则允许访问，否则不允许
                if (auth instanceof UsernamePasswordAuthenticationToken){
                    //说明用户登录了
                    granted = true;
                }
            }

            //granted为true表示请求通过，granted为false表示用户权限不足，请求未通过
            return new AuthorizationDecision(granted);
        }))
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
