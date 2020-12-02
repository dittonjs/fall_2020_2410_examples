package com.example.personalblog;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.personalblog.components.BlogPostCard;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView{
    LinearLayout blogPostsLayout;
    BlogPostsPresenter presenter;
    // request codes
    private final int MODIFY_POST = 0;
    private final int CREATE_NEW_POST = 1;
    // results codes
    public final static int POST_DELETED = 2;
    public final static int POST_UPDATED = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostsPresenter(this);

        FrameLayout mainLayout = new FrameLayout(this);

        ScrollView scrollView = new ScrollView(this);

        blogPostsLayout = new LinearLayout(this);
        blogPostsLayout.setOrientation(LinearLayout.VERTICAL);

        FloatingActionButton button = new FloatingActionButton(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 48, 48);
        params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        button.setLayoutParams(params);
        button.setImageResource(R.drawable.ic_baseline_add_24);

        button.setOnClickListener((view) -> {
            presenter.handleNewBlogPostClick();
        });

        mainLayout.addView(scrollView);
        mainLayout.addView(button);
        scrollView.addView(blogPostsLayout);

        setContentView(mainLayout);
    }

    @Override
    public void goToNewBlogPostPage() {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        startActivityForResult(intent, CREATE_NEW_POST);
    }

    @Override
    public void goToBlogPostPage(int id) {
        Intent intent = new Intent(this, BlogPostActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, MODIFY_POST);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard card = new BlogPostCard(this, post);
            card.setOnClickListener((view) -> {
                // go the the page to view the post
                presenter.handleBlogPostClick(post.id);
            });
            blogPostsLayout.addView(card);

        });
    }

    @Override
    public void removeBlogPost(BlogPost post) {
        View view = blogPostsLayout.findViewWithTag(post.id);
        blogPostsLayout.removeView(view);
    }

    @Override
    public void updatePostUI(BlogPost post) {
        BlogPostCard card = blogPostsLayout.findViewWithTag(post.id);
        card.setBlogPost(post);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.onNewBlogPostCreated(post);
        }

        if (requestCode == MODIFY_POST && resultCode == POST_DELETED) {
            //update the UI
            BlogPost deletedPost = (BlogPost)data.getSerializableExtra("post");
            presenter.onBlogPostDeleted(deletedPost);
        }

        if (requestCode == MODIFY_POST && resultCode == POST_UPDATED) {
            //update the UI
            BlogPost updatedPost = (BlogPost)data.getSerializableExtra("post");
            presenter.onBlogPostUpdated(updatedPost);
//            presenter.onBlogPostDeleted(deletedPost);
        }
    }
}