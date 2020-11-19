package com.example.personalblog.presenters;

import com.example.personalblog.BlogPostsActivity;
import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

import java.util.ArrayList;

public class BlogPostsPresenter {
    ArrayList<BlogPost> posts = new ArrayList<>();

    public interface MVPView extends BaseMVPView {
        void goToNewBlogPostPage();
        void renderBlogPost(BlogPost post);
        void goToBlogPostPage(long id);
    }

    MVPView view;
    AppDatabase database;

    public BlogPostsPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        new Thread(() -> {
            posts = (ArrayList<BlogPost>) database.getBlogPostDao().getAll();
            posts.forEach(post -> {
                view.renderBlogPost(post);
            });
        }).start();
    }

    public void handleNewBlogPostPress() {
        view.goToNewBlogPostPage();
    }

    public void handleNewBlogPostCreated(BlogPost post) {
        posts.add(post);
        view.renderBlogPost(post);
    };

    public void handleBlogPostSelected(long id) {
        view.goToBlogPostPage(id);
    }
}
