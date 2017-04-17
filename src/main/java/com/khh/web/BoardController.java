package com.khh.web;

import com.khh.domain.Board;
import com.khh.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hyunhokim on 2017. 4. 14..
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET(Board board, Model model) throws Exception {
        logger.info("register get .......");

        return "/bbs/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(Board board, Model model) throws Exception {
        logger.info("register post .......");
        logger.info(board.toString());

        boardService.register(board);

        return "/bbs/list";
    }

}
