package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class NewBlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void goBackToBlogPostsPage(BlogPost post);
    }

    MVPView view;
    AppDatabase database;

    public NewBlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void createNewBlogPost(String title, String description, String contents, String pictureUri) {
        new Thread(() -> {
            BlogPost post = new BlogPost();
            post.title = title;
            post.description = description;
            post.contents = contents;
            post.pictureUri = pictureUri;
            post.id = database.getBlogPostDao().insert(post);
            view.goBackToBlogPostsPage(post);
        }).start();
    }

    public void handleCancelPress() {
        view.goBackToBlogPostsPage(null);
    }
}
