package com.world_tech_point.visiting_earnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ReferActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);

        textView = findViewById(R.id.showRandomText);


    }

    public void Random(View view) {

        String referCode = randomText(8);
        textView.setText(referCode);
        Toast.makeText(this, ""+referCode, Toast.LENGTH_SHORT).show();

    }

    private String randomText(int length) {

        char[] chars ="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i =0; i < length; i++){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}