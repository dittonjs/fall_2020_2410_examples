package com.example.todos.presenters;

import androidx.room.Room;

import com.example.todos.NewTodoActivity;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

import java.util.ArrayList;

public class NewTodoPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView {
        public void goBackToTodosPage();
        public AppDatabase getContextDatabase();
    }

    public NewTodoPresenter(NewTodoActivity view) {
        this.view = view;
        this.database = view.getContextDatabase();
    }

    public void saveTodo(String contents) {
        // TODO: check the contents to make sure they are not empty
        new Thread(() -> {
            Todo todo = new Todo();
            todo.contents = contents;
            todo.isComplete = false;
            database.getTodoDao().create(todo);
            view.goBackToTodosPage();
        }).start();
    }
}
