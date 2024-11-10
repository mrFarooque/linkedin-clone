package com.linkedin.connections_service.auth;

@org.springframework.context.annotation.Configuration
public class WebConfig implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @org.springframework.beans.factory.annotation.Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }
}
