package com.example.newsboard.store;


import com.example.newsboard.model.ArticleEntity;
import com.example.newsboard.network.NetworkManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataStore {
    private static DataStore instance;

    private DataStore() {
    }

    synchronized public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void getArticles(OnDataReceivedListener<List<ArticleEntity>> responseListener) {
        NetworkManager.getService().getProducts().enqueue(new Callback<List<ArticleEntity>>() {
            @Override
            public void onResponse(Call<List<ArticleEntity>> call, Response<List<ArticleEntity>> response) {
                responseListener.onDataReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<ArticleEntity>> call, Throwable t) {
                responseListener.onFailed(t);
            }
        });
    }

    public void getArticleByIndex(String productIndex, OnDataReceivedListener<ArticleEntity> responseListener) {
        NetworkManager.getService().getProductByIndex(productIndex).enqueue(new Callback<ArticleEntity>() {
            @Override
            public void onResponse(Call<ArticleEntity> call, Response<ArticleEntity> response) {
                responseListener.onDataReceived((response.body()));
            }

            @Override
            public void onFailure(Call<ArticleEntity> call, Throwable t) {
                responseListener.onFailed(t);
            }
        });
    }
}

