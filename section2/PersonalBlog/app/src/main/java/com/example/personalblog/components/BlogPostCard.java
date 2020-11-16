package com.example.personalblog.components;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.personalblog.models.BlogPost;
import com.google.android.material.card.MaterialCardView;

public class BlogPostCard extends MaterialCardView {
    public BlogPostCard(Context context, BlogPost post) {
        super(context);
        LinearLayout mainLayout = new LinearLayout(context);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout header = new LinearLayout(context);
        header.setOrientation(LinearLayout.VERTICAL);
        LinearLayout body = new LinearLayout(context);
        body.setOrientation(LinearLayout.VERTICAL);
        LinearLayout footer = new LinearLayout(context);

        mainLayout.addView(header);
        mainLayout.addView(body);
        mainLayout.addView(footer);

        addView(mainLayout);

        // build the UI



    }
}
