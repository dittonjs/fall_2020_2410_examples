package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;
import com.google.android.material.slider.BasicLabelFormatter;

public class BlogPostPresenter {
    BlogPost post;

    public interface MVPView extends BaseMVPView {
        void displayPost(BlogPost post);
    }

    MVPView view;
    AppDatabase database;
    public BlogPostPresenter(MVPView view, int postId) {
        this.view = view;
        database = view.getContextDatabase();
        // load the post and display
        new Thread(() -> {
            post = database.getBlogPostDao().findById(postId);
            view.displayPost(post);
        }).start();
    }
}
