/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.init;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author aFousan
 */
@Order(1)
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] {DataConfig.class};
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
}
