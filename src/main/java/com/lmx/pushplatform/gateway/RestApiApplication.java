package com.lmx.pushplatform.gateway;

import com.google.common.collect.Lists;
import com.lmx.pushplatform.client.ClientDelegate;
import com.lmx.pushplatform.gateway.dao.AppRep;
import com.lmx.pushplatform.gateway.dao.DeveloperRep;
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
    public FilterRegistrationBean newAuthFilter(AppRep appRep) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthFilter(appRep));
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/push/server",
                "/user/*", "/group/*", "/im/*"));
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean newLoginFilter(DeveloperRep developerRep) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter(developerRep));
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/push/admin"));
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
