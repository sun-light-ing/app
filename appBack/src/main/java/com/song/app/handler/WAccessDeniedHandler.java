package com.song.app.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: appBack
 * @BelongsPackage: com.song.app.handler
 * @Author: sun
 * @CreateTime: 2024-12-08  22:21
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class WAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print("认证失败");
        response.getWriter().flush();
    }
}
