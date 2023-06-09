package com.liuyetech.myapplication.entity;

import android.text.TextUtils;

import java.util.Map;

public class PayResult {
    private String resultStatus;
    private String result;
    private String memo;

    public PayResult() {
    }

    public PayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "PayResult{" +
                "resultStatus='" + resultStatus + '\'' +
                ", result='" + result + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
