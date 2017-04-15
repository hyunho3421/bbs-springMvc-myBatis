package com.khh.service;

import com.khh.domain.Board;
import com.khh.repository.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 14..
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public Board read(int no) throws Exception {
        return boardDAO.read(no);
    }

    @Override
    public List<Board> listAll() throws Exception {
        return boardDAO.listAll();
    }

    @Override
    public void add(Board board) throws Exception {
        boardDAO.add(board);
    }

    @Override
    public void delete(int no) throws Exception {
        boardDAO.delete(no);
    }

    @Override
    public void update(Board board) throws Exception {
        boardDAO.update(board);
    }

    @Override
    public int count() throws Exception {
        return boardDAO.count();
    }
}
