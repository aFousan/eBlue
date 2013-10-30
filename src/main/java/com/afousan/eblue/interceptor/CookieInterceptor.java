/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afousan.eblue.interceptor;

import com.afousan.eblue.repo.EblueRepository;
import com.afousan.eblue.security.EblueSecurity;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author MacMini
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

    public static final String EBLUE_COOKIE = "eblueauth";
    @Inject
    private EblueRepository eblueRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookies[] = request.getCookies();
        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (EBLUE_COOKIE.equals(cookie.getName())) {
                    String auth = cookie.getValue();
                    String name = eblueRepository.findNameForAuth(auth);
                    if (name != null) {
                        String uid = eblueRepository.findUid(name);
                        EblueSecurity.setUser(name, uid);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView); //To change body of generated methods, choose Tools | Templates.
    }
}
