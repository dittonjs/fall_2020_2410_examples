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
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PhoneButtonData> phoneButtonData = new ArrayList<PhoneButtonData>() {
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

        for (int i = 0; i < phoneButtonData.size(); i++) {
            // do something with i and your phone data
        }

        for (PhoneButtonData data : phoneButtonData) {
            // do something with data
        }

        phoneButtonData.forEach(data -> {
            // do something with data
        });
//        callButton.setOnClickListener(view -> {
//            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
//            phoneIntent.setData(Uri.parse("tel:"+phoneNumberInput.getText().toString()));
//            startActivity(phoneIntent);
//        });
        setContentView(mainLayout);
    }
}