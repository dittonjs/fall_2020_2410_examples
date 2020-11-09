package com.example.personalblog;

import android.os.Bundle;

import com.example.personalblog.presenters.BlogPostsPresenter;
import com.example.personalblog.presenters.EditBlogPostPresenter;

public class EditBlogPostActivity extends BaseActivity implements EditBlogPostPresenter.MVPView {
    EditBlogPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditBlogPostPresenter(this);
    }
}
