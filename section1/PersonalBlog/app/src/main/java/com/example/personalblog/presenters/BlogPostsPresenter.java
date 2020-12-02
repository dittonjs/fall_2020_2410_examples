package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.models.BlogPost;

import java.util.ArrayList;

public class BlogPostsPresenter {
    ArrayList<BlogPost> posts = new ArrayList<>();
    public interface MVPView extends BaseMVPView {
        void goToNewBlogPostPage();
        void goToBlogPostPage(int id);
        void renderBlogPost(BlogPost post);
        void removeBlogPost(BlogPost post);
        void updatePostUI(BlogPost post);
    }

    MVPView view;
    AppDatabase database;

    public BlogPostsPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        new Thread(() -> {
            posts = (ArrayList<BlogPost>) database.getBlogPostDao().getAll();
            posts.forEach(post -> view.renderBlogPost(post));
        }).start();
    }

    public void handleNewBlogPostClick() {
        view.goToNewBlogPostPage();
    }

    public void handleBlogPostClick(int id) {
        view.goToBlogPostPage(id);
    }

    public void onNewBlogPostCreated(BlogPost post) {
        posts.add(post);
        view.renderBlogPost(post);
    }

    public void onBlogPostDeleted(BlogPost post) {
        for (int i = 0; i < posts.size(); i ++) {
            if (posts.get(i).id == post.id) {
                posts.remove(i);
                break;
            }
        }
        view.removeBlogPost(post);
    }

    public void onBlogPostUpdated(BlogPost post) {
        for (int i = 0; i < posts.size(); i ++) {
            if (posts.get(i).id == post.id) {
                posts.set(i, post);
                break;
            }
        }
        view.updatePostUI(post);
    }
}
