package com.manishkpr.exercise.views.fragments.article_detail;

import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.model.article.ArticleDetail;
import com.manishkpr.exercise.views.base.MvpView;

import java.util.List;


public interface ArticleDetailMvpView extends MvpView {
    void showArticle(ArticleDetail articleDetail);
    void showArticleError(String errMsg);
}
