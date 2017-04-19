package com.khh.repository;

import com.khh.domain.Board;
import com.khh.domain.Criteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hyunhokim on 2017. 4. 13..
 */
@Repository
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String namespace = "com.khh.mapper.BoardMapper";

    @Override
    public Board read(int no) throws Exception {
        return sqlSession.selectOne(namespace + ".read", no);
    }

    @Override
    public List<Board> listAll() throws Exception {
        return sqlSession.selectList(namespace + ".listAll");
    }

    @Override
    public void add(Board board) throws Exception {
        sqlSession.insert(namespace + ".add", board);
    }

    @Override
    public void delete(int no) throws Exception {
        sqlSession.delete(namespace + ".delete", no);
    }

    @Override
    public void update(Board board) throws Exception {
        sqlSession.update(namespace + ".update", board);
    }

    @Override
    public void initAutoIncrement() throws Exception {
        sqlSession.selectOne(namespace + ".initAutoIncrement");
    }

    @Override
    public void deleteAll() throws Exception {
        sqlSession.delete(namespace + ".deleteAll");
    }

    @Override
    public int count() throws Exception {
        return sqlSession.selectOne(namespace + ".count");
    }

    @Override
    public void increaseViewCnt(int no) throws Exception {
        sqlSession.update(namespace + ".increaseViewCnt", no);
    }

    @Override
    public List<Board> listPage(Criteria cri) throws Exception {
        return sqlSession.selectList(namespace + ".listPage", cri);
    }
}
