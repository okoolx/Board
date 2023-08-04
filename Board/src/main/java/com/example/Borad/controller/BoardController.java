package com.example.Board.controller;

import com.example.Board.alert.Message;
import com.example.Board.dto.Board;
import com.example.Board.dto.Comment;
import com.example.Board.dto.User;
import com.example.Board.paging.PageStatus;
import com.example.Board.paging.PageBar;
import com.example.Board.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ModelAndView getBoardPage(@RequestParam(value = "page") int page, ModelAndView mv){
        mv.setViewName("board-list");
        PageStatus pageStatus = new PageStatus();
        pageStatus.setNowPage(page);
        PageBar pageBar = new PageBar();
        pageBar.setPageStatus(pageStatus);
        pageBar.setTotalBoardCnt(boardService.getTotalCntBoard());
        List<Board> boardList = boardService.selectBoardList(pageStatus);
        mv.addObject("boardList", boardList);
        mv.addObject("pageBar", pageBar);
        return mv;
    }

    @GetMapping("/write")
    public String enterBoardForm(Model model){
        model.addAttribute("boardForm", new BoardForm());
        return "board-form";
    }

  

    @GetMapping("/modify") // 게시글 수정 페이지 불러오기
    public String enterBoardModifyForm(@RequestParam("boardNo") Long boardNo, Model model){
        Board board = boardService.selectBoardByBoardNo(boardNo);
        BoardForm boardForm = new BoardForm();
        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        model.addAttribute("boardForm", boardForm);
        model.addAttribute("boardNo", boardNo);
        return "board-modify-form";
    }

    @PostMapping("/modify/{boardNo}") 
    public ModelAndView modifyBoard(@Valid @ModelAttribute BoardForm boardForm, BindingResult bindingResult, @PathVariable("boardNo") Long boardNo, ModelAndView mv){

        if(bindingResult.hasErrors()){
            mv.addObject("boardNo", boardNo); /
            mv.setViewName("board-modify-form");
            return mv;
        }

        boardService.modifyBoard(boardNo, boardForm); 

        mv.addObject("data", new Message("게시글이 수정되었습니다.", "/board/detail?boardNo="+boardNo)); 
        mv.setViewName("alert-page");
        return mv;
    }
    
    @PostMapping("/reply")
    public Comment writeComment(@RequestBody Comment comment){
        return comment;
    }

    @GetMapping("/detail")
    public String getBoardDetails(@RequestParam("boardNo") Long boardNo, Model model){ // 게시글 상세 조회
        Board board = boardService.showBoardDetail(boardNo);
        model.addAttribute("board", board);
        return "board-detail";
    }

    @GetMapping ("/delete")
    public ModelAndView deleteBoard(@RequestParam("boardNo") Long boardNo, ModelAndView mv){
        boardService.deleteBoard(boardNo);
        mv.addObject("data", new Message("게시글이 삭제되었습니다.", "/board/list?page=1")); 
        mv.setViewName("alert-page");
        return mv;
    }

}
