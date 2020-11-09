package com.example.todos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.todos.components.TodoListItem;
import com.example.todos.database.AppDatabase;
import com.example.todos.database.TodoDao;
import com.example.todos.models.Todo;
import com.example.todos.presenters.TodosPresenter;

import java.util.ArrayList;

public class TodosActivity extends BaseActivity implements TodosPresenter.MVPView{
    LinearLayout mainLayout;
    LinearLayout todosLayout;
    TodosPresenter presenter;
    private final int CREATE_NEW_TODO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TodosPresenter(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        todosLayout = new LinearLayout(this);
        todosLayout.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(todosLayout);
        AppCompatButton newTodoButton = new AppCompatButton(this);
        newTodoButton.setText("Create new todo");
        newTodoButton.setOnClickListener(view -> {
            presenter.handleNewTodoClick();
        });
        mainLayout.addView(newTodoButton);
        mainLayout.addView(scrollView);

        setContentView(mainLayout);
    }

    @Override
    public void renderTodo(Todo todo) {
        // wait for animation to finish, then display the todos
        runOnUiThread(() -> {
            TodoListItem listItem = new TodoListItem(
                    this,
                    todo,
                    isComplete -> {
                        presenter.updateTodo(todo, isComplete);
                    });
            todosLayout.addView(listItem);
        });
    }


    @Override
    public void goToNewTodoPage() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivityForResult(intent, CREATE_NEW_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_TODO && resultCode == Activity.RESULT_OK) {
            Todo newTodo = (Todo)data.getSerializableExtra("result");
            presenter.onNewTodoCreated(newTodo);
        }
    }
}