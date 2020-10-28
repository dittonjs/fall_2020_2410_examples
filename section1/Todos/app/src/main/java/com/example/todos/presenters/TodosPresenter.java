package com.example.todos.presenters;

import com.example.todos.TodosActivity;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

import java.util.ArrayList;

public class TodosPresenter {
    private MVPView view;
    private ArrayList<Todo> todos = new ArrayList<>();
    private AppDatabase database;
    public interface MVPView {
        public void renderTodos(ArrayList<Todo> todos);
        public void goToNewTodoPage();
        public AppDatabase getContextDatabase();
    }

    public TodosPresenter(MVPView view) {
        this.view = view;
        this.database = view.getContextDatabase();
        loadTodos();

        // load todos
    }

    public void loadTodos() {
        new Thread(() -> {
            // go to the database and load todos
            todos = (ArrayList<Todo>)database.getTodoDao().getAll();
            view.renderTodos(todos);
        }).start();
    }

    public void handleCreateNewTodoPress() {
        new Thread(() -> {
            // stop our long running tasks or wait for them to finish
            view.goToNewTodoPage();
        }).start();
    }

    public void updateTodo(Todo todo, boolean isComplete) {
        new Thread(() -> {
            todo.isComplete = isComplete;
            database.getTodoDao().update(todo);
        }).start();
    }
}
