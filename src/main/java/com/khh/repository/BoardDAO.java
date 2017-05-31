package com.khh.repository;

import com.khh.domain.Board;
import com.khh.domain.Criteria;
import com.khh.domain.SearchCriteria;

import java.util.List;

/**
 * Created by hyunhokim on 2017. 4. 13..
 */
public interface BoardDAO {
    Board read(int no) throws Exception;

    List<Board> listAll() throws Exception;

    void create(Board board) throws Exception;

    void delete(int no) throws Exception;

    void update(Board board) throws Exception;

    void initAutoIncrement() throws Exception;

    void deleteAll() throws Exception;

    int count() throws Exception;

    void increaseViewCnt(int no) throws Exception;

    List<Board> listPage(Criteria cri) throws Exception;

    List<Board> list(SearchCriteria cri) throws Exception;

    int count(SearchCriteria cri) throws Exception;

    void increaseReplyCnt(int no) throws Exception;

    void addAttach(String fullName) throws Exception;

    List<String> getAttach(int bno) throws Exception;

    void replaceAttach(String fullName, int bno) throws Exception;

    void deleteAttach(int bno) throws Exception;
}
