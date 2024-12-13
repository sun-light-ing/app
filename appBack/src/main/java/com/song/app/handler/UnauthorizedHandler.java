package com.song.app.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: appBack
 * @BelongsPackage: com.song.app.handler
 * @Author: sun
 * @CreateTime: 2024-12-08  22:19
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, IOException {
        // 设置响应状态码为401（未授权）
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应内容类型
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 响应体内容，可以根据需要自定义
        response.getWriter().write("err");
    }
}
