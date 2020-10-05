package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PhoneButtonData> phoneButtonData = new ArrayList<PhoneButtonData>() {
        {
            add(new PhoneButtonData("1", 1, 0, 1));
            add(new PhoneButtonData("2", 1, 1, 1));
            add(new PhoneButtonData("3", 1, 2, 1));
            add(new PhoneButtonData("4", 2, 0, 1));
            add(new PhoneButtonData("5", 2, 1, 1));
            add(new PhoneButtonData("6", 2, 2, 1));
            add(new PhoneButtonData("7", 3, 0, 1));
            add(new PhoneButtonData("8", 3, 1, 1));
            add(new PhoneButtonData("9", 3, 2, 1));
            add(new PhoneButtonData("*", 4, 0, 1));
            add(new PhoneButtonData("0", 4, 1, 1));
            add(new PhoneButtonData("#", 4, 2, 1));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createLayout();
        createPhoneNumberDisplay();
        addButtons();
    }

    private void createLayout() {
        GridLayout mainLayout = new GridLayout(this);
        mainLayout.setColumnCount(3);
        mainLayout.setBackgroundColor(Color.GRAY);
        mainLayout.setId(R.id.mainLayout);
        setContentView(mainLayout);
    }

    private void createPhoneNumberDisplay() {
        PhoneNumberDisplay phoneNumberDisplay = new PhoneNumberDisplay(this);
        phoneNumberDisplay.setId(R.id.phoneNumberDisplay);
        GridLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(phoneNumberDisplay);
    }

    private void addButtons() {
        phoneButtonData.add(
                new PhoneButtonData(getString(R.string.call), 5, 0, 3, PhoneButtonData.ButtonType.CALL)
        );

        GridLayout mainLayout = findViewById(R.id.mainLayout);
        PhoneNumberDisplay phoneNumberDisplay = findViewById(R.id.phoneNumberDisplay);
        phoneButtonData.forEach(data -> {
            PhoneButton button = new PhoneButton(
                    this,
                    data,
                    (view) -> {
                        if (data.getType() == PhoneButtonData.ButtonType.CALL) {
                            // make the phone call
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(
                                    Uri.parse("tel:"+phoneNumberDisplay.getText().toString())
                            );
                            startActivity(callIntent);
                        } else {
                            phoneNumberDisplay.setText(
                                    phoneNumberDisplay.getText().toString() + data.getButtonText()
                            );
                        }
                    }
            );
            mainLayout.addView(button);
        });
    }
}