package com.manishkpr.exercise.views.fragments.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manishkpr.exercise.MainActivity;
import com.manishkpr.exercise.R;
import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.util.AppConstants;
import com.manishkpr.exercise.util.FragmentStackManager;
import com.manishkpr.exercise.util.RecyclerItemClickListener;
import com.manishkpr.exercise.views.base.BaseFragment;
import com.manishkpr.exercise.views.fragments.article_detail.ArticleDetailFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFragment extends BaseFragment implements ArticleMvpView {

    @Inject
    ArticlePresenter articlePresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView (R.id.layout_progress)
    View layoutProgress;

    @BindView (R.id.layout_error)
    View layout_error;


    @BindView (R.id.article_error_msg)
    TextView article_error_msg;



    ArticleRecyclerViewAdapter articleRecyclerViewAdapter;
    List<Article> articleList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_article, null);
        setUpView(root);
        return root;
    }

    void setUpView(ViewGroup root) {
        ButterKnife.bind(this, root);
        articlePresenter.attachView(this);
        articlePresenter.loadArticles();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        articlePresenter.detachView();
    }

    @Override
    public void showArticles(List<Article> articleList) {
        layoutProgress.setVisibility(View.GONE);
        layout_error.setVisibility(View.GONE);
        this.articleList = articleList;
        articleRecyclerViewAdapter = new ArticleRecyclerViewAdapter(getActivity(), this.articleList);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(articleRecyclerViewAdapter);
        setUpClick();
    }

    @Override
    public void showArticlesError(String errMsg) {
        layoutProgress.setVisibility(View.GONE);
        article_error_msg.setText(errMsg);
    }

    void setUpClick() {
        recyclerView.addOnItemTouchListener(
            new RecyclerItemClickListener(getActivity(), (View v, int position) -> {
                ArticleDetailFragment articleDetailFragment= new ArticleDetailFragment();
                articleDetailFragment.setArguments(FragmentStackManager.putBundle(articleList.get(position), AppConstants.BUNDLE_ARTICLE_ITEM));
                MainActivity.fragmentStackManager.addFragment(articleDetailFragment,true, ArticleDetailFragment.class.getSimpleName());
            })
        );
    }
}
