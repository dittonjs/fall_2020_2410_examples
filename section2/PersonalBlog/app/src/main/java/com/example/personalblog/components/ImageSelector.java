package com.example.personalblog.components;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.personalblog.R;

public class ImageSelector extends FrameLayout {
    String imageUri;
    AppCompatImageView imageView;

    public ImageSelector(Context context) {
        this(context, "");
    }

    public ImageSelector(Context context, String imageUri) {
        super(context);
        this.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground, null));
        this.imageUri = imageUri;
        imageView = new AppCompatImageView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 480);
        imageView.setLayoutParams(params);

        addView(imageView);
        displayImage();
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
        displayImage();
    }

    private void displayImage() {
        if (imageUri.equals("")) {
            imageView.setImageResource(R.drawable.ic_baseline_add_a_photo_240);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            // display default image
        } else {
            imageView.setImageURI(Uri.parse(imageUri));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // display the imageUri
        }
    }
}
