package com.khh.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by hyunhokim on 2017. 6. 5..
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final String LOGIN = "login";
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute(LOGIN) != null) {
            logger.info("clear login data before");

            session.removeAttribute(LOGIN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();

        ModelMap modelMap = modelAndView.getModelMap();
        Object user = modelMap.get("user");

        if(user != null) {
            logger.info("new login success");
            session.setAttribute(LOGIN, user);

            Object dest = session.getAttribute("dest");

            response.sendRedirect(dest != null ? (String)dest : "/bbs/list");
        }
    }
}
