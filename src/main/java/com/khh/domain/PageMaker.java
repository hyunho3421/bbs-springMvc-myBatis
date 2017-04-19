package com.khh.domain;

import lombok.Data;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by hyunhokim on 2017. 4. 19..
 */
@Data
public class PageMaker {
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private Criteria criteria;

    //pagination 개수
    private int displayPageNum = 10;

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;

        calcPage();
    }

    private void calcPage() {

        endPage = (int) ( Math.ceil((double) criteria.getPage() / displayPageNum) * displayPageNum );

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) ( Math.ceil((double) totalCount / criteria.getPerPageNum()) );

        if(endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
    }

    public String makeQuery(int page) {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", criteria.getPerPageNum())
                .build();

        return uriComponents.toUriString();
    }
}
