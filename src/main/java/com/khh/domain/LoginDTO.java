package com.khh.domain;

import lombok.Data;

/**
 * Created by hyunhokim on 2017. 6. 5..
 */
@Data
public class LoginDTO {
    private String id;
    private String password;
    private boolean useCookie;
}
