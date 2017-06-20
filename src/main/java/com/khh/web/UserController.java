package com.khh.web;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.service.UserDetailService;
import com.khh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    protected UserDetailService userDetailService;

    @RequestMapping(value = "/login",  method = RequestMethod.GET)
    public String loginGET(@ModelAttribute("dto")LoginDTO dto, HttpServletRequest request) {

        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);

        return "/user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logoutGET() throws Exception {

        return new ResponseEntity<String>("logout_success", HttpStatus.OK);
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinGET(Model model) throws Exception {

        return "/user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinPOST(User user, HttpServletRequest request) throws Exception {

        userService.joinUser(user);

        UserDetails ckUserDetails = userDetailService.loadUserByUsername(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(ckUserDetails, user.getPassword(), ckUserDetails.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/bbs/list";
    }

    @ResponseBody
    @RequestMapping(value = "/join/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> checkExistID(@PathVariable("id") String id) throws Exception {

        User isUser = userService.checkExistID(id);

        if(isUser != null) {
            logger.info("exist id : " + id);

            return new ResponseEntity<String>("existID", HttpStatus.OK);
        }

        logger.info("not exist id : " + id);
        return new ResponseEntity<String>("notExistID", HttpStatus.OK);
    }
}
