package com.example.fuddify2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);

        // Text view
        AppCompatTextView textView = new AppCompatTextView(this);
//        textView.setTextSize(18);
        textView.setText("Enter a word or phrase and press Fuddify!");

        // Edit Text
        final AppCompatEditText editText = new AppCompatEditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        editText.setLayoutParams(params);
        editText.setText("Default text");
        // Phrase View
        final AppCompatTextView phraseView = new AppCompatTextView(MainActivity.this);
        // Button
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Fuddify");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("button", "I got clicked");
                String phrase = editText.getText().toString();
                String fuddifiedPhrase = phrase.replace('r', 'w');
                phraseView.setText(fuddifiedPhrase);
            }
        });

        layout.addView(textView);
        layout.addView(editText);
        layout.addView(button);
        layout.addView(phraseView);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
    }
}