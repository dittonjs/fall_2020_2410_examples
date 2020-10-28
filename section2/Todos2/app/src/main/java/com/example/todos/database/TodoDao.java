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
    @Query("SELECT * from todo")
    List<Todo> getTodos();

    @Query("SELECT * from todo WHERE id = :id LIMIT 1")
    Todo getTodo(int id);

    @Insert
    void createTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);
}
