package org.example.snsprojcet.common.config;

import jakarta.servlet.Filter;
import org.example.snsprojcet.domain.auth.filter.LoginFilter;
import org.example.snsprojcet.domain.auth.resolver.LoginUserArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    public WebConfig(LoginUserArgumentResolver loginUserArgumentResolver) {
        this.loginUserArgumentResolver = loginUserArgumentResolver;
    }

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

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver); // LoginUserArgumentResolver 등록
    }
}
