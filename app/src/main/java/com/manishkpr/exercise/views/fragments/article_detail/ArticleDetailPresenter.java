package com.manishkpr.exercise.views.fragments.article_detail;

import com.manishkpr.exercise.data.remote.BackendService;
import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.model.article.ArticleDetail;
import com.manishkpr.exercise.util.RxUtil;
import com.manishkpr.exercise.views.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailMvpView> {

    private final BackendService backendService;
    private Disposable disposable;

    @Inject
    public ArticleDetailPresenter(BackendService backendService) {
        this.backendService = backendService;
    }

    @Override
    public void attachView(ArticleDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) disposable.dispose();
    }

    public void loadArticle(int id) {
        checkViewAttached();
        RxUtil.dispose(disposable);
        backendService.getArticle(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ArticleDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleDetail articleDetail) {
                        getMvpView().showArticle(articleDetail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showArticleError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
