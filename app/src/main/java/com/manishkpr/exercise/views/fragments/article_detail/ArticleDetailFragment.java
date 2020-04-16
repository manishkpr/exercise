package com.manishkpr.exercise.views.fragments.article_detail;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.manishkpr.exercise.R;

import com.manishkpr.exercise.model.article.Article;
import com.manishkpr.exercise.util.AppConstants;
import com.manishkpr.exercise.views.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleDetailFragment extends BaseFragment {

    @BindView(R.id.test_text)
    TextView testText;

    Article article;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_test, null);
        setUpView(root);
        return root;
    }

    void setUpView(ViewGroup root) {
        ButterKnife.bind(this, root);
        getRequest();
    }

    void getRequest(){
        Bundle bundle = getArguments();
        if(bundle!=null) {
            article = bundle.getParcelable(AppConstants.BUNDLE_ARTICLE_ITEM);

            if(article!=null){
                testText.setText(article.shortDescription());
            }
        }
    }


}
