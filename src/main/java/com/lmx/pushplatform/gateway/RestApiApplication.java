package com.lmx.pushplatform.gateway;

import com.google.common.collect.Lists;
import com.lmx.pushplatform.client.ClientDelegate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiApplication {

    @Bean
    public ClientDelegate newClientDelegate() {
        return new ClientDelegate();
    }

    @Bean
    public FilterRegistrationBean newAuthFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/push", "/user", "/group", "/developer", "/im"));
        return filterRegistrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
