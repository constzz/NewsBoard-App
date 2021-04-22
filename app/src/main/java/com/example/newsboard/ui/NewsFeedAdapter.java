package com.example.newsboard.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsboard.R;
import com.example.newsboard.model.ArticleEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {
    private List<ArticleEntity> data = new ArrayList<>();

    private final OnItemClickListener onItemClickListener;

    public NewsFeedAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View articleItemView = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(articleItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleEntity curArticle = data.get(position);
        Picasso.get()
                .load(curArticle.getImage())
                .error(R.drawable.image_error)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.image);
        holder.time.setText(curArticle.getTime());
        holder.title.setText(curArticle.getTitle());

        holder.itemView.setOnClickListener(view -> {
            onItemClickListener.onItemClick(holder.itemView, curArticle, position);
        });
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public void setData(List<ArticleEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            time = (TextView) itemView.findViewById(R.id.article_time);
        }
    }

    interface OnItemClickListener {
        void onItemClick(View view, ArticleEntity selectedItem, int position);
    }
}
