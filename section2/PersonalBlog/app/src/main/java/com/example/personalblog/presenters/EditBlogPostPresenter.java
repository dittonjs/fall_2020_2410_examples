package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;

public class EditBlogPostPresenter {
    public interface MVPView extends BaseMVPView {

    }

    MVPView view;
    AppDatabase database;

    public EditBlogPostPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }
}
