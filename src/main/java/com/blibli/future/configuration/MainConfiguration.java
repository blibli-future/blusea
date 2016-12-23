package com.blibli.future.configuration;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhika on 08/10/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.blibli.future.controller")
public class MainConfiguration extends RouterConfigurationSupport {

    @Override
    public List<String> listRouteFiles() {

        List<String> routeFiles = new ArrayList<>();
        routeFiles.add("classpath:routes.conf");
        return routeFiles;
    }

}



