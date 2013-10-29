/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aFousan
 */
@Controller
public class BlueController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/join")
    public String join() {
        return "join";
    }
}
