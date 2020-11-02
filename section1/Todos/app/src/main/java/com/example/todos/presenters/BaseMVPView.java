package com.example.todos.presenters;

import com.example.todos.database.AppDatabase;

public interface BaseMVPView {
    public AppDatabase getContextDatabase();
}
