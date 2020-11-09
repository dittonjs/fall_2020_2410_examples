package com.example.personalblog;

import android.os.Bundle;

import com.example.personalblog.presenters.BlogPostPresenter;
import com.example.personalblog.presenters.BlogPostsPresenter;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this);
    }
}
