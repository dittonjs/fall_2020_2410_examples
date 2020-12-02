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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class BlogPostCard extends MaterialCardView {
    private MaterialButton viewButton;
    private FloatingActionButton fab;
    private AppCompatImageView imageView;
    private MaterialTextView titleView;
    private MaterialTextView descriptionView;
    private MaterialTextView contentsView;
    private LinearLayout header;
    private BlogPost post;
    private boolean showFullPost = false;


    public BlogPostCard(Context context, BlogPost post) {
        this(context, post, false);
    }
    public BlogPostCard(Context context, BlogPost post, boolean showFullPost) {
        super(context);
        this.post = post;
        this.showFullPost = showFullPost;
        setTag(post.id);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24, 48, 24);
//        setElevation(24);
        setLayoutParams(params);
        LinearLayout mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        header = new LinearLayout(context);
        header.setOrientation(LinearLayout.VERTICAL);
        LinearLayout body = new LinearLayout(context);
        body.setPadding(64, 32, 64, 32);
        body.setOrientation(LinearLayout.VERTICAL);
        LinearLayout footer = new LinearLayout(context);

        mainLayout.addView(header);
        mainLayout.addView(body);
        mainLayout.addView(footer);

        addView(mainLayout);
        imageView = new AppCompatImageView(context);
        displayImage();
        header.addView(imageView);


        //Body

        titleView = new MaterialTextView(context, null, R.attr.textAppearanceHeadline6);
        titleView.setText(post.title);
        body.addView(titleView);

        descriptionView = new MaterialTextView(context);
        descriptionView.setText(post.description);
        body.addView(descriptionView);
        descriptionView.setTextSize(18);

        contentsView = new MaterialTextView(context);
        contentsView.setText(post.contents);
        body.addView(contentsView);
        if (showFullPost) {
            contentsView.setTextSize(18);
        } else {
            contentsView.setMaxLines(3);
            contentsView.setEllipsize(TextUtils.TruncateAt.END);
        }
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        contentParams.setMargins(0, 12, 0, 0);
        contentsView.setLayoutParams(contentParams);

        // footer
        if (!showFullPost) {
            viewButton = new MaterialButton(context, null, R.attr.borderlessButtonStyle);
            viewButton.setText("Read");
            footer.addView(viewButton);
//        footer.setGravity(Gravity.RIGHT);
        }

        // FAB

        if (showFullPost) {
            fab = new FloatingActionButton(context);
            fab.setImageResource(R.drawable.ic_baseline_edit_24);
            MaterialCardView.LayoutParams fabParams = new MaterialCardView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            fabParams.gravity = Gravity.RIGHT;
            fabParams.setMargins(0, 480, 48, 0);
            fab.setLayoutParams(fabParams);
            addView(fab);
        }
    }

    private void displayImage() {
        //Header
        if (post.pictureUri.equals("") && showFullPost) {
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setImageResource(R.drawable.ic_baseline_photo_size_select_actual_24);
            header.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground, null));
            imageView.setLayoutParams(imageParams);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else if(!post.pictureUri.equals("")) {
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setImageURI(Uri.parse(post.pictureUri));
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    public void setBlogPost(BlogPost post) {
        this.post = post;
        displayImage();
        titleView.setText(post.title);
        descriptionView.setText(post.description);
        contentsView.setText(post.contents);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        viewButton.setOnClickListener(l);
    }

    public void setFabOnClickListener(OnClickListener l) {
        if (fab != null) {
            fab.setOnClickListener(l);
        }
    }
}
