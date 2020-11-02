package com.example.todos.presenters;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

public class NewTodoPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void goBackToTodosPage(Todo newTodo);
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
            newTodo.id = (int)database.getTodoDao().createTodo(newTodo);
            view.goBackToTodosPage(newTodo);
        }).start();
    }
}
