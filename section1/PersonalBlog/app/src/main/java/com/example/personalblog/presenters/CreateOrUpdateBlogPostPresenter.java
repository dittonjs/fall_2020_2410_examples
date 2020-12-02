package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class CreateOrUpdateBlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void goBack(BlogPost post);
        void goToPhotos();
        void displayPicture(String pictureUri);
        void populatePostForm(BlogPost post);
        void displayTitleError();
        void displayDescriptionError();
        void goToCamera();
    }

    public static final int DEFAULT_POST_ID = -1;

    MVPView view;
    AppDatabase database;
    BlogPost post;
    public CreateOrUpdateBlogPostPresenter(MVPView view, int id) {
        this.view = view;
        database = view.getContextDatabase();
        if (id != DEFAULT_POST_ID) {
            // load the post from the database
            new Thread(() -> {
                post = database.getBlogPostDao().findById(id);
                view.populatePostForm(post);
            }).start();
        }
    }

    public void saveBlogPost(String title, String description, String contents, String pictureUri) {
        // insertion into the database
        boolean hasError = false;
        if (title.length() == 0) {
            view.displayTitleError();
            hasError = true;
        }
        if (description.length() == 0) {
            view.displayDescriptionError();
            hasError = true;
        }
        if (hasError) {
            return;
        }

        new Thread(() -> {
            if (post == null) {
                // we are inserting a post
                BlogPost newPost = new BlogPost();
                newPost.title = title;
                newPost.description = description;
                newPost.contents = contents;
                newPost.pictureUri = pictureUri;
                newPost.id = (int)database.getBlogPostDao().insert(newPost);
                view.goBack(newPost);
            } else {
                // update existing post
                post.title = title;
                post.description = description;
                post.contents = contents;
                post.pictureUri = pictureUri;
                database.getBlogPostDao().update(post);
                view.goBack(post);
            }

        }).start();
    }

    public void handleSelectPictureButtonPressed() {
        view.goToPhotos();
    }

    public void handleTakePicturePressed() {
        view.goToCamera();
    }

    public void handlePictureSelected(String pictureUri) {
        view.displayPicture(pictureUri);
    }

    public void handleCancelPressed() {
        view.goBack(null);
    }
}
