package com.example.todos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.todos.components.TodoListItem;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;
import com.example.todos.presenters.TodosPresenter;

import java.util.ArrayList;

public class TodosActivity extends BaseActivity implements TodosPresenter.MVPView{
    LinearLayout mainLayout;
    LinearLayout todosLayout;
    TodosPresenter presenter;

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
    protected void onResume() {
        super.onResume();
        presenter.loadTodos();
    }

    @Override
    public void renderTodos(ArrayList<Todo> todos) {
        // wait for animation to finish, then display the todos
        runOnUiThread(() -> {
            todosLayout.removeAllViews();
            todos.forEach(todo -> {
                TodoListItem listItem = new TodoListItem(
                        this,
                        todo,
                        isComplete -> {
                            presenter.updateTodo(todo, isComplete);
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
}