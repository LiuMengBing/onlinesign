package com.example.msgnotice;

import org.springframework.web.context.WebApplicationContext;

import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by liumengbing on 2018/10/18 0018.
 */
public class SpringEndpointConfigurator extends ServerEndpointConfig.Configurator{

    private static WebApplicationContext wac;

    public SpringEndpointConfigurator() {
    }

    public SpringEndpointConfigurator(WebApplicationContext wac) {
        SpringEndpointConfigurator.wac = wac;
    }

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        T endPoint = wac.getAutowireCapableBeanFactory().getBean(endpointClass);
        return (endPoint != null) ? endPoint : wac.getAutowireCapableBeanFactory().createBean(endpointClass);
    }
}
