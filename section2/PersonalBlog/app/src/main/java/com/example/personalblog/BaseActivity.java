package com.example.personalblog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.personalblog.database.AppDatabase;
import com.example.personalblog.presenters.BaseMVPView;

public class BaseActivity extends AppCompatActivity implements BaseMVPView {

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "blogposts").build();
    }
}
