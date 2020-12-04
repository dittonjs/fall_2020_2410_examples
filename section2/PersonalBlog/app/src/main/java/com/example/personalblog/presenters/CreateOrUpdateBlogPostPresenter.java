package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class CreateOrUpdateBlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void goBackToBlogPostsPage(BlogPost post);
        void goToPhotos();
        void displayImage(String imageUri);
        void renderPost(BlogPost post);
        void displayTitleError();
        void goToCamera();
    }

    MVPView view;
    AppDatabase database;
    BlogPost post;
    public CreateOrUpdateBlogPostPresenter(MVPView view, long id) {
        this.view = view;
        database = view.getContextDatabase();
        if (id != -1) {
            new Thread(() -> {
                post = database.getBlogPostDao().findById(id);
                view.renderPost(post);
            }).start();
        }
    }

    public void saveBlogPost(String title, String description, String contents, String pictureUri) {
        if (title.length() == 0) {
            // dont save
            view.displayTitleError();
            return;
        }
        new Thread(() -> {
            if (post == null) {
                BlogPost post = new BlogPost();
                post.title = title;
                post.description = description;
                post.contents = contents;
                post.pictureUri = pictureUri;
                post.id = database.getBlogPostDao().insert(post);
                view.goBackToBlogPostsPage(post);
            } else {
                // update the existing post
                post.title = title;
                post.description = description;
                post.contents = contents;
                post.pictureUri = pictureUri;
                database.getBlogPostDao().update(post);
                view.goBackToBlogPostsPage(post);
            }
            
        }).start();
    }

    public void handleCancelPress() {
        view.goBackToBlogPostsPage(null);
    }

    public void handleSelectImagePress() {
        view.goToPhotos();
    }

    public void handleImageSelected(String imageUri) {
        view.displayImage(imageUri);
    }

    public void handleTakePicturePress() {
        view.goToCamera();
    }
}

