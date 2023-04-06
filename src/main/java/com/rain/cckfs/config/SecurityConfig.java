package com.rain.cckfs.config;

import com.rain.cckfs.oa.OAAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: LightRain
 * @Description: 安全配置
 * @DateTime: 2023-04-01 15:54
 * @Version：1.0
 **/
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     * @Author: LightRain
     * @Date: 3/4/2023 下午 2:08
     * @Param: [httpSecurity]
     * @Return: org.springframework.security.web.SecurityFilterChain
     * @Description: 安全过滤器链配置
     * @since 17
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //successForwardUrl("/login/success")：登录成功跳转到controller处理
        httpSecurity.csrf()
                .disable()//禁用跨站攻击
                .cors()
                .and()
                .formLogin()
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/failure")
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/createKaptchaCodeImg").permitAll()
                .anyRequest().permitAll()
                //.authenticated()//没有这一句，将会直接绕过登录可以访问到controller
                .and()
                // 添加 Session管理器
                .sessionManagement()
                // Session失效后跳转到这个链接
                .invalidSessionUrl("/session/invalid")
                .and()
                .exceptionHandling()
                //统一处理未授权异常
                .authenticationEntryPoint(new OAAuthenticationEntryPoint())
                .and()
                //退出登录配置
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        return httpSecurity.build();
    }

    /**
     * @Author: LightRain
     * @Date: 3/4/2023 下午 2:08
     * @Param: []
     * @Return: org.springframework.web.cors.CorsConfigurationSource
     * @Description: cors配置源
     * @since 17
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域访问的站点
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4500", "http://localhost:80", "http://cckfs.fairiy.com/", "https://cckfs.fairiy.com/"));
        //允许跨域访问的methods
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
        // 允许携带凭证
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对所有URL生效
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    /**
     * @Author: LightRain
     * @Date: 3/4/2023 下午 2:08
     * @Param: []
     * @Return: org.springframework.security.crypto.password.PasswordEncoder
     * @Description: 密码加密器
     * </br>
     * 会把客户端传来的密码进行加密，然后跟数据库中的密码做对比，要求数据库中的密码也是加密过的
     * 如果没有该加密器，spring security会报出异常：There is no PasswordEncoder mapped for the id "null"
     * @since 17
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
