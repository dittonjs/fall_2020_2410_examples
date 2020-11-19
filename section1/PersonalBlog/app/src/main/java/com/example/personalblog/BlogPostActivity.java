package com.example.personalblog;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.example.personalblog.components.BlogPostCard;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostPresenter;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this, getIntent().getIntExtra("id", -1));
        ScrollView scrollView = new ScrollView(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }

    @Override
    public void displayPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard card = new BlogPostCard(this, post, true);
            mainLayout.addView(card);
        });

    }
}
