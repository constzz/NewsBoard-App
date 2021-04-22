package com.example.newsboard.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newsboard.R;
import com.example.newsboard.model.ArticleEntity;
import com.example.newsboard.store.DataStore;
import com.example.newsboard.store.OnDataReceivedListener;
import com.squareup.picasso.Picasso;

public class ArticleDetailsActivity extends AppCompatActivity {
    public static final String ARTICLE_ID = "article_id_from_news_list";

    private ImageView image;
    private TextView title;
    private TextView content;
    private TextView time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_page);

        initViews();
        Bundle externalArgs = getIntent().getExtras();
        if (externalArgs != null) {
            String articleId = externalArgs.getString(ARTICLE_ID);
            if ((articleId != null)) {
                requestArticle(articleId);
            }
        }
    }

    private void initViews() {
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        time = findViewById(R.id.time);
    }

    private void applyData(ArticleEntity data) {
        title.setText(data.getTitle());
        time.setText(data.getTime());
        Picasso.get()
                .load(data.getImage())
                .error(R.drawable.image_error)
                .placeholder(R.drawable.image_placeholder)
                .into(image);
        content.setText(data.getContent());
    }

    private void requestArticle(String articleByIndex) {
        DataStore.getInstance().getArticleByIndex(articleByIndex, new OnDataReceivedListener<ArticleEntity>() {
            @Override
            public void onDataReceived(ArticleEntity data) {
                applyData(data);
            }

            @Override
            public void onFailed(Throwable throwable) {
                Toast.makeText(ArticleDetailsActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
