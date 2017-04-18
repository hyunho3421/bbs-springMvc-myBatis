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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by hyunhokim on 2017. 4. 14..
 */
@Controller
@RequestMapping("/bbs/*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGET() throws Exception {
        logger.info("register get .......");

        return "/bbs/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPOST(Board board, RedirectAttributes rttr) throws Exception {
        logger.info("register post .......");
        logger.info(board.toString());

        boardService.register(board);

        rttr.addFlashAttribute("msg", "register_success");

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGET(Model model) throws Exception {
        logger.info("show all list .......");

        model.addAttribute("list", boardService.listAll());

        return "/bbs/list";
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public String viewGET(@RequestParam("no") int no, Model model) throws Exception {
        logger.info("view no." + no + "........");

        model.addAttribute("board", boardService.read(no));

        return "/bbs/view";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removePOST(@RequestParam("no") int no, RedirectAttributes rttr) throws Exception {
        logger.info("remove no." + no + " ........");

        boardService.delete(no);

        rttr.addFlashAttribute("msg", "remove_success");

        return "redirect:/bbs/list";
    }

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modifyGET(@RequestParam("no") int no, Model model) throws Exception{
        logger.info("modify no." + no + " ........");

        model.addAttribute("board", boardService.read(no));

        return "/bbs/modify";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modifyPOST(Board board, RedirectAttributes rttr) throws Exception {
        logger.info("modify board ........");
        logger.info(board.toString());

        boardService.update(board);
        rttr.addFlashAttribute("msg", "success_modify");

        return "redirect:/bbs/list";
    }
}