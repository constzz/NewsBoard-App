package com.example.newsboard.store;

public interface OnDataReceivedListener<T> {
    void onDataReceived(T data);
    void onFailed(Throwable throwable);
}