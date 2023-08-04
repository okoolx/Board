package com.example.Board.repository;

import com.example.Board.dto.Board;
import com.example.Board.paging.PageStatus;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardRepository {

    private final EntityManager em;

    public BoardRepository(EntityManager em){
        this.em = em;
    }
    // 게시글 모두 불러오기
    public List<Board> findAll() { 
        return em.createQuery("select m from Board m", Board.class).getResultList();
    }
      // 게시글 페이지 단위로 불러오기
//    public Page findByPageNum(int pageNum) { 
//        return em.createQuery("select m from Board m order by m.boardNo desc limit 10, 10", Board.class).getSingleResult();
//    }

    // 게시글 등록
    public Board save(Board board) { 
        em.persist(board);
        return board;
    }

    public Board update(Board board) {
        return null;
    }

    public Board delete(Board board) {
        em.remove(board);
        return board;
    }

    public Board findByBoardNo(Long boardNo){
        Board board = em.createQuery("select m from Board m where m.boardNo = :board_no", Board.class)
                .setParameter("board_no", boardNo)
                .getSingleResult();
        return board;
    }

    public List<Board> selectBoardList(PageStatus pageStatus){
        return em.createQuery("select m from Board m order by m.boardNo desc", Board.class)
                .setFirstResult(pageStatus.getPageStart()) 
                .setMaxResults(pageStatus.getBoardCntPerPage())
                .getResultList();
    }

    public int getTotalBoardCnt(){
        return em.createQuery("select count(m) from Board m", Long.class).getSingleResult().intValue();
    }

}
