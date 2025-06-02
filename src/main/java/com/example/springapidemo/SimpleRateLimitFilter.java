package com.example.springapidemo;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class SimpleRateLimitFilter implements Filter {
    private static final int LIMIT = 10;
    private static final long WINDOW_MS = 60_000; // 1 minute

    private final Map<String, RequestCounter> counters = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String origin = httpReq.getHeader("Origin");

        // CORS-Header immer setzen, auch bei Fehlern und Preflight
        if (origin != null && (
                "http://localhost:3000".equals(origin) ||
                "http://192.168.0.150:3000".equals(origin))) {
            httpResp.setHeader("Access-Control-Allow-Origin", origin);
            httpResp.setHeader("Access-Control-Allow-Headers", "*");
            httpResp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            httpResp.setHeader("Access-Control-Allow-Credentials", "true");
        }

        // Preflight-Request direkt beantworten
        if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
            httpResp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String ip = request.getRemoteAddr();
        long now = Instant.now().toEpochMilli();

        RequestCounter counter = counters.compute(ip, (k, v) -> {
            if (v == null || now - v.startTime > WINDOW_MS) {
                return new RequestCounter(1, now);
            } else {
                v.count++;
                return v;
            }
        });

        if (counter.count > LIMIT) {
            httpResp.setStatus(429);
            httpResp.setContentType("application/json");
            httpResp.getWriter().write("{\"error\":\"Too Many Requests\",\"message\":\"Zu viele Anfragen. Bitte warte einen Moment.\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
