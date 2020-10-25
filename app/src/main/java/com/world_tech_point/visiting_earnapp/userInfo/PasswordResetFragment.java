package com.world_tech_point.visiting_earnapp.userInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.world_tech_point.visiting_earnapp.API_Method;
import com.world_tech_point.visiting_earnapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PasswordResetFragment extends Fragment {

    TextInputLayout emailET, numberET;
    Button verifyBtn;
    TextView userName,password;
    ProgressBar progressBar;
    CardView resultView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_forget_pass, container, false);


        emailET = root.findViewById(R.id.forgetEmail);
        numberET = root.findViewById(R.id.forgetNumber);
        verifyBtn = root.findViewById(R.id.forgetSubmitBtn);
        userName = root.findViewById(R.id.forgetSHowUserName);
        password = root.findViewById(R.id.forgetShowPassword);
        progressBar = root.findViewById(R.id.forgetProgressBar);
        resultView = root.findViewById(R.id.forgetResult);

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailET.getEditText().getText().toString();
                String number = numberET.getEditText().getText().toString();
                if (email.isEmpty()){
                    emailET.getEditText().setError("Enter Valid Email address");
                }else if (number.isEmpty()){
                    numberET.getEditText().setError("Enter Valid number");
                }else {
                    forgetMethod(number,email);
                }
            }
        });

        return root;
    }

    private void forgetMethod(final String number, final String email) {
        String url = API_Method.BASE_URL + "forget_password";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("success")) {
                        resultView.setVisibility(View.VISIBLE);
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObj = jsonArray.getJSONObject(i);
                            String name = dataObj.getString("userName");
                            String pass = dataObj.getString("password");
                            userName.setText("Your Name: "+name);
                            password.setText("Your password: "+pass);
                        }
                    } else if (obj.getString("response").equals("not_match")) {
                        Toast.makeText(getContext(), "Number and email could not match", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Problem", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "url problem", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("number", number);
                Params.put("email", email);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }


}