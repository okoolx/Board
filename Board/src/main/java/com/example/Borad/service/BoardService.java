package com.example.Board.service;

import com.example.Board.dto.Board;
import com.example.Board.dto.User;
import com.example.Board.form.BoardForm;
import com.example.Board.paging.PageStatus;
import com.example.Board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> showBoardList(){
        return boardRepository.findAll();
    }

    public Board selectBoardByBoardNo(Long boardNo){
        return boardRepository.findByBoardNo(boardNo);
    }

    public Board createBoard(User user, BoardForm boardForm){
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setUserId(user.getId());
        board.setViews(0);
        board.setWrittenDate(getCurrentTime());
        return boardRepository.save(board);
    }

    public String getCurrentTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(timestamp);
    }

    public Board modifyBoard(Long boardNo, BoardForm boardForm){
        Board board = boardRepository.findByBoardNo(boardNo);
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        return boardRepository.save(board);
    }

    public Board showBoardDetail(Long boardNo){
        Board board = boardRepository.findByBoardNo(boardNo);
        board.setViews(board.getViews()+1);
        boardRepository.save(board); 
        return board;
    }

    public Board deleteBoard(Long boardNo){
        Board board = boardRepository.findByBoardNo(boardNo);
        return boardRepository.delete(board);
    }

    public List<Board> selectBoardList(PageStatus pageStatus){
        return boardRepository.selectBoardList(pageStatus);
    }

    public int getTotalCntBoard(){
        return boardRepository.getTotalBoardCnt();
    }

}
