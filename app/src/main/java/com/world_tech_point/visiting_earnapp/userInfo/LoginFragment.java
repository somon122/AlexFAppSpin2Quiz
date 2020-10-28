package com.world_tech_point.visiting_earnapp.userInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.world_tech_point.visiting_earnapp.API_Method;
import com.world_tech_point.visiting_earnapp.MainActivity;
import com.world_tech_point.visiting_earnapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class LoginFragment extends Fragment {

    private TextView loginCreateAccount, forgetPassword;
    private Button submit;
    private TextInputLayout number, password;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        loginCreateAccount = root.findViewById(R.id.loginCreateAccount_id);
        forgetPassword = root.findViewById(R.id.loginPasswordReset);
        submit = root.findViewById(R.id.loginSubmit);
        number = root.findViewById(R.id.loginNumber);
        password = root.findViewById(R.id.loginPassword);
        progressBar = root.findViewById(R.id.loginProgressBar);
        loginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.userInfoHost, registerFragment)
                        .commit();
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello World", Toast.LENGTH_SHORT).show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        return root;
    }

    private void login() {

        String num = number.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString().trim();

        if (num.isEmpty()) {
            number.getEditText().setError("Enter number");
        } else if (pass.isEmpty()) {
            password.getEditText().setError("Enter Password");
        } else {
            loginMethod(num, pass);
        }
    }

    private void loginMethod(final String number, final String password) {
        String url = API_Method.BASE_URL + "user_login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("login_success")) {
                        String res = obj.getString("list");
                        JSONArray jsonArray = new JSONArray(res);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject dataObj = jsonArray.getJSONObject(i);
                            int userId = dataObj.getInt("id");
                            String userName = dataObj.getString("userName");
                            String imageUrl = dataObj.getString("imageUrl");
                            String number = dataObj.getString("number");
                            String email = dataObj.getString("email");
                            String refer_code = dataObj.getString("refer_code");
                            SaveUserInfo saveUserInfo = new SaveUserInfo(getContext());
                            saveUserInfo.dataStore(String.valueOf(userId), userName,imageUrl, number, email, refer_code);
                            Toasty.success(getContext(),"Login Success",Toasty.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        }
                    } else if (obj.getString("response").equals("not_match")) {

                        Toast.makeText(getContext(), "Number and password could not match", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Problem : "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "url problem", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Params = new HashMap<>();
                Params.put("number", number);
                Params.put("password", password);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }

}