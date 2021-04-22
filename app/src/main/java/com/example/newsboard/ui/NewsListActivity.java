package com.example.newsboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsboard.R;
import com.example.newsboard.model.ArticleEntity;
import com.example.newsboard.store.DataStore;
import com.example.newsboard.store.OnDataReceivedListener;

import java.util.List;

public class NewsListActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private NewsFeedAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipeToRefresh);
        RecyclerView list = findViewById(R.id.list);

        adapter = new NewsFeedAdapter((view, selectedItem, position) -> {
            Intent intent = new Intent(NewsListActivity.this, ArticleDetailsActivity.class);
            intent.putExtra(ArticleDetailsActivity.ARTICLE_ID, String.valueOf(position));
            startActivity(intent);
        });

        list.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(this::requestArticles);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestArticles();
    }

    private void requestArticles() {
        DataStore.getInstance().getArticles(new OnDataReceivedListener<List<ArticleEntity>>() {
            @Override
            public void onDataReceived(List<ArticleEntity> data) {
                adapter.setData(data);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailed(Throwable throwable) {
                Toast.makeText(NewsListActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

