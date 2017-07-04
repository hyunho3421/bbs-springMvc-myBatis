package com.khh.interceptor;

import com.khh.domain.User;
import com.khh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hyunhokim on 2017. 6. 6..
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute("login") == null) {
            logger.info("current user is not logined");

            saveDest(request);

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                User user = userService.checkUserWithSessionKey(loginCookie.getValue());

                logger.info("User" + user.toString());

                if(user != null) {
                    session.setAttribute("login", user);
                    return true;
                }
            }

            if(request.getRequestURI().equals("/bbs/list")) {
                return request.getRequestURI().equals("/bbs/list");
            }

            response.sendRedirect("/user/login");
            return false;
        }

        return true;
    }

    private void saveDest(HttpServletRequest request) {
        String uri = request.getRequestURI();

        String query = request.getQueryString();

        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = "?" + query;
        }

        if (request.getMethod().equals("GET")) {
            logger.info("dest: " + uri + query);
            request.getSession().setAttribute("dest", uri + query);
        }
    }
}
