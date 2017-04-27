package com.khh.repository;

import com.khh.domain.Board;
import com.khh.domain.Reply;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/**.xml")
public class ReplyDAOTest {
    @Autowired
    private ReplyDAO replyDAO;

    @Autowired
    private BoardDAO boardDAO;

    private Reply reply;
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.setWriter("현호");
        board.setTitle("제목");
        board.setContent("내용");

        reply = new Reply();
        reply.setBno(1);
        reply.setReplyText("리플 테스트");
        reply.setReplyer("익명");
    }

    @Test
    public void addAndReadReplyTest() throws Exception{
        init();

        boardDAO.create(board);

        replyDAO.create(reply);

        List<Reply> replies = replyDAO.list(1);
        Reply getReply = replies.get(0);

        assertThat(getReply.getBno(), is(reply.getBno()));
        assertThat(getReply.getReplyText(), is(reply.getReplyText()));
        assertThat(getReply.getReplyer(), is(reply.getReplyer()));
    }

    @Test
    public void addAndDeleteReplyTest() throws Exception {
        init();

        boardDAO.create(board);

        replyDAO.create(reply);

        replyDAO.delete(1);

        List<Reply> list = replyDAO.list(1);

        assertThat(list.size(), is(0));
    }

    @Test
    public void addAndUpdate() throws Exception {
        init();

        boardDAO.create(board);

        replyDAO.create(reply);

        Reply updatedReply = new Reply();
        updatedReply.setRno(1);
        updatedReply.setReplyText("수정된 리플");

        replyDAO.update(updatedReply);

        List<Reply> replies = replyDAO.list(1);
        Reply getReply = replies.get(0);

        assertThat(getReply.getReplyText(), is(updatedReply.getReplyText()));
    }

    public void init() throws Exception {
        boardDAO.deleteAll();
        assertThat(boardDAO.count(), is(0));

        boardDAO.initAutoIncrement();

        replyDAO.deleteAll();
        assertThat(replyDAO.count(), is(0));

        replyDAO.initAutoIncrement();
    }
}
