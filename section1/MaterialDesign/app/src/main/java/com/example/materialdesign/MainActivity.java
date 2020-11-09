package com.example.materialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.WrapperListAdapter;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FrameLayout mainLayout = new FrameLayout(this);
//
//
////        AppCompatButton button = new AppCompatButton(this);
////        button.setText("I am a regular button");
//
//        MaterialButton materialButton = new MaterialButton(
//                this,
//                null,
//                R.attr.materialButtonOutlinedStyle
//        );
//        materialButton.setIcon(getDrawable(R.drawable.ic_baseline_add_24));
//        materialButton.setOnClickListener(view -> {
//
//        });
//        materialButton.setText("I am a material button");
//
//
//        FloatingActionButton fab = new FloatingActionButton(this);
//        fab.setImageResource(R.drawable.ic_baseline_add_24);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
//        params.setMargins(0, 0, 48, 48);
//        fab.setLayoutParams(params);
//
//        PopupMenu popupMenu = new PopupMenu(this, fab);
//        popupMenu.getMenu().add(0, 1, 1, "Edit");
//        popupMenu.getMenu().add(1, 2, 2, "Delete");
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == 1) {
//                    // open the edit
//                    return true;
//                } else {
//                    return true;
//                }
//            }
//        });
//
//        fab.setOnClickListener((view) -> {
//            popupMenu.show();
//        });
//
//
//        MaterialCardView cardView = new MaterialCardView(this);
////        cardView.setPadding(48, 48, 48, 48);
//
//        MaterialCardView card = new MaterialCardView(this);
//        card.setPadding(48, 48,48, 48);
//        MaterialTextView textView = new MaterialTextView(this);
//        LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        cardViewParams.setMargins(48, 48, 48 ,48);
//        card.setLayoutParams(cardViewParams);
//        textView.setText("Hello, world!");
//        textView.setTextSize(24);
//
//        MaterialTextView textView1 = new MaterialTextView(this);
//        textView1.setText("I am some text!");
//
//        card.addView(textView);
//        card.addView(textView1);
//        cardView.addView(card);
//        mainLayout.addView(contentLayout);
//        mainLayout.addView(fab);
//        contentLayout.addView(materialButton);
//        contentLayout.addView(cardView);
//        setContentView(mainLayout);
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setImageResource(R.drawable.ic_baseline_add_24);

        contentLayout.addView(fab);

        DrawerLayout drawerLayout = new DrawerLayout(this);
        drawerLayout.addView(contentLayout);

        NavigationView navigationView = new NavigationView(this);
        navigationView.getMenu().add("Home");
        navigationView.getMenu().add("Inbox");
        navigationView.getMenu().add("Settings");

        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = GravityCompat.START;
        navigationView.setLayoutParams(params);

        fab.setOnClickListener((view) -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        drawerLayout.addView(navigationView);

        setContentView(drawerLayout);

    }
}