package com.world_tech_point.visiting_earnapp.withdraw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
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

import es.dmoral.toasty.Toasty;


public class WalletActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    TextInputLayout requestByET, amountET;
    Spinner spinner;
    Button submitBtn;
    ProgressBar progressBar;

    SaveUserInfo saveUserInfo;
    List<String> methodList;
    String paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        Toolbar toolbar = findViewById(R.id.withdrawToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        saveUserInfo = new SaveUserInfo(this);
        requestByET = findViewById(R.id.walletPayment_request_by);
        amountET = findViewById(R.id.walletAmount_id);
        spinner = findViewById(R.id.walletSpinner);
        submitBtn = findViewById(R.id.walletSubmitBtn);
        progressBar = findViewById(R.id.walletProgressBar);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!paymentMethod.equals("Select payment Method")) {
                    submitWallet();
                } else {
                    Toasty.error(WalletActivity.this, "Please Select payment method", Toasty.LENGTH_LONG).show();
                }
            }
        });

        methodList = new ArrayList<>();
        methodList.add("Select payment Method");
        methodList.add("Bkash");
        methodList.add("Rocket");
        methodList.add("Nagot");
        methodList.add("Mobile Recharge");
        methodList.add("Paypal");
        methodList.add("BitCoin");

        ArrayAdapter<String> mainDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, methodList);
        mainDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mainDataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                paymentMethod = spinner.getSelectedItem().toString();
                methodHints(paymentMethod);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void methodHints(String value) {

        if (value.equals("Select payment Method")) {

            requestByET.getEditText().setHint("Select payment Method first");

        } else if (value.equals("Bkash")) {

            requestByET.getEditText().setHint("Enter Bkash number");

        } else if (value.equals("Rocket")) {

            requestByET.getEditText().setHint("Enter Rocket number");

        } else if (value.equals("Nagot")) {

            requestByET.getEditText().setHint("Enter Nagot number");

        } else if (value.equals("Mobile Recharge")) {

            requestByET.getEditText().setHint("Enter Mobile number");

        } else if (value.equals("Paypal")) {

            requestByET.getEditText().setHint("Enter Paypal Email Address");

        } else if (value.equals("BitCoin")) {

            requestByET.getEditText().setHint("Enter BitCoin address");

        } else {
            requestByET.getEditText().setHint("Select payment Method first");
        }


    }

    private void submitWallet() {

        String amount = amountET.getEditText().getText().toString().trim();
        String requestBy = requestByET.getEditText().getText().toString();

        if (amount.isEmpty()) {


        } else if (requestBy.isEmpty()) {


        }else {
            Toasty.success(WalletActivity.this, "Payment Method Sent Successfully", Toasty.LENGTH_SHORT).show();
            //insertWithdraw("");

        }
    }

    private void insertWithdraw(final String number) {
        String url = API_Method.BASE_URL + "insert_withdraw";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("request_sent")) {
                        Toasty.success(WalletActivity.this, "Payment Method Sent Successfully", Toasty.LENGTH_SHORT).show();
                        finish();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(WalletActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WalletActivity.this, "url problem", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("number", number);
                Params.put("number", number);
                Params.put("number", number);
                Params.put("number", number);
                Params.put("number", number);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(WalletActivity.this);
        queue.add(stringRequest);
    }


}

