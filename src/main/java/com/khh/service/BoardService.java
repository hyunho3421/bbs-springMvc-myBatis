package com.khh.service;

import com.khh.domain.Board;
import com.khh.domain.Criteria;
import com.khh.domain.SearchCriteria;

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

    int count(SearchCriteria cri) throws Exception;

    List<Board> listPage(Criteria criteria) throws Exception;

    List<Board> list(SearchCriteria criteria) throws Exception;
}
