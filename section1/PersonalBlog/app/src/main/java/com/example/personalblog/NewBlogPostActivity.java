package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.personalblog.components.ImageSelector;
import com.example.personalblog.components.MaterialInput;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.NewBlogPostPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewBlogPostActivity extends BaseActivity implements NewBlogPostPresenter.MVPView {
    NewBlogPostPresenter presenter;
    LinearLayout mainLayout;
    ImageSelector selector;
    private final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewBlogPostPresenter(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        selector = new ImageSelector(this);
        selector.setOnClickListener((view) -> {
            presenter.handleSelectPictureButtonPressed();
        });
        mainLayout.addView(selector);

        MaterialInput titleInput = new MaterialInput(this, "Title");
        MaterialInput descriptionInput = new MaterialInput(this, "Description");
        MaterialInput contentsInput = new MaterialInput(this, "Contents", true);


        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);

        MaterialButton saveButton = new MaterialButton(this);
        saveButton.setText("Save");
        saveButton.setOnClickListener((view) -> {
            presenter.insertBlogPost(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    contentsInput.getText().toString(),
                    selector.getImageUri()
            );
        });

        MaterialButton cancelButton = new MaterialButton(this, null, R.attr.borderlessButtonStyle);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener((view) -> {
            presenter.handleCancelPressed();
        });

        LinearLayout buttonsLayout = new LinearLayout(this);
        buttonsLayout.setGravity(Gravity.RIGHT);
        buttonsLayout.setPadding(48, 0, 48, 0);
        buttonsLayout.addView(cancelButton);
        buttonsLayout.addView(saveButton);

        mainLayout.addView(buttonsLayout);

        setContentView(mainLayout);
    }

    @Override
    public void goBackToBlogPosts(BlogPost post) {
        if (post == null) {
            setResult(Activity.RESULT_CANCELED, null);
        } else {
            Intent intent = new Intent();
            intent.putExtra("result", post);
            setResult(Activity.RESULT_OK, intent);
        }
        finish();
    }

    @Override
    public void goToPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void displayPicture(String pictureUri) {
        selector.setImageUri(pictureUri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri pictureUri = data.getData();
            presenter.handlePictureSelected(pictureUri.toString());
        }
    }
}
