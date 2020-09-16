package com.example.signuppage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.signuppage.views.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        Header loginInfoHeader = new Header(this);
        loginInfoHeader.setText("Login Info");
        mainLayout.addView(loginInfoHeader);

        LabelledInput userNameInput = new LabelledInput(this, "Username");
        mainLayout.addView(userNameInput);

        LabelledInput passwordInput = new LabelledInput(this, "Password");
        mainLayout.addView(passwordInput);

        Header personalInfoHeader = new Header(this);
        personalInfoHeader.setText("Personal Info");
        mainLayout.addView(personalInfoHeader);

        final LabelledInput firstNameInput = new LabelledInput(this, "First Name");
        mainLayout.addView(firstNameInput);

        LabelledInput lastNameInput = new LabelledInput(this, "Last Name");
        mainLayout.addView(lastNameInput);

        LabelledInput addressInput = new LabelledInput(this, "Address");
        mainLayout.addView(addressInput);

        AppCompatButton submitButton = new AppCompatButton(this);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("First Name", firstNameInput.getText().toString());
            }
        });
        mainLayout.addView(submitButton);

        setContentView(mainLayout);


    }
}