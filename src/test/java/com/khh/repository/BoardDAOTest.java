package com.khh.repository;

import com.khh.domain.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 13..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/**.xml")
public class BoardDAOTest {

    @Autowired
    private BoardDAO boardDAO;

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
        board.setWriter("hyunho");
        board.setTitle("test title");
        board.setContent("contents");
    }

    @Test
    public void addAndReadTest() throws Exception {
        init();

        boardDAO.add(board);

        Board getBoard = boardDAO.read(1);

        assertThat(getBoard.getNo(), is(1));
        assertThat(getBoard.getTitle(), is(board.getTitle()));
        assertThat(getBoard.getWriter(), is(board.getWriter()));
        assertThat(getBoard.getContent(), is(board.getContent()));

        System.out.println(board.toString());
    }

    @Test
    public void deleteAndAddTest() throws Exception {
        init();

        boardDAO.add(board);

        assertThat(boardDAO.count(), is(1));

        boardDAO.delete(1);

        assertThat(boardDAO.count(), is(0));
    }

    @Test
    public void listAllAndAddTest() throws Exception {
        init();

        boardDAO.add(board);
        boardDAO.add(board);
        boardDAO.add(board);
        boardDAO.add(board);

        assertThat(boardDAO.count(), is(4));

        List<Board> list = boardDAO.listAll();

        list.stream().forEach(
                e -> System.out.println("no : " + e.getNo() + ", title : " + e.getTitle() + ", content : " + e.getContent()
                +", writer : " + e.getWriter() + ", reg_date : " + e.getReg_date() + ", view count : " + e.getView_cnt())
        );

        assertThat(list.size(), is(4));
    }

    @Test
    public void updateAndAddTest() throws Exception {
        init();
    }

    public void init() throws Exception {
        boardDAO.deleteAll();
        assertThat(boardDAO.count(), is(0));

        boardDAO.initAutoIncrement();
    }


}
