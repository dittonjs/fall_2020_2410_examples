package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class NewBlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void goBackToBlogPosts(BlogPost post);
    }

    MVPView view;
    AppDatabase database;
    public NewBlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void insertBlogPost(String title, String description, String contents, String pictureUri) {
        // insertion into the database
        new Thread(() -> {
            BlogPost newPost = new BlogPost();
            newPost.title = title;
            newPost.description = description;
            newPost.contents = contents;
            newPost.pictureUri = pictureUri;
            newPost.id = (int)database.getBlogPostDao().insert(newPost);
            view.goBackToBlogPosts(newPost);
        }).start();
    }
}
