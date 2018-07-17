package com.home.http.entity;

import android.content.Context;

import java.util.List;


public class WordResult {
    int error_code;
    String reason;
//    Content result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

//    public Content getResult() {
//        return result;
//    }
//
//    public void setResult(Content result) {
//        this.result = result;
//    }

    public static class Content {
        String bushou;
        String head;
        String pinyin;
        String chengyujs;
        String from_;
        String example;
        String yufa;
        String ciyujs;
        String yinzhengjs;
        List<String> tongyi;
        List<String> fanyi;
    }
}
