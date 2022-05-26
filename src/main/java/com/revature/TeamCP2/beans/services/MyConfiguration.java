package com.revature.TeamCP2.beans.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedHeaders("Origin, Content-Type, Accept")
                        .allowedOrigins("http://teamcp2-ui.s3-website-us-east-1.amazonaws.com")
                        .allowedMethods("POST, GET, OPTIONS, PUT, DELETE, HEAD");
            }
        };
    }
}
//@Component
//public class CorsFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");
//        filterChain.doFilter(request, response);
//    }
//}
