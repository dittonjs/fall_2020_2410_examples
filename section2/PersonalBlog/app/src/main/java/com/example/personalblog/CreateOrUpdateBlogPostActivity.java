package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
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

public class CreateOrUpdateBlogPostActivity extends BaseActivity implements CreateOrUpdateBlogPostPresenter.MVPView{
    CreateOrUpdateBlogPostPresenter presenter;
    LinearLayout mainLayout;
    ImageSelector imageSelector;
    MaterialInput titleInput;
    MaterialInput descriptionInput;
    MaterialInput contentsInput;
    private final int SELECT_IMAGE = 1;
    private final int TAKE_PICTURE = 2;

    private String currentFilePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CreateOrUpdateBlogPostPresenter(this, getIntent().getLongExtra("id", -1));
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        imageSelector = new ImageSelector(this);
        imageSelector.setOnClickListener((view) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Choose Image")
                    .setItems(new CharSequence[]{"From Camera", "From Photos"}, (menuItem, i) -> {
                        if (i == 0) {
                            presenter.handleTakePicturePress();
                        } else {
                            presenter.handleSelectImagePress();
                        }
                    }).show();
        });
        mainLayout.addView(imageSelector);

        titleInput = new MaterialInput(this, "Title");
        descriptionInput = new MaterialInput(this, "Description");
        contentsInput = new MaterialInput(this, "Contents", true);

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
            titleInput.setErrorEnabled(false);
            presenter.saveBlogPost(
                    titleInput.getText().toString(),
                    descriptionInput.getText().toString(),
                    contentsInput.getText().toString(),
                    imageSelector.getImageUri()
            );
        });

        mainLayout.addView(titleInput);
        mainLayout.addView(descriptionInput);
        mainLayout.addView(contentsInput);

        mainLayout.addView(buttons);
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(mainLayout);

        setContentView(scrollView);
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

    @Override
    public void goToPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE);
    }

    @Override
    public void displayImage(String imageUri) {
        imageSelector.setImageUri(imageUri);
    }

    @Override
    public void renderPost(BlogPost post) {
        runOnUiThread(() -> {
            titleInput.setText(post.title);
            descriptionInput.setText(post.description);
            contentsInput.setText(post.contents);
            imageSelector.setImageUri(post.pictureUri);
        });
    }

    @Override
    public void displayTitleError() {
        Snackbar.make(mainLayout, "Title cannot be blank.", Snackbar.LENGTH_SHORT).show();
        titleInput.setErrorEnabled(true);
        titleInput.setError("Title cannot be blank.");
    }

    @Override
    public void goToCamera() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "JPEG_" + timeStamp + ".jpg";

        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        currentFilePath = imageFile.getAbsolutePath();

        Uri imageUri = FileProvider.getUriForFile(this, "com.example.personalblog.provider", imageFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            presenter.handleImageSelected(imageUri.toString());
        }
        if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_CANCELED) {
            presenter.handleImageSelected("");
        }
        if (requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
            presenter.handleImageSelected(currentFilePath);
        }
    }
}
