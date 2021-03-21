package com.lukas.ocr.service;

import com.lukas.ocr.entity.OCRResult;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class OCRService {

    @Resource
    private OCRResult ocrResult;

    public OCRResult doOCRService(MultipartFile file) throws TesseractException {
        // 文件存储路径
        String path = "D:/Pictures";
        // 获取文件名(UUID+文件原名)
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        File filePath = new File(path, fileName);
        try {
            // 写入文件
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //OCR识别英文字母
        File imageFile = new File("D:/Pictures/" + fileName);
        Tesseract tessreact = new Tesseract();
        tessreact.setDatapath("D:\\tessdata");

        String result = tessreact.doOCR(imageFile);
        ArrayList<Character> letters = new ArrayList<>();
        for(int i=0;i<result.length();i++){
            if(result.charAt(i) >= 'A' && result.charAt(i) <= 'Z'
                    ||result.charAt(i)>='a' && result.charAt(i) <= 'z'){
                letters.add(result.charAt(i));
            }
        }
        ocrResult.setContents(letters);
        return ocrResult;
    }
}
