package com.example.todos.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todos.models.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao getTodoDao();
}
