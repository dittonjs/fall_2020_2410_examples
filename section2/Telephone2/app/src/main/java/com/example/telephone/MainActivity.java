package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PhoneButtonData> phoneButtonData = new ArrayList<PhoneButtonData>() {
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
        createButtons();
    }

    private void createLayout() {
        GridLayout mainLayout = new GridLayout(this);
        mainLayout.setColumnCount(3);
        mainLayout.setId(R.id.mainLayout);
        setContentView(mainLayout);
    }

    private void createPhoneNumberDisplay() {
        PhoneNumberView phoneNumberView = new PhoneNumberView(this);
        phoneNumberView.setId(R.id.phoneNumberDisplay);
        GridLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(phoneNumberView);
    }

    private void createButtons() {
        GridLayout mainLayout = findViewById(R.id.mainLayout);
        PhoneNumberView phoneNumberView = findViewById(R.id.phoneNumberDisplay);
        phoneButtonData.add(new PhoneButtonData(getResources().getString(R.string.call_button_text), 5, 0, 3, PhoneButtonData.ButtonType.CALL));
        phoneButtonData.forEach(data -> {
            PhoneButton button = new PhoneButton(
                    this,
                    data,
                    (view) -> {
                        if (data.getType() == PhoneButtonData.ButtonType.CALL) {
                            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                            phoneIntent.setData(Uri.parse("tel:"+phoneNumberView.getText().toString()));
                            startActivity(phoneIntent);
                        } else {
                            phoneNumberView.setText(
                                    phoneNumberView.getText().toString() + data.getButtonText()
                            );
                        }
                    }
            );

            mainLayout.addView(button);
            // do something with data
        });
    }

}