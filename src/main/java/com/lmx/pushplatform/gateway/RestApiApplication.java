package com.lmx.pushplatform.gateway;

import com.lmx.pushplatform.client.ClientDelegate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiApplication {

    @Bean
    public ClientDelegate newClientDelegate() {
        return new ClientDelegate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
