/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author welcome
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.afousan.eblue.controller"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Bean
    public InternalResourceViewResolver view() {
        InternalResourceViewResolver view = new InternalResourceViewResolver();
        view.setSuffix(".jsp");
        view.setPrefix("/WEB-INF/views/");
        view.setViewClass(JstlView.class);
        return view;
    }
}
