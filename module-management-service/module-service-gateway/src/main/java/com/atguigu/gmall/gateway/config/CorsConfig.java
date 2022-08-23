package com.atguigu.gmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName CorsConfig.java
 * @Description TODO
 * @createTime 2022年08月20日 15:47:00
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter getCorsWebFilter() {
        // CORS跨域配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 设置允许访问的网络
        corsConfiguration.addAllowedOrigin("*");
        // 设置是否从服务器获取cookie
        corsConfiguration.setAllowCredentials(true);
        // 设置请求方法 * 表示任意
        corsConfiguration.addAllowedMethod("*");
        // 允许携带请求头信息 * 表示任意
        corsConfiguration.addAllowedHeader("*");
        // 配置源对象
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // CORS过滤器对象
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // CORS过滤器对象
        return new CorsWebFilter(urlBasedCorsConfigurationSource);

    }
}
