package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;
import com.google.android.material.slider.BasicLabelFormatter;

public class BlogPostPresenter {
    BlogPost post;
    boolean didUpdate = false;

    public interface MVPView extends BaseMVPView {
        void displayPost(BlogPost post);
        void updatePostUI(BlogPost post);
        void goBackToPostsPage(BlogPost post, boolean isDeleted, boolean isUpdate);
        void displayDeleteConfirmation();
        void goToUpdatePage(BlogPost post);
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

    public void deletePost() {
        new Thread(() -> {
            database.getBlogPostDao().delete(post);
            view.goBackToPostsPage(post, true, false);
        }).start();
    }

    public void handleDeleteClick() {
        view.displayDeleteConfirmation();
    }

    public void handleEditClick() {
        view.goToUpdatePage(post);
    }

    public void handleBlogPostUpdated(BlogPost post) {
        this.post = post;
        didUpdate = true;
        view.updatePostUI(post);
    }

    public void handleBackPressed() {
        view.goBackToPostsPage(post, false, didUpdate);
    }
}

