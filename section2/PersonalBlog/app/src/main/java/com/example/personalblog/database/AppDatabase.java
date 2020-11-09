package com.example.personalblog.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.personalblog.models.BlogPost;

@Database(entities = {BlogPost.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BlogPostDao getBlogPostDao();
}
