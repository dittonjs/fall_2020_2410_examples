package com.example.todos.presenters;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.todos.TodosActivity;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

import java.util.ArrayList;

public class TodosPresenter {
    MVPView view;
    AppDatabase database;
    ArrayList<Todo> todos = new ArrayList<>();

    public interface MVPView extends BaseMVPView{
        public void renderTodos(ArrayList<Todo>  todos);
        public void goToNewTodoPage();
    }

    public TodosPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        loadTodos();
    }

    public void handleNewTodoClick() {
        new Thread(() -> {
            // save a bunch of stuff to the db
            view.goToNewTodoPage();
        }).start();
    }

    public void loadTodos() {
        new Thread(() -> {
            todos = (ArrayList<Todo>) database.getTodoDao().getTodos();
            view.renderTodos(todos);
        }).start();
    }

    public void updateTodo(Todo todo, boolean isComplete) {
        new Thread(() -> {
            todo.isComplete = isComplete;
            database.getTodoDao().updateTodo(todo);
        }).start();
    }
}
