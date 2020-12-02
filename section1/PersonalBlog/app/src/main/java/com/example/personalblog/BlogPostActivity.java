package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;

import com.example.personalblog.components.BlogPostCard;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;
    LinearLayout mainLayout;
    private final int UPDATE_BLOG_POST = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this, getIntent().getIntExtra("id", -1));
        ScrollView scrollView = new ScrollView(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }

    @Override
    public void displayPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard card = new BlogPostCard(this, post, true);
            card.setFabOnClickListener((view) -> {
                PopupMenu popupMenu = new PopupMenu(this, view);
                popupMenu.getMenu().add("Edit");
                popupMenu.getMenu().add("Delete");
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener((menuItem) -> {
                    if (menuItem.getTitle().toString().equals("Edit")) {
                        // handle edit
                        presenter.handleEditClick();
                    } else {
                        // handle delete
                        presenter.handleDeleteClick();
                    }
                    return true;
                });
            });
            mainLayout.addView(card);
        });

    }

    @Override
    public void updatePostUI(BlogPost post) {
        BlogPostCard card = mainLayout.findViewWithTag(post.id);
        card.setBlogPost(post);
    }

    @Override
    public void goBackToPostsPage(BlogPost post, boolean isDeleted, boolean isUpdate) {
        // go back to previous page
        if (isDeleted) {
            Intent intent = new Intent();
            intent.putExtra("post", post);
            setResult(BlogPostsActivity.POST_DELETED, intent);
        } else if (isUpdate) {
            Intent intent = new Intent();
            intent.putExtra("post", post);
            setResult(BlogPostsActivity.POST_UPDATED, intent);
        }
        finish();
    }

    @Override
    public void displayDeleteConfirmation() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Are you sure you want to delete this post?")
                .setPositiveButton("Delete", (view, i) -> {
                    presenter.deletePost();
                })
                .setNeutralButton("Cancel", (view, i) -> {
                    view.dismiss();
                })
                .show();
    }

    @Override
    public void goToUpdatePage(BlogPost post) {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        intent.putExtra("id", post.id);
        startActivityForResult(intent, UPDATE_BLOG_POST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_BLOG_POST && resultCode == Activity.RESULT_OK) {
            // we updated the post and need to refresh the ui
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handleBlogPostUpdated(post);
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        presenter.handleBackPressed();
    }
}
