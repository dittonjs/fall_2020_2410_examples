package com.example.todos.components;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.todos.models.Todo;

public class TodoListItem extends LinearLayout {
    Todo todo;
    public interface OnTodoStatusChangedListener {
        public void onChange(boolean isComplete);
    }

    public TodoListItem(Context context, Todo todo, OnTodoStatusChangedListener onChangeListener) {
        super(context);
        this.todo = todo;
        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
        checkBox.setOnCheckedChangeListener((view, newValue) -> {
            onChangeListener.onChange(newValue);
        });
        checkBox.setChecked(todo.isComplete);

        AppCompatTextView todoContentsView = new AppCompatTextView(context);
        todoContentsView.setText(todo.contents);

        addView(checkBox);
        addView(todoContentsView);
    }
}
