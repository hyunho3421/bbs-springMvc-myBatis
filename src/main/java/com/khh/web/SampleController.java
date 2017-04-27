package com.khh.web;

import com.khh.domain.Board;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyunhokim on 2017. 4. 26..
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    @RequestMapping("/hello")
    public ResponseEntity<Board> sayHello() {
        Board board = new Board();
        board.setNo(1);
        board.setTitle("제목");
        board.setContent("내용");
        board.setWriter("김현호");
        board.setView_cnt(0);

        return new ResponseEntity<>(board, HttpStatus.BAD_REQUEST);
    }
}
