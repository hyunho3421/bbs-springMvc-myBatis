package com.khh.repository;

import com.khh.domain.Board;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 13..
 */
public interface BoardDAO {
    Board read(int no) throws Exception;

    List<Board> listAll() throws Exception;

    void add(Board board) throws Exception;

    void delete(int no) throws Exception;

    void update(Board board) throws Exception;

    void initAutoIncrement() throws Exception;

    void deleteAll() throws Exception;

    int count() throws Exception;

    void increaseViewCnt(int no) throws Exception;
}
