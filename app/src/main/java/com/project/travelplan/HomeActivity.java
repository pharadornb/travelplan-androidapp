package com.project.travelplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    String useruse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.user);
        useruse = intent.getStringExtra(Login.EXTRA_MESSAGE);
        textView.setText(useruse);
    }
}