package com.khh.web;

import com.khh.domain.FileDTO;
import com.khh.service.BoardService;
import com.khh.util.MediaUtils;
import com.khh.util.UploadFileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by hyunhokim on 2017. 5. 13..
 */
@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Resource(name = "uploadPath")
    private String uploadPath;

    @ResponseBody
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {

        logger.info("originalName: " + file.getOriginalFilename());

        return new ResponseEntity<>(
                UploadFileUtils.uploadFile(uploadPath,
                        file.getOriginalFilename(),
                        file.getBytes()),
                HttpStatus.CREATED
        );
    }

    @ResponseBody
    @RequestMapping("/displayFile")
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        logger.info("FILE NAME : " + fileName);

        try {
            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            MediaType mType = MediaUtils.getMediaType(formatName);

            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(uploadPath + fileName);

            if (mType != null) {
                headers.setContentType(mType);
            } else {

                fileName = fileName.substring(fileName.indexOf("_") + 1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\""
                                + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFile(String fileName) {
        logger.info("delete file: " + fileName);

        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        MediaType mType = MediaUtils.getMediaType(formatName);

        if (mType != null) {
            String front = fileName.substring(0, 12);
            String end = fileName.substring(14);

            new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
        }

        new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAllFiles", method = RequestMethod.POST)
    public ResponseEntity<String> deleteFiles(@RequestParam("files[]") String[] files) {
        logger.info("delete all files : " + files.toString());

        if(files == null || files.length ==0) {
            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }

        for(String fileName : files) {

            String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

            MediaType mType = MediaUtils.getMediaType(formatName);

            if(mType != null) {
                //thumbnail 삭제
                String front = fileName.substring(0, 12);
                String end = fileName.substring(12);

                new File(uploadPath + (front + "s_" + end).replace('/', File.separatorChar)).delete();
            }

            //원본 삭제
            new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
        }

        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadCkeditor", method = RequestMethod.POST)
    public String multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response, FileDTO fileDTO, Model model) {

        try {

            String result = UploadFileUtils.uploadImage(uploadPath,
                    fileDTO.getUpload().getOriginalFilename(),
                    fileDTO.getUpload().getBytes());

            String fileUrl = "/displayFile" + result;

            model.addAttribute("fileDTO", fileDTO);
            model.addAttribute("fileUrl", fileUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/ckeditorImageUpload";
    }

    //noti: fullName으로 들어오는 값에서 dot(.) 뒤에 값이 잘려서 정규식(:.+)으로 dot(.)뒤에 값도 포함도록함.
    @ResponseBody
    @RequestMapping("/displayFile/{year}/{month}/{day}/{fileName:.+}")
    public ResponseEntity<byte[]> displayFile(
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            @PathVariable("day") String day,
            @PathVariable("fileName") String fileName) throws Exception {
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            String path = year + "/" + month + "/" + day + "/" + fileName;

            in = new FileInputStream("/Users/hyunhokim/Documents/dev/study/" + path);

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.add("Content-Disposition", "attachment; filename=\""
                    + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }
}
