package com.khh.repository;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SqlSession session;

    private static String namespace = "com.khh.mapper.UserMapper";

    @Override
    public User login(LoginDTO dto) throws Exception {
        return session.selectOne(namespace + ".login", dto);
    }
}
