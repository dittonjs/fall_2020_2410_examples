package com.example.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.todos.components.TodoListItem;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;
import com.example.todos.presenters.TodosPresenter;

import java.util.ArrayList;

public class TodosActivity extends AppCompatActivity implements TodosPresenter.MVPView {
    LinearLayout mainLayout;
    LinearLayout todosLayout;
    TodosPresenter todosPresenter;
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
    protected void onResume() {
        super.onResume();
        todosPresenter.loadTodos();
    }

    @Override
    public void renderTodos(ArrayList<Todo> todos) {
        runOnUiThread(() -> {
            todosLayout.removeAllViews();

            todos.forEach(todo -> {
                TodoListItem listItem = new TodoListItem(
                        this,
                        todo,
                        (newValue) -> {
                            todosPresenter.updateTodo(todo, newValue);
                        });
                todosLayout.addView(listItem);
            });
        });
    }

    @Override
    public void goToNewTodoPage() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivity(intent);
    }

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todos").build();
    }
}