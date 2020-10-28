package com.world_tech_point.visiting_earnapp.userInfo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.world_tech_point.visiting_earnapp.App_Controller;
import com.world_tech_point.visiting_earnapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


public class RegisterFragment extends Fragment {

    private static final int PICK_IMAGE = 100;
    private static final int STORAGE_PERMISSION = 101;
    private static final int PHONE_PERMISSION_CODE = 102;
    TextView registerAlreadyLogin, imageChoose;
    TextInputLayout userName, number, emailAddress, password, confirmPassword, referCode;
    Button registerButton;
    ProgressBar progressBar;
    String currentDateAndTime;
    String deviceId;
    App_Controller app_controller;
    CircleImageView imageView;
    Uri imageURI = null;
    Bitmap imagePath = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        registerAlreadyLogin = root.findViewById(R.id.signUpAlreadyLogin_id);

        userName = root.findViewById(R.id.registerUserName);
        number = root.findViewById(R.id.registerNumber);
        emailAddress = root.findViewById(R.id.registerEmail);
        password = root.findViewById(R.id.registerPassword);
        app_controller = new App_Controller(getContext());

        imageChoose = root.findViewById(R.id.imageChoose);
        imageView = root.findViewById(R.id.circleImageView);

        confirmPassword = root.findViewById(R.id.registerConfirmPassword);
        referCode = root.findViewById(R.id.registerReferCode);
        registerButton = root.findViewById(R.id.registerSubmit);
        progressBar = root.findViewById(R.id.registerProgressBar);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss");
        currentDateAndTime = sdf.format(new Date());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = telephonyManager.getDeviceId();

            } else {
                requestPhoneStatePermission();
            }
        } else {

            TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = telephonyManager.getDeviceId();

        }

        imageChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkPermissions();
                } else {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }

            }
        });

        registerAlreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginFragment loginFragment = new LoginFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.userInfoHost, loginFragment)
                        .commit();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        return root;
    }


    public String getFileData(Bitmap bitmap) {

        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        }
        return "";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE) {
            imageURI = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageURI);
                imagePath = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                imageView.setImageBitmap(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void requestPhoneStatePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.READ_PHONE_STATE)) {

            new AlertDialog.Builder(getContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed for Register or SignUp")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_PERMISSION_CODE);
        }
    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, STORAGE_PERMISSION
            );

        }

    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case STORAGE_PERMISSION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                } else {
                    Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case PHONE_PERMISSION_CODE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getContext(), "Permission granted and you can access this app", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


    private void register() {

        String name = userName.getEditText().getText().toString();
        String email = emailAddress.getEditText().getText().toString();
        String num = number.getEditText().getText().toString().trim();
        String pass = password.getEditText().getText().toString();
        String cPass = confirmPassword.getEditText().getText().toString();
        String refer = referCode.getEditText().getText().toString();

        if (name.isEmpty()) {
            userName.getEditText().setError("Enter UserName");
        } else if (email.isEmpty()) {
            emailAddress.getEditText().setError("Enter Email Address");
        } else if (num.isEmpty()) {
            number.getEditText().setError("Enter Number");
        } else if (pass.isEmpty()) {
            password.getEditText().setError("Enter password");
        } else if (cPass.isEmpty()) {
            confirmPassword.getEditText().setError("Enter confirm password");
        } else if (refer.isEmpty()) {
            referCode.getEditText().setError("Enter refer code");
        } else {

            registerMethod(name, email, num, cPass, refer, random_refer_code(), deviceId, "50", currentDateAndTime);
        }

    }

    private void registerMethod(final String userName, final String email, final String number,
                                final String password, final String refer_code, final String new_refer_code,
                                final String device_id, final String refer_point, final String date_time) {
        String url = API_Method.BASE_URL + "write_profile";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("response").equals("success")) {

                        Toasty.success(getContext(), "Register Success", Toasty.LENGTH_SHORT).show();
                        LoginFragment loginFragment = new LoginFragment();
                        fragment(loginFragment);

                    } else if (obj.getString("response").equals("field")) {
                        Toast.makeText(getContext(), "Register Field", Toast.LENGTH_SHORT).show();

                    } else if (obj.getString("response").equals("refer_point_not_added")) {
                        Toast.makeText(getContext(), "refer_point_not_added", Toast.LENGTH_SHORT).show();

                    } else if (obj.getString("response").equals("refer_not_exists")) {
                        Toast.makeText(getContext(), "refer_not_exists", Toast.LENGTH_SHORT).show();

                    } else if (obj.getString("response").equals("user_exists")) {
                        Toast.makeText(getContext(), "user_exists", Toast.LENGTH_SHORT).show();
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
                Params.put("userName", userName);
                Params.put("imageUrl", getFileData(imagePath));
                Params.put("number", number);
                Params.put("email", email);
                Params.put("password", password);
                Params.put("device_id", device_id);
                Params.put("refer_point", refer_point);
                Params.put("new_refer_code", new_refer_code);
                Params.put("refer_code", refer_code);
                Params.put("date_time", date_time);
                return Params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }

    private void fragment(Fragment fragment) {

        FragmentManager manager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.userInfoHost, fragment)
                .commit();

    }

    private String random_refer_code(){
        char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i<8;i++){
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}