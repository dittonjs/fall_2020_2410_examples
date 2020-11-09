package com.example.personalblog.presenters;

public class BlogPostPresenter {
    public interface MVPView extends BaseMVPView {

    }

    MVPView view;

    public BlogPostPresenter(MVPView view) {
        this.view = view;
    }
}
