package com.example.fuddify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);

        // Text view
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Type in a word or phrase and tap Fuddify!");

        // Edit text
        final AppCompatEditText editText = new AppCompatEditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        editText.setLayoutParams(params);

        // Phrase View
        final AppCompatTextView phraseView = new AppCompatTextView(this);

        // Button
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Fuddify");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phrase = editText.getText().toString();
                String fuddifiedPhrase = phrase.replace('r', 'w');
                Log.d("asdf", fuddifiedPhrase);

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