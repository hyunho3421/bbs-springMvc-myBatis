package com.khh.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hyunhokim on 2017. 7. 11..
 */
public class FileDTO {
    private String CKEditorFuncNum;
    private MultipartFile upload;

    public String getCKEditorFuncNum() {
        return CKEditorFuncNum;
    }

    public void setCKEditorFuncNum(String CKEditorFuncNum) {
        this.CKEditorFuncNum = CKEditorFuncNum;
    }

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }
}
