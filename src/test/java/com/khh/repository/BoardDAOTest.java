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
        board.setWriter("현호");
        board.setTitle("제목");
        board.setContent("내용");
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

        boardDAO.add(board);

        board.setNo(1);
        board.setTitle("수정 제목");
        board.setContent("수정 내용");
        board.setWriter("수정 작성자");

        boardDAO.update(board);

        Board updatedBoard = boardDAO.read(1);

        assertThat(updatedBoard.getTitle(), is("수정 제목"));
        assertThat(updatedBoard.getContent(), is("수정 내용"));
        assertThat(updatedBoard.getWriter(), is("수정 작성자"));

    }

    @Test
    public void increaseViewCount() throws Exception {
        init();

        int view_cnt;

        boardDAO.add(board);
        view_cnt = boardDAO.read(1).getView_cnt();
        assertThat(view_cnt, is(0));

        //view count 한번 증가
        boardDAO.increaseViewCnt(1);
        view_cnt = boardDAO.read(1).getView_cnt();
        assertThat(view_cnt, is(1));

        //view count 한번 증가
        boardDAO.increaseViewCnt(1);
        view_cnt = boardDAO.read(1).getView_cnt();
        assertThat(view_cnt, is(2));

        //view count 한번 증가
        boardDAO.increaseViewCnt(1);
        view_cnt = boardDAO.read(1).getView_cnt();
        assertThat(view_cnt, is(3));

    }

    public void init() throws Exception {
        boardDAO.deleteAll();
        assertThat(boardDAO.count(), is(0));

        boardDAO.initAutoIncrement();
    }


}
