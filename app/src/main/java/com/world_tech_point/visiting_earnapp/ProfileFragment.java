package com.world_tech_point.visiting_earnapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.world_tech_point.visiting_earnapp.userInfo.SaveUserInfo;
import com.world_tech_point.visiting_earnapp.withdraw.WalletActivity;
import com.world_tech_point.visiting_earnapp.withdraw.WithdrawHistoryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    CircleImageView imageView;
    TextView profileBalance, profileTotalWithdraw,profileUserName;
    Button withdrawBtn;
    SaveUserInfo saveUserInfo;
    ProgressBar progressBar;

    LinearLayout withdrawHistoryShowBtn, invite, social,privacy,aboutUs,developer,logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        imageView = root.findViewById(R.id.profileCircleImageView);
        profileBalance = root.findViewById(R.id.profileBalance);
        profileTotalWithdraw = root.findViewById(R.id.profileTotalWithdraw);
        withdrawBtn = root.findViewById(R.id.profileWithdrawBtn);
        progressBar = root.findViewById(R.id.profileProgressBar);

        withdrawHistoryShowBtn = root.findViewById(R.id.profileHistoryShowBtn);
        profileUserName = root.findViewById(R.id.profileUserName);
        invite = root.findViewById(R.id.profileInviteFriend);
        aboutUs = root.findViewById(R.id.profileAboutUs);
        social = root.findViewById(R.id.profileSocialGroup);
        privacy = root.findViewById(R.id.profilePrivacyPolicy);
        developer = root.findViewById(R.id.profileDeveloperOption);
        logout = root.findViewById(R.id.profileLogout);


        saveUserInfo = new SaveUserInfo(getContext());
        userInfoMethod(saveUserInfo.getNumber());
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WalletActivity.class));
            }
        });

        withdrawHistoryShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WithdrawHistoryActivity.class));
            }
        });
      invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReferActivity.class));
            }
        });
      social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Social Group", Toast.LENGTH_SHORT).show();
            }
        });
      aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "aboutUs", Toast.LENGTH_SHORT).show();
            }
        });

      privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "privacy", Toast.LENGTH_SHORT).show();
            }
        });
     developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "developer", Toast.LENGTH_SHORT).show();
            }
        });

    logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo.delete();
               getActivity().finishAffinity();
            }
        });
        return root;
    }

    private void userInfoMethod(final String number) {
        String url = API_Method.BASE_URL + "retrieve_user_info";
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

                            String userName = dataObj.getString("userName");
                            String imageUrl = dataObj.getString("imageUrl");
                            String main_point = dataObj.getString("main_point");
                            String total_withdraw = dataObj.getString("total_withdraw");

                            String img = API_Method.IMAGE_BASE_URL + imageUrl;
                            Picasso.get().load(img).placeholder(R.drawable.person).fit().into(imageView);
                            profileTotalWithdraw.setText(total_withdraw);
                            profileUserName.setText(userName);
                            int dollar = Integer.parseInt(main_point) / 85;
                            profileBalance.setText("$" + dollar);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Problem", Toast.LENGTH_SHORT).show();
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
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }


}