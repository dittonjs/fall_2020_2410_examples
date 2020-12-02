package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

public class BlogPostPresenter {
    public interface MVPView extends BaseMVPView {
        void renderBlogPost(BlogPost post);
        void displayDeleteConfirmation();
        void goBackToPostsPage(BlogPost post, boolean isDeleted, boolean isUpdated);
        void goToEditPage(BlogPost post);
        void updatePostUI(BlogPost post);
    }

    MVPView view;
    AppDatabase database;
    BlogPost post;
    boolean didUpdate = false;

    public BlogPostPresenter(MVPView view, long id) {
        this.view = view;
        database = view.getContextDatabase();
        new Thread(() -> {
            post = database.getBlogPostDao().findById(id);
            view.renderBlogPost(post);
        }).start();
    }

    public void handleDeletePressed() {
        view.displayDeleteConfirmation();
    }

    public void handleEditPressed() {
        view.goToEditPage(post);
    }

    public void deletePost() {
        new Thread(() -> {
            database.getBlogPostDao().delete(post);
            view.goBackToPostsPage(post, true, false);
        }).start();
    }

    public void handlePostUpdated(BlogPost post) {
        this.post = post;
        didUpdate = true;
        view.updatePostUI(post);
    }

    public void handleBackPressed() {
        view.goBackToPostsPage(post, false, true);
    }
}
