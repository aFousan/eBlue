/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.controller;

import com.afousan.eblue.repo.EblueRepository;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author aFousan
 */
@Controller
public class BlueController {

    @Autowired
    private EblueRepository eblueRepository;

    @Autowired
    public BlueController(EblueRepository eblueRepository) {
        this.eblueRepository = eblueRepository;
    }

    @RequestMapping(value = "/login")
    public String login(String user, String pass) {
        if (eblueRepository.auth(user, pass)) {
            return "redirect:/" + user;
        }
        return "login";
    }

    @RequestMapping(value = "/join")
    public String join(String name, String pass, String pass2, Model model, HttpServletResponse response) {
        String auth = eblueRepository.addUser(name, pass);
        return "join";
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String success(@PathVariable String name,Model model){
        model.addAttribute("name", name);
        return "success";
    }
}
