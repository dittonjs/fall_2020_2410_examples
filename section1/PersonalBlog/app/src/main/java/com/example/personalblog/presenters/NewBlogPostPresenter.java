package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class NewBlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void goBackToBlogPosts(BlogPost post);
        void goToPhotos();
        void displayPicture(String pictureUri);
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

    public void handleSelectPictureButtonPressed() {
        view.goToPhotos();
    }

    public void handlePictureSelected(String pictureUri) {
        view.displayPicture(pictureUri);
    }

    public void handleCancelPressed() {
        view.goBackToBlogPosts(null);
    }
}
