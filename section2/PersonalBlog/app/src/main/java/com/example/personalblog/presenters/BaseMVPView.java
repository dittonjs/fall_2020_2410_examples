package com.example.personalblog.presenters;

import com.example.personalblog.database.AppDatabase;

public interface BaseMVPView {
    AppDatabase getContextDatabase();
}
