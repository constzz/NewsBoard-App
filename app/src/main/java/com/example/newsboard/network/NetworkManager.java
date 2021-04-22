package com.example.newsboard.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static ArticleService service;

    private static ArticleService createService() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://newsboardapp-b2daf-default-rtdb.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  client.create(ArticleService.class);
    }

    synchronized public static ArticleService getService() {
        if (service == null) {
            service = createService();
        }
        return service;
    }
}
