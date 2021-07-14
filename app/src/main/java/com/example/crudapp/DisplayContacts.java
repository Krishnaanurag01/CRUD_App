package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayContacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        Intent intent = getIntent();
        TextView nameText = findViewById(R.id.displayName2);
        TextView numberText = findViewById(R.id.displayNumber2);

        String name= intent.getStringExtra("Name");
        String number= intent.getStringExtra("Number");

        nameText.setText(name);
        numberText.setText(number);

    }
}