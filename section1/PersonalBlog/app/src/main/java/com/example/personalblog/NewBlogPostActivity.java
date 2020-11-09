package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.personalblog.components.MaterialInput;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.NewBlogPostPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewBlogPostActivity extends BaseActivity implements NewBlogPostPresenter.MVPView {
    NewBlogPostPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewBlogPostPresenter(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        MaterialInput titleInput = new MaterialInput(this, "Title");
        MaterialInput descriptionInput = new MaterialInput(this, "Description");
        MaterialInput contentsInput = new MaterialInput(this, "Contents", true);


        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);

        AppCompatButton saveButton = new AppCompatButton(this);
        saveButton.setText("Save");
        saveButton.setOnClickListener((view) -> {
            presenter.insertBlogPost(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    contentsInput.getText().toString(),
                    ""
            );
        });

        mainLayout.addView(saveButton);

        setContentView(mainLayout);
    }

    @Override
    public void goBackToBlogPosts(BlogPost post) {
        Intent intent = new Intent();
        intent.putExtra("result", post);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
