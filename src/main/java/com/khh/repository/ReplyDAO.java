package com.khh.repository;

import com.khh.domain.Criteria;
import com.khh.domain.Reply;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
public interface ReplyDAO {
    List<Reply> list(int bno) throws Exception;

    List<Reply> listPage(int bno, Criteria criteria);

    void create(Reply reply) throws Exception;

    void update(Reply reply) throws Exception;

    void delete(int rno) throws Exception;

    void initAutoIncrement() throws Exception;

    void deleteAll() throws Exception;

    int count() throws Exception;

    int count(int bno) throws Exception;
}
