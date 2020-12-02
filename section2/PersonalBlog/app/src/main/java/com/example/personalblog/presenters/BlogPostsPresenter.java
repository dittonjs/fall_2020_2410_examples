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
        void removePost(BlogPost post);
        void updatePostUI(BlogPost post);
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

    public void handleBlogPostDeleted(BlogPost post) {
        for(int i =0; i < posts.size(); i++) {
            if (post.id == posts.get(i).id) {
                posts.remove(i);
                break;
            }
        }
        view.removePost(post);
    }

    public void handleBlogPostUpdated(BlogPost post) {
        for(int i =0; i < posts.size(); i++) {
            if (post.id == posts.get(i).id) {
                posts.set(i, post);
                break;
            }
        }

        view.updatePostUI(post);
    }
}
