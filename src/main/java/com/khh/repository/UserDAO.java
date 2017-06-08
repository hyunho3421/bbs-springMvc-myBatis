package com.khh.repository;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
public interface UserDAO {
    User login(LoginDTO dto) throws Exception;
}
