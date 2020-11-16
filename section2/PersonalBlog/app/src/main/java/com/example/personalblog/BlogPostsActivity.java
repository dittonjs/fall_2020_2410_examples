package com.example.personalblog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView {
    BlogPostsPresenter presenter;
    LinearLayout postsLayout;
    private final int CREATE_NEW_POST = 1;
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
        Intent intent = new Intent(this, NewBlogPostActivity.class);
        startActivityForResult(intent, CREATE_NEW_POST);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            AppCompatTextView textView = new AppCompatTextView(this);
            textView.setText(post.title);
            postsLayout.addView(textView);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handleNewBlogPostCreated(post);
        }
    }
}