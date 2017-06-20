package com.khh.interceptor;

import com.khh.domain.Board;
import com.khh.domain.User;
import com.khh.service.BoardService;
import com.khh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private BoardService boardService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        String qs = request.getQueryString();

        if(qs != null) {
            int no_index = qs.lastIndexOf("no=");
            int bbs_no = 0;

            if(no_index > -1) {
                int amp_index = qs.indexOf("&", no_index);

                if(amp_index > -1) {
                    bbs_no = Integer.parseInt((qs.substring(no_index + 3, amp_index)));
                } else {
                    bbs_no = Integer.parseInt(qs.substring(no_index + 3));
                }

                Board board = boardService.read(bbs_no);

                if (!board.getWriter().equals(authentication.getName())) {
                    response.sendRedirect("/bbs/denied");
                }
            }
        }

        return true;

    }

}
