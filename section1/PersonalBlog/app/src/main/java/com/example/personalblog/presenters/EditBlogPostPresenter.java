package com.example.personalblog.presenters;

public class EditBlogPostPresenter {
    public interface MVPView extends BaseMVPView {

    }

    BlogPostPresenter.MVPView view;

    public EditBlogPostPresenter(BlogPostPresenter.MVPView view) {
        this.view = view;
    }
}
