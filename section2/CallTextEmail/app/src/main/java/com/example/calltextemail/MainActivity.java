package com.example.calltextemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.calltextemail.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.MVPView {
    AppCompatEditText phoneNumber;
    AppCompatEditText email;
    MainPresenter presenter;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private final int REQUEST_SMS_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        phoneNumber = new AppCompatEditText(this);
        mainLayout.addView(phoneNumber);
        AppCompatButton callButton = new AppCompatButton(this);
        callButton.setOnClickListener((view) -> {
            presenter.handleCallPressed(phoneNumber.getText().toString());
        });
        callButton.setText("Call");
        mainLayout.addView(callButton);

        AppCompatButton textButton = new AppCompatButton(this);
        textButton.setOnClickListener((view) -> {
            presenter.handleTextPressed(phoneNumber.getText().toString());
        });
        textButton.setText("Text");
        mainLayout.addView(textButton);

        email = new AppCompatEditText(this);
        mainLayout.addView(email);

        AppCompatButton emailButton = new AppCompatButton(this);
        emailButton.setOnClickListener((view) -> {
            presenter.handleEmailPressed(email.getText().toString());
        });
        emailButton.setText("Email");
        mainLayout.addView(emailButton);
        setContentView(mainLayout);
    }

    @Override
    public void makePhoneCall(String number) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+number));
            startActivity(callIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_PERMISSIONS);
        }

    }

    @Override
    public void sendText(String number) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:"+ number));
            startActivity(smsIntent);
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSIONS);
        }
    }

    @Override
    public void sendEmail(String address) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+address));
        startActivity(emailIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PHONE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.handleCallPressed(phoneNumber.getText().toString());
            } else {
                // display a message saying that they will need to allow permission
            }
        }

        if (requestCode == REQUEST_SMS_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.handleTextPressed(phoneNumber.getText().toString());
            } else {
                // display a message saying that they will need to allow permission
            }
        }
    }
}