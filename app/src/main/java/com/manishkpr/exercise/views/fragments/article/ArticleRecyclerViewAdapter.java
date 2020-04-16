package com.manishkpr.exercise.views.fragments.article;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.manishkpr.exercise.R;
import com.manishkpr.exercise.model.article.Article;

import com.manishkpr.exercise.util.CircleTransformation;
import com.manishkpr.exercise.util.Format;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder> {

    List<Article> articleList;
    Context context;

    public ArticleRecyclerViewAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_articles_items, parent,false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.article_id.setText(articleList.get(position).title());
        viewHolder.article_description.setText(articleList.get(position).shortDescription());
        viewHolder.article_date.setText(Format.getDate(articleList.get(position).lastUpdate(),"yyyy-MM-dd"));

        Picasso.get().load(articleList.get(position).avatar())
            .transform(new CircleTransformation())
            .into(viewHolder.article_image, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.article_id)           TextView article_id;
        @BindView(R.id.article_date)         TextView article_date;
        @BindView(R.id.article_description)  TextView article_description;

        @BindView(R.id.article_image)        ImageView article_image;
        @BindView(R.id.progressBar)          ProgressBar progressBar;



        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }

        @Override
        public void onClick(View v) {
            int position  =   getAdapterPosition();
            Logger.i(" "+ position);
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
