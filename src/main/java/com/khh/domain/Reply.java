package com.khh.domain;

import lombok.Data;

import java.util.Date;

/**
 * Created by hyunhokim on 2017. 4. 27..
 */
@Data
public class Reply {
    private Integer rno;
    private Integer bno;
    private String replyText;
    private String replyer;
    private Date reg_date;
    private Date update_date;
}
