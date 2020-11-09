package com.example.personalblog.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalblog.models.BlogPost;

import java.util.List;

@Dao
public interface BlogPostDao {

    @Query("SELECT * from blogpost")
    List<BlogPost> getAll();

    @Query("SELECT * from blogpost WHERE id = :id LIMIT 1")
    BlogPost findById(int id);

    @Insert
    long insert(BlogPost post);

    @Update
    void update(BlogPost post);

    @Delete
    void delete(BlogPost post);
}
