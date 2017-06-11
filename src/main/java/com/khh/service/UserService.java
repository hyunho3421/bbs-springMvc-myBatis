package com.khh.service;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;

import java.util.Date;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
public interface UserService {
    User login(LoginDTO dto) throws Exception;

    void keepLogin(String id, String sessionId, Date next);

    User checkUserWithSessionKey(String value);
}
