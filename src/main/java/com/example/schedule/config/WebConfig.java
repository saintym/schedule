package com.example.schedule.config;

import com.example.schedule.domain.session.SessionService;
import com.example.schedule.domain.session.filter.SessionFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final SessionService sessionService;

    public WebConfig(SessionService sessionService){
        this.sessionService = sessionService;
    }

    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter(){

        FilterRegistrationBean<SessionFilter> filterRegistrationBean = new FilterRegistrationBean<SessionFilter>();
        filterRegistrationBean.setFilter(new SessionFilter(sessionService));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
