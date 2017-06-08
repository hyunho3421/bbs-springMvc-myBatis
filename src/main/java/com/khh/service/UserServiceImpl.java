package com.khh.service;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(LoginDTO dto) throws Exception {
        return userDAO.login(dto);
    }
}
