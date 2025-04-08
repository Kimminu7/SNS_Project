package org.example.snsprojcet.common.config;

import jakarta.servlet.Filter;
import org.example.snsprojcet.domain.auth.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean loginFilter () {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // Filter 등록
        filterRegistrationBean.setFilter(new LoginFilter());
        // 순서 설정
        filterRegistrationBean.setOrder(1);
        // 전체 URL에 필터 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
