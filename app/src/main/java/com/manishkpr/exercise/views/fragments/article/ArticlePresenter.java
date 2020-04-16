package com.manishkpr.exercise.views.fragments.article;

import com.manishkpr.exercise.data.remote.BackendService;
import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.util.RxUtil;
import com.manishkpr.exercise.views.base.BasePresenter;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArticlePresenter extends BasePresenter<ArticleMvpView> {

    private final BackendService backendService;
    private Disposable disposable;

    @Inject
    public ArticlePresenter(BackendService backendService) {
        this.backendService = backendService;
    }

    @Override
    public void attachView(ArticleMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) disposable.dispose();
    }

    public void loadArticles() {
        checkViewAttached();
        RxUtil.dispose(disposable);
        backendService.getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Article>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Article> articles) {
                        getMvpView().showArticles(articles);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showArticlesError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
