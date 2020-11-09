package com.example.personalblog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostsPresenter;

public class BlogPostsActivity extends BaseActivity implements BlogPostsPresenter.MVPView{
    LinearLayout blogPostsLayout;
    BlogPostsPresenter presenter;
    private final int CREATE_NEW_POST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostsPresenter(this);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);

        blogPostsLayout = new LinearLayout(this);
        blogPostsLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("New Blog Post");

        button.setOnClickListener((view) -> {
            presenter.handleNewBlogPostClick();
        });

        mainLayout.addView(button);
        mainLayout.addView(scrollView);
        scrollView.addView(blogPostsLayout);

        setContentView(mainLayout);
    }

    @Override
    public void goToNewBlogPostPage() {
        Intent intent = new Intent(this, NewBlogPostActivity.class);
        startActivityForResult(intent, CREATE_NEW_POST);
    }

    @Override
    public void goToBlogPostPage(int id) {

    }

    @Override
    public void renderBlogPost(BlogPost post) {
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText(post.title);
        blogPostsLayout.addView(textView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_POST && resultCode == Activity.RESULT_OK) {
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.onNewBlogPostCreated(post);
        }
    }
}