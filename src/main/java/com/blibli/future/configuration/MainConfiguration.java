package com.blibli.future.configuration;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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



