package com.world_tech_point.visiting_earnapp.userInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.world_tech_point.visiting_earnapp.R;

public class UserInfoHostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_host);

        LoginFragment loginFragment = new LoginFragment();
        fragment(loginFragment);


    }

    private void fragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.userInfoHost,fragment)
        .commit();

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}