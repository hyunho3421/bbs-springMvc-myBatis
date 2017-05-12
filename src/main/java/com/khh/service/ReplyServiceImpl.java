package com.khh.service;

import com.khh.domain.Criteria;
import com.khh.domain.Reply;
import com.khh.repository.BoardDAO;
import com.khh.repository.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDAO replyDAO;

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<Reply> list(int bno, Criteria criteria) throws Exception {
        return replyDAO.listPage(bno, criteria);
    }

    @Transactional
    @Override
    public void register(Reply reply) throws Exception {
        replyDAO.create(reply);
        boardDAO.increaseReplyCnt(1010);
    }

    @Override
    public void modify(Reply reply) throws Exception {
        replyDAO.update(reply);
    }

    @Override
    public void delete(int rno) throws Exception {
        replyDAO.delete(rno);
    }

    @Override
    public int count(int bno) throws Exception {
        return replyDAO.count(bno);
    }
}
