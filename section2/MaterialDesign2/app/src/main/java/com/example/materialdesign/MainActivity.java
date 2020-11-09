package com.example.materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
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
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        FloatingActionButton fab = new FloatingActionButton(this);
        fab.setImageResource(R.drawable.ic_baseline_add_24);
        contentLayout.addView(fab);

        DrawerLayout drawerLayout = new DrawerLayout(this);
        drawerLayout.addView(contentLayout);

        NavigationView navigationView = new NavigationView(this);
        navigationView.getMenu().add(0,1,1, "Home");
        navigationView.getMenu().add(1,1,2,"Inbox");
        navigationView.getMenu().add(2, 1, 3,"Settings");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

        DrawerLayout.LayoutParams navigationParams = new DrawerLayout.LayoutParams(400, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationParams.gravity = Gravity.RIGHT;
        navigationView.setLayoutParams(navigationParams);

        fab.setOnClickListener((view) -> {
            drawerLayout.openDrawer(Gravity.RIGHT);
        });

        drawerLayout.addView(navigationView);

        setContentView(drawerLayout);
    }
}