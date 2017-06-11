package com.khh.repository;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void keepLogin(String id, String sessionId, Date next) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        session.update(namespace + ".keepLogin", paramMap);
    }

    @Override
    public User checkUserWithSessionKey(String value) {
        return session.selectOne(namespace + ".checkUserWithSessionKey", value);
    }
}
