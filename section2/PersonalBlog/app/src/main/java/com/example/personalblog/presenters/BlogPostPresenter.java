package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class BlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void renderBlogPost(BlogPost post);
    }

    MVPView view;
    AppDatabase database;
    BlogPost post;

    public BlogPostPresenter(MVPView view, long id) {
        this.view = view;
        database = view.getContextDatabase();
        new Thread(() -> {
            post = database.getBlogPostDao().findById(id);
            view.renderBlogPost(post);
        }).start();
    }
}
