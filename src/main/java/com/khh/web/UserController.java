package com.khh.web;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
