package com.example.todos.presenters;

import android.widget.BaseExpandableListAdapter;

import com.example.todos.DistanceCalculator;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

public class NewTodoPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void goBackToTodosPage();
    }

    public NewTodoPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void saveTodo(String contents) {
        
        new Thread(() -> {
            Todo newTodo = new Todo();
            newTodo.isComplete = false;
            newTodo.contents = contents;
            database.getTodoDao().createTodo(newTodo);
            view.goBackToTodosPage();
        }).start();
    }
}
