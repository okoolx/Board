package com.example.Board.paging;

import lombok.Getter;

@Getter
public class PageStatus {

    private int nowPage;
    private int boardCntPerPage;
    public int getPageStart(){
        return (this.nowPage -1)* boardCntPerPage;
    }

    public PageStatus(){
        this.nowPage = 1;
        this.boardCntPerPage = 10;
    }

    public void setNowPage(int nowPage){
        if(nowPage <= 0){
            this.nowPage = 1;
        }
        else{
            this.nowPage = nowPage;
        }
    }

    public void setBoardCntPerPage(int pageCount){ 
        int cnt = this.boardCntPerPage;
        if(pageCount != cnt){
            this.boardCntPerPage = cnt;
        }
        else{
            this.boardCntPerPage = pageCount;
        }
    }

}
