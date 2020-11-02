package com.example.todos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.todos.components.TodoListItem;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;
import com.example.todos.presenters.TodosPresenter;

import java.util.ArrayList;

public class TodosActivity extends BaseActivity implements TodosPresenter.MVPView {
    LinearLayout mainLayout;
    LinearLayout todosLayout;
    TodosPresenter todosPresenter;
    ArrayList<TodoListItem> todoListItems;
    private final int CREATE_NEW_TODO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todosPresenter = new TodosPresenter(this);

        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        todosLayout = new LinearLayout(this);
        todosLayout.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(todosLayout);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Create new todo");
        button.setOnClickListener(view -> {
            todosPresenter.handleCreateNewTodoPress();
        });

        mainLayout.addView(button);
        mainLayout.addView(scrollView);

        setContentView(mainLayout);
    }


    @Override
    public void renderTodo(Todo todo) {
        runOnUiThread(() -> {
            TodoListItem listItem = new TodoListItem(
                    this,
                    todo,
                    (newValue) -> {
                        todosPresenter.updateTodo(todo, newValue);
                    });
            todoListItems.add(listItem);
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
            todosPresenter.handleNewTodoCreated(newTodo);
        }

    }
}