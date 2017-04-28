package com.khh.service;

import com.khh.domain.Reply;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
public interface ReplyService {
    List<Reply> list(int bno) throws Exception;

    void register(Reply reply) throws Exception;

    void modify(Reply reply) throws Exception;

    void delete(int rno) throws Exception;
}
