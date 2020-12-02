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

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView {
    BlogPostsPresenter presenter;
    LinearLayout postsLayout;
    // request code
    private final int CREATE_NEW_POST = 1;
    private final int MODIFY_POST = 2;

    // result codes
    public static final int POST_DELETED = 10;
    public static final int POST_UPDATED = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostsPresenter(this);
        postsLayout = new LinearLayout(this);
        postsLayout.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(postsLayout);

        FrameLayout mainLayout = new FrameLayout(this);
        mainLayout.addView(scrollView);
        FloatingActionButton newBlogPostButton = new FloatingActionButton(this);
        newBlogPostButton.setOnClickListener((view) -> {
            presenter.handleNewBlogPostPress();
        });
        newBlogPostButton.setImageResource(R.drawable.ic_baseline_add_24);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 48, 48);
        params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        
        newBlogPostButton.setLayoutParams(params);

        mainLayout.addView(newBlogPostButton);
        setContentView(mainLayout);
    }

    @Override
    public void goToNewBlogPostPage() {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        startActivityForResult(intent, CREATE_NEW_POST);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard blogPostCard = new BlogPostCard(this, post);
            blogPostCard.setOnClickListener((view) -> {
                presenter.handleBlogPostSelected(post.id);
            });
            postsLayout.addView(blogPostCard);
        });
    }

    @Override
    public void goToBlogPostPage(long id) {
        Intent intent = new Intent(this, BlogPostActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, MODIFY_POST);
    }

    @Override
    public void removePost(BlogPost post) {
        runOnUiThread(() -> {
            View view = postsLayout.findViewWithTag(post.id);
            postsLayout.removeView(view);
        });
    }

    @Override
    public void updatePostUI(BlogPost post) {
        BlogPostCard card = postsLayout.findViewWithTag(post.id);
        card.setBlogPost(post);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handleNewBlogPostCreated(post);
        }

        if (requestCode == MODIFY_POST && resultCode == POST_DELETED) {
            // do something to remove the post from the screen
            BlogPost post = (BlogPost) data.getSerializableExtra("post");
            presenter.handleBlogPostDeleted(post);
        }

        if (requestCode == MODIFY_POST && resultCode == POST_UPDATED) {
            // do something to update the post ui on the screen
            BlogPost post = (BlogPost) data.getSerializableExtra("post");
            presenter.handleBlogPostUpdated(post);
        }
    }
}