package com.manishkpr.exercise.views.fragments.article;

import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.views.base.MvpView;

import java.util.List;


public interface ArticleMvpView extends MvpView {
    void showArticles( List<Article> articleList);
    void showArticlesError( String errMsg);
}
