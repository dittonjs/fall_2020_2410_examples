package com.example.todos.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todos.models.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
    List<Todo> getAll();

    @Query("SELECT * FROM todo WHERE id = :id LIMIT 1")
    Todo findById(int id);

    @Insert
    long create(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);
}
