package com.manishkpr.exercise.views.fragments.article_detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;



import com.manishkpr.exercise.R;

import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.model.article.ArticleDetail;
import com.manishkpr.exercise.util.AppConstants;
import com.manishkpr.exercise.util.CircleTransformation;
import com.manishkpr.exercise.views.base.BaseFragment;
import com.manishkpr.exercise.views.fragments.article.ArticlePresenter;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailFragment extends BaseFragment implements ArticleDetailMvpView {

    @Inject ArticleDetailPresenter articleDetailPresenter;
    @BindView(R.id.article_description)  TextView articleDescription;
    @BindView(R.id.detail_layout)        LinearLayout detailLayout;
    @BindView(R.id.article_image)        ImageView article_image;
    @BindView(R.id.progressBar)          ProgressBar progressBar;

    MenuItem actionEdit;
    MenuItem actionCancel;

    Article article;
    ArticleDetail articleDetail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_article_detail, null);
        setUpView(root);
        setHasOptionsMenu(true);
        return root;
    }

    void setUpView(ViewGroup root) {
        ButterKnife.bind(this, root);
        articleDetailPresenter.attachView(this);
        getRequest();
    }

    void getRequest(){
        detailLayout.setVisibility(View.GONE);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            article = bundle.getParcelable(AppConstants.BUNDLE_ARTICLE_ITEM);

            if(article!=null){
                getActivity().setTitle(article.title());
                articleDetailPresenter.loadArticle(article.id());
            }
        }
    }

    @Override
    public void showArticle(ArticleDetail articleDetail) {
        this.articleDetail = articleDetail;
        articleDescription.setText(articleDetail.text());
        setAvatar();
        layoutProgress.setVisibility(View.GONE);
        detailLayout.setVisibility(View.VISIBLE);
    }

    void setAvatar() {
        Picasso.get().load(article.avatar())
                .transform(new CircleTransformation())
                .into(article_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public void showArticleError(String errMsg) {
        layoutProgress.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE);
        article_error_msg.setText(errMsg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_article, menu);
        actionEdit = menu.findItem(R.id.action_edit);
        actionCancel = menu.findItem(R.id.action_cancel);
        actionCancel.setVisible(false);
    }

    void menuToggle(){
        if(actionCancel.isVisible()){
            actionCancel.setVisible(false);
            actionEdit.setVisible(true);
        }else{
            actionCancel.setVisible(true);
            actionEdit.setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                Logger.i("edit");
                menuToggle();
                return true;
            case R.id.action_cancel:
                Logger.i("cancel");
                menuToggle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
