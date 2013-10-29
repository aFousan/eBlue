/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author welcome
 */
@Controller
public class BlueController {
    
    @RequestMapping(value = "/login")
    public String home(){
        return "login";
    }
}
