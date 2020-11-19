package com.example.personalblog.components;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.example.personalblog.R;
import com.example.personalblog.models.BlogPost;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class BlogPostCard extends MaterialCardView {
    MaterialButton readButton;

    public BlogPostCard(Context context, BlogPost post) {
        this(context, post, false);
    }

    public BlogPostCard(Context context, BlogPost post, boolean showFullPost) {
        super(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24,48,24);
        setLayoutParams(params);

        LinearLayout mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout header = new LinearLayout(context);
        header.setOrientation(LinearLayout.VERTICAL);
        LinearLayout body = new LinearLayout(context);
        body.setPadding(72, 32, 72, 0);
        body.setOrientation(LinearLayout.VERTICAL);
        LinearLayout footer = new LinearLayout(context);

        mainLayout.addView(header);
        mainLayout.addView(body);
        mainLayout.addView(footer);

        addView(mainLayout);

        //Header

        if (post.pictureUri.equals("") && showFullPost) {
            AppCompatImageView imageView = new AppCompatImageView(context);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setLayoutParams(imageParams);
            header.addView(imageView);
            imageView.setImageResource(R.drawable.ic_baseline_photo_size_select_actual_240);
            header.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground, null));
        } else if (!post.pictureUri.equals("")) {
            AppCompatImageView imageView = new AppCompatImageView(context);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setLayoutParams(imageParams);
            header.addView(imageView);
            imageView.setImageURI(Uri.parse(post.pictureUri));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        //Body
        MaterialTextView titleView = new MaterialTextView(context, null, R.attr.textAppearanceHeadline6);
        titleView.setText(post.title);
        body.addView(titleView);

        MaterialTextView descriptionView = new MaterialTextView(context);
        descriptionView.setText(post.description);
        descriptionView.setTextSize(18);
        body.addView(descriptionView);

        MaterialTextView contentsView = new MaterialTextView(context);
        contentsView.setText(post.contents);
        LinearLayout.LayoutParams contentsParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentsParams.setMargins(0, 12, 0, 0);
        contentsView.setLayoutParams(contentsParams);

        if (showFullPost) {
            contentsView.setTextSize(18);
        } else {
            contentsView.setMaxLines(3);
            contentsView.setEllipsize(TextUtils.TruncateAt.END);
        }
        body.addView(contentsView);

        // Footer
        if (showFullPost) {
            footer.setPadding(0, 32, 0, 0);
        } else {
            footer.setPadding(8, 0, 8, 0);
            readButton = new MaterialButton(context, null, R.attr.borderlessButtonStyle);
            readButton.setText( "Read");
//        footer.setGravity(Gravity.RIGHT);
            footer.addView(readButton);
        }



    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        readButton.setOnClickListener(l);
    }
}
