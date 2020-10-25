package com.world_tech_point.visiting_earnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1500;
    //varriable
    Animation top,bottom;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //countryName();

        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //Hooks
        imageView = findViewById(R.id.image1);
        textView = findViewById(R.id.text1);
        imageView.setAnimation(top);
        textView.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashActivity.this,MainActivity.class));
               finish();
            }
        },SPLASH_SCREEN);

    }

    private void countryName(){

        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocation(37.090240, -95.712891, 1);

            String add = "";
            if (addresses.size() > 0)
            {
                for (int i=0; i<addresses.get(0).getMaxAddressLineIndex();i++)
                    add += addresses.get(0).getAddressLine(i) + "\n";
            }

            Toast.makeText(this, ""+add, Toast.LENGTH_SHORT).show();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}