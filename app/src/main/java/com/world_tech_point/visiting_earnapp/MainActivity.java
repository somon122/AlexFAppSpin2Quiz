package com.world_tech_point.visiting_earnapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.startapp.sdk.adsbase.StartAppAd;
import com.world_tech_point.visiting_earnapp.userInfo.SaveUserInfo;
import com.world_tech_point.visiting_earnapp.userInfo.UserInfoHostActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        if (saveUserInfo.getNumber().equals("")){
         //startActivity(new Intent(getApplicationContext(), UserInfoHostActivity.class));
        }
    }
    SaveUserInfo saveUserInfo;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveUserInfo = new SaveUserInfo(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.action_Spinning);

        StartAppAd.disableSplash();
        StartAppAd.disableAutoInterstitial();


        GameFragment gameFragment = new GameFragment();
        setFragment(gameFragment);
        bottomNavigationView.setSelectedItemId(R.id.action_Game);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.action_Game:
                        GameFragment gameFragment = new GameFragment();
                        setFragment(gameFragment);
                        return true;
                    case R.id.action_Earning:
                      ProfileFragment profileFragment = new ProfileFragment();
                      setFragment(profileFragment);
                        return true;
                    case R.id.action_Spinning:
                       EarningFragment earningFragment = new EarningFragment();
                       setFragment(earningFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    private void setFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mainHost_id,fragment)
                .commit();
    }
    @Override
    public void onBackPressed() {
            exitsAlert();
    }

    private void exitsAlert() {
        finishAffinity();
    }

}