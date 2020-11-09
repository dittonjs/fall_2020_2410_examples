package com.example.personalblog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostsPresenter;

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView {
    BlogPostsPresenter presenter;
    LinearLayout mainLayout;
    private final int CREATE_NEW_POST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostsPresenter(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        AppCompatButton newBlogPostButton = new AppCompatButton(this);
        newBlogPostButton.setText("New Blog Post");
        newBlogPostButton.setOnClickListener((view) -> {
            presenter.handleNewBlogPostPress();
        });

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
            mainLayout.addView(textView);
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