package com.example.personalblog;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.personalblog.components.BlogPostCard;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostPresenter;
import com.example.personalblog.presenters.BlogPostsPresenter;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;
    LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this, getIntent().getLongExtra("id", -1));
        ScrollView scrollView = new ScrollView(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard blogPostCard = new BlogPostCard(this, post, true);
//            blogPostCard.setOnClickListener((view) -> {
//                presenter.handleBlogPostSelected(post.id);
//            });
            mainLayout.addView(blogPostCard);
        });
    }
}
