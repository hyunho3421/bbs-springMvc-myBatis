package com.khh.service;

import com.khh.domain.Reply;
import com.khh.repository.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDAO replyDAO;

    @Override
    public List<Reply> list(int bno) throws Exception {
        return replyDAO.list(bno);
    }

    @Override
    public void register(Reply reply) throws Exception {
        replyDAO.create(reply);
    }

    @Override
    public void modify(Reply reply) throws Exception {
        replyDAO.update(reply);
    }

    @Override
    public void delete(int rno) throws Exception {
        replyDAO.delete(rno);
    }
}
