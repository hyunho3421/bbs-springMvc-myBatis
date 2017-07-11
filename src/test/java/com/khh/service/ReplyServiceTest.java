package com.khh.service;

import com.khh.domain.Board;
import com.khh.repository.BoardDAO;
import com.khh.repository.ReplyDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hyunhokim on 2017. 5. 12..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/**.xml")
public class ReplyServiceTest {

    @Autowired
    ReplyService replyService;

    @Autowired
    BoardDAO boardDAO;

    @Autowired
    ReplyDAO replyDAO;

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.setTitle("테스트 타이틀");
        board.setContent("테스트 내용");
        board.setWriter("테스터");
    }

    @Test
    public void replyRegisterTxTest() throws Exception{
        init();

        boardDAO.create(board);


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
