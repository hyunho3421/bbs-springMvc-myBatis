package com.khh.web;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Controller
@RequestMapping("/user")
public class UserController {
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

        return "/user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logoutGET(
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        Object obj = session.getAttribute("login");

        if(obj != null) {
            User user = (User) obj;

            session.removeAttribute("login");
            session.invalidate();

            return new ResponseEntity<String>("logout_success", HttpStatus.OK);
        }

        return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
    }
}
