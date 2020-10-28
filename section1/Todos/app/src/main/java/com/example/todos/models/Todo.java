package com.example.todos.models;

// Table = Todos
// ===================================================
// id       |     contents     |      is_complete      |
//=====================================================
// 1        | Go pickup grandma      |         false        |
// 1        | Go pickup grandma      |         false        |

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;
}
