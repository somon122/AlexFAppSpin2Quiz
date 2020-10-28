package com.world_tech_point.visiting_earnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.world_tech_point.visiting_earnapp.userInfo.SaveUserInfo;

import java.util.Random;

public class ReferActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    TextView referCodeShow;
    Button referBtn;
    SaveUserInfo saveUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);

        Toolbar toolbar = findViewById(R.id.referToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        referCodeShow = findViewById(R.id.referCodeShow);
        referBtn = findViewById(R.id.referBtn);
        saveUserInfo = new SaveUserInfo(this);
        referCodeShow.setText(saveUserInfo.getRefer_code());

        referBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareApp();
            }
        });
    }
    private void ShareApp() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody = "App link: "+"https://play.google.com/store/apps/details?id="+getPackageName();
        String shareSub = "Refer code: "+saveUserInfo.getRefer_code();
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Earning App"));
    }

}