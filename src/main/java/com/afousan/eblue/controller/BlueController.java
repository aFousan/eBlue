/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.controller;

import com.afousan.eblue.exception.NoSuchDataException;
import com.afousan.eblue.interceptor.CookieInterceptor;
import com.afousan.eblue.repo.EblueRepository;
import com.afousan.eblue.security.EblueSecurity;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/")
    public String root() {
        if (EblueSecurity.isSignedIn()) {
            return "redirect:/" + EblueSecurity.getName();
        }
        return "landing";
    }

    @RequestMapping(value = "/landing")
    public String rootLanding() {
        if (EblueSecurity.isSignedIn()) {
            return "redirect:/" + EblueSecurity.getName();
        }
        return "landing";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(required = false) String name, @RequestParam(required = false) String pass, Model model, HttpServletResponse response) {

        if (eblueRepository.auth(name, pass)) {
            addAuthCookie(eblueRepository.addAuth(name), name, response);
            return "redirect:/" + name;
        } else if (StringUtils.hasText(name) || StringUtils.hasText(pass)) {
            model.addAttribute("errorpass", Boolean.TRUE);
        }
        return "login";
    }

    private void addAuthCookie(String auth, String name, HttpServletResponse response) {
        EblueSecurity.setUser(name, eblueRepository.findUid(name));

        Cookie cookie = new Cookie(CookieInterceptor.EBLUE_COOKIE, auth);
        cookie.setComment("eBlue Cookie");
        // cookie valid for up to 1 week
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
    }

    
    @RequestMapping(value = "/join")
    public String toJoin(){        
        return "join";
    }
    
    @RequestMapping(value = "/join")
    public String join(String name, String pass, String pass2, Model model, HttpServletResponse response) {
        
        if (eblueRepository.isUserValid(name)) {
            model.addAttribute("errorduplicateuser", Boolean.TRUE);
            return "login";
        }

        if (!StringUtils.hasText(pass) || !StringUtils.hasText(pass2) || !pass.equals(pass2)) {
            model.addAttribute("errormatch", Boolean.TRUE);
            return "login";
        }

        String auth = eblueRepository.addUser(name, pass);
        addAuthCookie(auth, name, response);


        return "redirect:/" + name;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String success(@PathVariable String name, Model model) {
        //checkuser logged or not

//        checkUser(name);
        model.addAttribute("name", name);

        return "success";
    }

    private void checkUser(String username) {
        if (!eblueRepository.isUserValid(username)) {
            throw new NoSuchDataException(username, true);
        }
    }
}
