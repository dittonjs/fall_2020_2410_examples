package com.example.personalblog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.example.personalblog.components.BlogPostCard;
import com.example.personalblog.models.BlogPost;
import com.example.personalblog.presenters.BlogPostPresenter;
import com.example.personalblog.presenters.BlogPostsPresenter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BlogPostActivity extends BaseActivity implements BlogPostPresenter.MVPView {
    BlogPostPresenter presenter;
    LinearLayout mainLayout;
    private final int UPDATE_POST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BlogPostPresenter(this, getIntent().getLongExtra("id", -1));
        ScrollView scrollView = new ScrollView(this);
        mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }

    @Override
    public void renderBlogPost(BlogPost post) {
        runOnUiThread(() -> {
            BlogPostCard blogPostCard = new BlogPostCard(this, post, true);
            blogPostCard.setFabOnClickListener((fab) -> {
                PopupMenu popupMenu = new PopupMenu(this, fab);
                popupMenu.getMenu().add("Edit");
                popupMenu.getMenu().add("Delete");
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    if (menuItem.getTitle().toString().equals("Edit")) {
                        // handle editing
                        presenter.handleEditPressed();
                    } else {
                        // handle deleting
                        presenter.handleDeletePressed();
                    }
                    return true;
                });
                popupMenu.show();
            });
//            blogPostCard.setOnClickListener((view) -> {
//                presenter.handleBlogPostSelected(post.id);
//            });
            mainLayout.addView(blogPostCard);
        });
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
    public void goBackToPostsPage(BlogPost post, boolean isDeleted, boolean isUpdated) {
        Intent intent = new Intent();
        intent.putExtra("post", post);
        if (isDeleted) {
            setResult(BlogPostsActivity.POST_DELETED ,intent);
        } else if (isUpdated) {
            setResult(BlogPostsActivity.POST_UPDATED, intent);
        }
        finish();
    }

    @Override
    public void goToEditPage(BlogPost post) {
        Intent intent = new Intent(this, CreateOrUpdateBlogPostActivity.class);
        intent.putExtra("id", post.id);
        startActivityForResult(intent, UPDATE_POST);
    }

    @Override
    public void updatePostUI(BlogPost post) {
        BlogPostCard card = mainLayout.findViewWithTag(post.id);
        card.setBlogPost(post);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_POST && resultCode == Activity.RESULT_OK) {
            // we know the post was updated and we need to update the ui
            BlogPost post = (BlogPost) data.getSerializableExtra("result");
            presenter.handlePostUpdated(post);
        }
    }

    @Override
    public void onBackPressed() {
        presenter.handleBackPressed();
    }
}
