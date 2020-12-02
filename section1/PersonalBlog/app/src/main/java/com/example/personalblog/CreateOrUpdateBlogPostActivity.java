package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.example.personalblog.components.ImageSelector;
import com.example.personalblog.components.MaterialInput;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.CreateOrUpdateBlogPostPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrUpdateBlogPostActivity extends BaseActivity implements CreateOrUpdateBlogPostPresenter.MVPView {
    CreateOrUpdateBlogPostPresenter presenter;
    LinearLayout mainLayout;
    ImageSelector selector;
    MaterialInput titleInput;
    MaterialInput descriptionInput;
    MaterialInput contentsInput;
    private final int PICK_IMAGE = 1;
    private final int TAKE_PICTURE = 2;

    String currentFilePath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id", CreateOrUpdateBlogPostPresenter.DEFAULT_POST_ID);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        selector = new ImageSelector(this);
        selector.setOnClickListener((view) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Choose Image")
                    .setItems(new CharSequence[]{"From Camera", "From Photos"}, (menuItem, i) -> {
                        if (i == 0) {
                            presenter.handleTakePicturePressed();
                        } else {
                            presenter.handleSelectPictureButtonPressed();
                        }
                    }).show();
        });
        mainLayout.addView(selector);

        titleInput = new MaterialInput(this, "Title");
        descriptionInput = new MaterialInput(this, "Description");
        contentsInput = new MaterialInput(this, "Contents", true);

        presenter = new CreateOrUpdateBlogPostPresenter(this, id);

        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);

        MaterialButton saveButton = new MaterialButton(this);
        saveButton.setText("Save");
        saveButton.setOnClickListener((view) -> {
            titleInput.setErrorEnabled(false);
            descriptionInput.setErrorEnabled(false);

            presenter.saveBlogPost(
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

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(mainLayout);

        setContentView(scrollView);
    }

    @Override
    public void goBack(BlogPost post) {
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
    public void populatePostForm(BlogPost post) {
        runOnUiThread(() -> {
            titleInput.setText(post.title);
            descriptionInput.setText(post.description);
            contentsInput.setText(post.contents);
            selector.setImageUri(post.pictureUri);
        });
    }

    @Override
    public void displayTitleError() {
        titleInput.setErrorEnabled(true);
        titleInput.setError("Title cannot be blank");
        Snackbar.make(mainLayout, "Title cannot be blank", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void displayDescriptionError() {
        descriptionInput.setErrorEnabled(true);
        descriptionInput.setError("Description cannot be blank");
        Snackbar.make(mainLayout, "Description cannot be blank", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void goToCamera() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "JPEG_" + timeStamp + ".jpg";

        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        currentFilePath = imageFile.getAbsolutePath();

        Uri imageUri = FileProvider.getUriForFile(
                this,
                "com.example.personalblog.provider",
                imageFile
        );

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri pictureUri = data.getData();
            presenter.handlePictureSelected(pictureUri.toString());
        }
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            presenter.handlePictureSelected(currentFilePath);
        }
    }
}
