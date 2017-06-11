package com.khh.service;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public void keepLogin(String id, String sessionId, Date next) {
        userDAO.keepLogin(id, sessionId, next);
    }

    @Override
    public User checkUserWithSessionKey(String value) {
        return userDAO.checkUserWithSessionKey(value);
    }

    @Override
    public void joinUser(User user) {
        userDAO.joinUser(user);
    }

    @Override
    public User checkExistID(String id) {
        return userDAO.checkExistID(id);
    }
}
