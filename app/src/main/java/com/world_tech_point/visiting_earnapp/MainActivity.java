package com.world_tech_point.visiting_earnapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.startapp.sdk.adsbase.StartAppAd;
import com.world_tech_point.visiting_earnapp.userInfo.SaveUserInfo;
import com.world_tech_point.visiting_earnapp.userInfo.UserInfoHostActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        if (saveUserInfo.getNumber().equals("")){
         startActivity(new Intent(getApplicationContext(), UserInfoHostActivity.class));
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

    private void AppControllerMethod() {
        String url = API_Method.BASE_URL + "read_app_controller";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObj = jsonArray.getJSONObject(i);
                            String wait_time = dataObj.getString("wait_time");
                            String B_R_N_limit = dataObj.getString("B_R_N_limit");
                            String paypal_limit = dataObj.getString("paypal_limit");
                            String bitcoin_limit = dataObj.getString("bitcoin_limit");
                            String recharge_limit = dataObj.getString("recharge_limit");
                            String vpn_country = dataObj.getString("vpn_country");
                            String quiz_limit = dataObj.getString("quiz_limit");
                            String point_divider = dataObj.getString("point_divider");
                            String withdraw_status = dataObj.getString("withdraw_status");
                            App_Controller app_controller = new App_Controller(MainActivity.this);
                            app_controller.dataStore(wait_time,B_R_N_limit,paypal_limit,bitcoin_limit,recharge_limit,
                                    vpn_country,quiz_limit,point_divider,withdraw_status);

                        }
                    } else if (obj.getString("response").equals("field")) {

                        Toast.makeText(MainActivity.this, "Number and password could not match", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "url problem", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }



}