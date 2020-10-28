package com.world_tech_point.visiting_earnapp.withdraw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.world_tech_point.visiting_earnapp.API_Method;
import com.world_tech_point.visiting_earnapp.R;
import com.world_tech_point.visiting_earnapp.userInfo.SaveUserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WithdrawHistoryActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    RecyclerView recyclerView;
    List<WithdrawClass>withdrawClassList;
    ProgressBar progressBar;
    SaveUserInfo saveUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_history);

        Toolbar toolbar = findViewById(R.id.withdrawHistoryToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.historyProgressBar);
        withdrawClassList= new ArrayList<>();
        recyclerView = findViewById(R.id.wHRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        saveUserInfo = new SaveUserInfo(this);
        withdrawMethod(saveUserInfo.getUserId());

    }

    private void withdrawMethod(final String userId) {
        String url = API_Method.BASE_URL + "read_withdraw";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObj = jsonArray.getJSONObject(i);
                            String number = dataObj.getString("payment_request_by");
                            String amount = dataObj.getString("amount");
                            String method = dataObj.getString("withdraw_method");
                            String status = dataObj.getString("status");
                            WithdrawClass withdrawClass = new WithdrawClass(number,amount,status,method);
                            withdrawClassList.add(withdrawClass);
                        }

                        WithdrawAdapter withdrawAdapter = new WithdrawAdapter(WithdrawHistoryActivity.this, withdrawClassList);
                        recyclerView.setAdapter(withdrawAdapter);
                        withdrawAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(WithdrawHistoryActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WithdrawHistoryActivity.this, "url problem", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("userId", userId);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(WithdrawHistoryActivity.this);
        queue.add(stringRequest);
    }

}