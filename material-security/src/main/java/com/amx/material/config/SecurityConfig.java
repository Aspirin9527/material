package com.amx.material.config;

import com.alibaba.fastjson.JSONObject;
import com.amx.material.entity.ResponseResult;
import com.amx.material.filter.JwtAuthenticationTokenFilter;
import com.amx.material.utils.JwtUtil;
import com.amx.material.utils.RedisCache;
import com.amx.material.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author lin
 * @date 2023年04月03日 15:11
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    RedisCache redisCache;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll().anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
            String token = request.getHeader("token");
            String userid;
            try {
                Claims claims = JwtUtil.parseJWT(token);
                userid = claims.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("token非法");
            }
            redisCache.deleteObject("userId:" + userid);
            WebUtils.renderString(response,JSONObject.toJSONString(new ResponseResult(200, "注销成功")));
        });

        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //配置异常处理器
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    /**
     * 退出处理
     *
     * @return
     */
    /*@Bean
    public LogoutSuccessHandler logoutSussHandler() {
        return (request, response, authentication) -> {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) auth.getPrincipal();
            Integer userid = loginUser.getSysUser().getId();
            redisCache.deleteObject("userId:"+userid);
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.OK.value());

            response.getWriter().write(JSONObject.toJSONString("退出成功"));
        };

    }*/


}
