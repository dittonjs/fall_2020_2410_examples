package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;

public class BlogPostPresenter {
    public interface MVPView extends BaseMVPView {

    }

    MVPView view;
    AppDatabase database;

    public BlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }
}
