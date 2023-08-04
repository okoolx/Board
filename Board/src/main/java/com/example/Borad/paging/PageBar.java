package com.example.Board.paging;

import lombok.Getter;

nowPage // 현재 페이지
boardCntPerPage // 페이지당 출력할 board 개수
totalBoardCnt // 전체 board 개수
startPageNum // 해당 페이지에서의 첫번째 페이지 번호
endPageNum // 해당 페이지에서의 마지막 페이지 번호
prev // 이전 버튼
next // 다음 버튼
displayPageNumCnt // 페이지 번호 개수
*/

@Getter
public class PageBar {

    private PageStatus pageStatus; // 전달 될 페이지 상태 객체 변수
    private int totalBoardCnt; // 총 게시글 수
    private int startPageNum;
    private int endPageNum;
    private boolean prev; // 이전 버튼 유무
    private boolean next; // 다음 버튼 유무
    private int displayPageNumCnt = 5; // 화면에 보일 페이지 바 번호 링크의 개수

    public void setPageStatus(PageStatus pageStatus) {
        this.pageStatus = pageStatus;
    }

    public void setTotalBoardCnt(int totalBoardCnt) {
        this.totalBoardCnt = totalBoardCnt;
        calcData();
    }

    private void calcData() {
        endPageNum = (int) (Math.ceil(pageStatus.getNowPage() / (double) displayPageNumCnt) * displayPageNumCnt);
        startPageNum = (endPageNum - displayPageNumCnt) + 1;
        if(startPageNum <= 0) startPageNum = 1;
        int tempEndPage = (int) (Math.ceil(totalBoardCnt / (double) pageStatus.getBoardCntPerPage()));
        if (endPageNum > tempEndPage) {
            endPageNum = tempEndPage;
        }
        prev = startPageNum == 1 ? false : true;
        next = endPageNum * pageStatus.getBoardCntPerPage() < totalBoardCnt ? true : false;

    }

    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }

    public void setDisplayPageNumCnt(int displayPageNumCnt) {
        this.displayPageNumCnt = displayPageNumCnt;
    }

}
