package com.example.super_ponic_ultra.Demoone;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}