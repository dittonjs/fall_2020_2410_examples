package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PhoneButtonData> phoneButtonData = new ArrayList<PhoneButtonData>() {
        {
            add(new PhoneButtonData("1", 0, 0, 1));
            add(new PhoneButtonData("2", 0, 1, 1));
            add(new PhoneButtonData("3", 0, 2, 1));
            add(new PhoneButtonData("4", 1, 0, 1));
            add(new PhoneButtonData("5", 1, 1, 1));
            add(new PhoneButtonData("6", 1, 2, 1));
            add(new PhoneButtonData("7", 2, 0, 1));
            add(new PhoneButtonData("8", 2, 1, 1));
            add(new PhoneButtonData("9", 2, 2, 1));
            add(new PhoneButtonData("*", 3, 0, 1));
            add(new PhoneButtonData("0", 3, 1, 1));
            add(new PhoneButtonData("#", 3, 2, 1));
            add(new PhoneButtonData("CALL", 4, 0, 3));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        GridLayout phoneButtonsLayout = new GridLayout(this);
        phoneButtonData.forEach(data -> {
            PhoneButton button = new PhoneButton(
                    this,
                    data,
                    (view) -> {}
            );

        });


//        callButton.setOnClickListener(view -> {
//            Intent callIntent = new Intent(Intent.ACTION_CALL);
//            callIntent.setData(Uri.parse("tel:"+phoneNumberView.getText().toString()));
//            startActivity(callIntent);
//        });

        setContentView(mainLayout);
    }
}