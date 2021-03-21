package com.lukas.ocr.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OCRResult {
    private ArrayList<Character> contents;

    public ArrayList<Character> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Character> contents) {
        this.contents = contents;
    }
}
