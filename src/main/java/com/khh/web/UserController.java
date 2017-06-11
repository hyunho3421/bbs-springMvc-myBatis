package com.khh.web;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String LOGIN = "login";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",  method = RequestMethod.GET)
    public String loginGET(@ModelAttribute("dto")LoginDTO dto) {

        return "/user/login";
    }

    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public String loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
        User user  = userService.login(dto);

        if(user == null) {
            return "/user/login";
        }

        model.addAttribute("user", user);

        if (dto.isUseCookie()) {
            int amount = 60 * 60 * 24 * 7;

            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));

            userService.keepLogin(user.getId(), session.getId(), sessionLimit);
        }

        return "/user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logoutGET(
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        Object obj = session.getAttribute("login");

        if(obj != null) {
            User user = (User) obj;

            //세션 삭제
            session.removeAttribute("login");
            session.invalidate();

            //쿠키 삭제
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                loginCookie.setValue("");
                loginCookie.setPath("/");
                loginCookie.setMaxAge(0);
                response.addCookie(loginCookie);
            }

            return new ResponseEntity<String>("logout_success", HttpStatus.OK);
        }

        return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinGET(Model model) throws Exception {

        return "/user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String joinPOST(User user, HttpServletRequest request) throws Exception {

        userService.joinUser(user);

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN, user);

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
