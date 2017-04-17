package com.khh.service;

import com.khh.domain.Board;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 14..
 */
public interface BoardService {
    Board read(int no) throws Exception;

    List<Board> listAll() throws Exception;

    void register(Board board) throws Exception;

    void delete(int no) throws Exception;

    void update(Board board) throws Exception;

    int count() throws Exception;
}
