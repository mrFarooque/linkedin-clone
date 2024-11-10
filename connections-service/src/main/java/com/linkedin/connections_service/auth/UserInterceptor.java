package com.linkedin.connections_service.auth;

@org.springframework.stereotype.Component
public class UserInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
    @Override
    public boolean preHandle(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-User-Id");
        if (userId != null) {
            UserContextHolder.setCurrentUserId(Long.valueOf(userId));
        }
        return org.springframework.web.servlet.HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(
          jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.clear();
        org.springframework.web.servlet.HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
