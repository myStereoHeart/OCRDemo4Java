package com.lukas.ocr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas.ocr.entity.OCRResult;
import com.lukas.ocr.service.OCRService;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class OCRController {
    @Resource
    private OCRService ocrService;

    @PostMapping("/ocr")
    @ResponseBody
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request,
                            HttpServletResponse response, HttpSession session) throws IOException, TesseractException {
        if(file.isEmpty()){
            return "上传失败,文件为空";
        } else if(!file.getContentType().equals("image/jpeg")){
            if(!file.getContentType().equals("image/png")){
                return "文件格式不正确,需上传jpg或png格式文件.";
            }
        }
        OCRResult ocrResult = ocrService.doOCRService(file);

        // java对象转换为json字符换
        ObjectMapper mapper = new ObjectMapper();
        String result =  mapper.writeValueAsString(ocrResult);
        return result;

    }

}
