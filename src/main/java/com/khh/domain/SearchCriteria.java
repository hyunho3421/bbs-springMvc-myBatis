package com.khh.domain;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 4. 20..
 */
@Data
public class SearchCriteria extends Criteria{
    private String searchType;
    private String keyword;
}
