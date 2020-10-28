package com.example.todos;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.room.Room;

import com.example.todos.database.AppDatabase;
import com.example.todos.presenters.NewTodoPresenter;
import com.example.todos.presenters.TodosPresenter;

public class NewTodoActivity extends BaseActivity implements NewTodoPresenter.MVPView {
    NewTodoPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewTodoPresenter(this);
        AppCompatEditText editText = new AppCompatEditText(this);
        AppCompatButton saveButton = new AppCompatButton(this);
        saveButton.setOnClickListener(view -> {
            presenter.saveTodo(editText.getText().toString());
        });
        saveButton.setText("Save");
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(editText);
        mainLayout.addView(saveButton);
        setContentView(mainLayout);
    }

    @Override
    public void goBackToTodosPage() {
        finish();
    }
}
