package com.khh.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by hyunhokim on 2017. 4. 13..
 */
@Data
public class Board {
    private int no;
    private String title;
    private String content;
    private String writer;
    private Date reg_date;
    private int view_cnt;
    private int reply_cnt;
    private String[] files;
}
