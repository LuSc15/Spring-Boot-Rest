package com.example.springapidemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfig {
    @Bean
    public FilterRegistrationBean<SimpleRateLimitFilter> rateLimitFilter() {
        FilterRegistrationBean<SimpleRateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SimpleRateLimitFilter());
        registrationBean.addUrlPatterns("/getOrders", "/getOrderById", "/getOrdersByCustomer", "/getOrdersByYear");
        return registrationBean;
    }
}
