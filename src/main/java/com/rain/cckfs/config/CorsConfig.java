package com.rain.cckfs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: LightRain
 * @Description: Cors从源策略配置
 * @DateTime: 2023-04-01 17:51
 * @Version：1.0
 **/
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * @Author: LightRain
     * @Date: 3/4/2023 下午 1:55
     * @Param: [registry]
     * @Return: void
     * @Description: 添加Cors从源策略映射
     * @since 17
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
        addMapping("/**"): 设置允许跨域的路径
        allowedOriginPatterns("*"): 设置允许跨域请求的域名
        allowCredentials(true): 是否允许cookie
        allowedMethods("GET", "POST", "DELETE", "PUT"): 设置允许的请求方式
        allowedHeaders("*"): 设置允许的header属性
        maxAge(3600): 跨域允许时间
         */
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .allowedHeaders("*")
                .maxAge(3600);
    }

}
