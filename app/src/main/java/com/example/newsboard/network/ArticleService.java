package com.example.newsboard.network;

import com.example.newsboard.model.ArticleEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleService {
    @GET(".json")
    Call<List<ArticleEntity>> getProducts();

    @GET("/{id}.json")
    Call<ArticleEntity> getProductByIndex(@Path("id") String articleId);
}