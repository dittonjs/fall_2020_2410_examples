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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class BlogPostCard extends MaterialCardView {
    MaterialButton readButton;
    FloatingActionButton fab;
    BlogPost post;
    AppCompatImageView imageView;
    MaterialTextView titleView;
    MaterialTextView descriptionView;
    MaterialTextView contentsView;
    LinearLayout header;
    boolean showFullPost = false;
    public BlogPostCard(Context context, BlogPost post) {
        this(context, post, false);
    }

    public BlogPostCard(Context context, BlogPost post, boolean showFullPost) {
        super(context);
        setTag(post.id);
        this.post = post;
        this.showFullPost = showFullPost;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24,48,24);
        setLayoutParams(params);

        LinearLayout mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        header = new LinearLayout(context);
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

        imageView = new AppCompatImageView(context);
        setupImage();
        header.addView(imageView);

        //Body
        titleView = new MaterialTextView(context, null, R.attr.textAppearanceHeadline6);
        titleView.setText(post.title);
        body.addView(titleView);

        descriptionView = new MaterialTextView(context);
        descriptionView.setText(post.description);
        descriptionView.setTextSize(18);
        body.addView(descriptionView);

        contentsView = new MaterialTextView(context);
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

    private void setupImage() {
        if (post.pictureUri.equals("") && showFullPost) {
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setLayoutParams(imageParams);
            imageView.setImageResource(R.drawable.ic_baseline_photo_size_select_actual_240);
            header.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground, null));
        } else if (!post.pictureUri.equals("")) {
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 560);
            imageView.setLayoutParams(imageParams);
            imageView.setImageURI(Uri.parse(post.pictureUri));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    public void setBlogPost(BlogPost post) {
        this.post = post;
        setupImage();
        titleView.setText(post.title);
        descriptionView.setText(post.description);
        contentsView.setText(post.contents);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        readButton.setOnClickListener(l);
    }

    public void setFabOnClickListener(OnClickListener l) {
        fab.setOnClickListener(l);
    }
}
