package com.tland.landsystem.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "jwtAuthenticationFilter", urlPatterns = "/api/*")
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo bộ lọc nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Ép kiểu về HttpServletRequest và HttpServletResponse để xử lý header
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Nếu URL bắt đầu bằng /api/auth, bỏ qua filter (cho phép truy cập không cần token)
        String path = req.getRequestURI();
        if (path.startsWith(req.getContextPath() + "/api/auth/")) {
            chain.doFilter(request, response);
            return;
        }

        // Thêm các header CORS vào response
        res.setHeader("Access-Control-Allow-Origin", "*"); // Cho phép tất cả domain
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        // Nếu là request OPTIONS, trả về OK ngay lập tức (không cần tiếp tục chuỗi filter)
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Nếu không có logic xác thực JWT (hoặc chưa dùng token), chỉ tiếp tục chuỗi filter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp nếu cần
    }
}
