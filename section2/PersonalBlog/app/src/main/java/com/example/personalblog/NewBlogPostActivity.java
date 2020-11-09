package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.view.GravityCompat;

import com.example.personalblog.components.MaterialInput;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostsPresenter;
import com.example.personalblog.presenters.NewBlogPostPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewBlogPostActivity extends BaseActivity implements NewBlogPostPresenter.MVPView{
    NewBlogPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewBlogPostPresenter(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        MaterialInput titleInput = new MaterialInput(this, "Title");
        MaterialInput descriptionInput = new MaterialInput(this, "Description");
        MaterialInput contentsInput = new MaterialInput(this, "Contents", true);

        MaterialButton saveButton = new MaterialButton(this, null, R.attr.materialButtonStyle);
        saveButton.setText("Save");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.weight = 1;
        saveButton.setLayoutParams(params);
        MaterialButton cancelButton = new MaterialButton(this, null, R.attr.borderlessButtonStyle);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener((view) -> {
            presenter.handleCancelPress();
        });

        LinearLayout buttons = new LinearLayout(this);
        buttons.setGravity(Gravity.RIGHT);
        buttons.setPadding(48, 0, 48, 0);
        buttons.addView(cancelButton);
        buttons.addView(saveButton);


        saveButton.setOnClickListener((view) -> {
            presenter.createNewBlogPost(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    contentsInput.getText().toString(),
                    ""
            );
        });

        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);
        mainLayout.addView(buttons);

        setContentView(mainLayout);
    }

    @Override
    public void goBackToBlogPostsPage(BlogPost post) {
        if (post == null) {
            setResult(Activity.RESULT_CANCELED, null);
        } else {
            Intent intent = new Intent();
            intent.putExtra("result", post);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }
}
