package com.example.msgnotice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Created by liumengbing on 2018/10/18 0018.
 */
@ConditionalOnWebApplication
@Configuration
public class WebSocketConfig {

    @Bean
    public CustomSpringConfigurator customSpringConfigurator() {
        return new CustomSpringConfigurator(); // This is just to get context
    }
}