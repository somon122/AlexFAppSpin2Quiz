package com.world_tech_point.visiting_earnapp.userInfo;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveUserInfo {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SaveUserInfo(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("SaveUserInfo",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void dataStore (String userId,String userName, String imageUrl ,String number ,String email, String refer_code){

        editor.putString("number",number);
        editor.putString("email",email);
        editor.putString("imageUrl",imageUrl);
        editor.putString("userId",userId);
        editor.putString("userName",userName);
        editor.putString("refer_code",refer_code);
        editor.commit();

    }

    public String getUserId (){
        String userId = sharedPreferences.getString("userId","");
        return userId;
    }
    public String getRefer_code (){
        String userId = sharedPreferences.getString("refer_code","");
        return userId;
    }
    public String getUserName (){
        String userName = sharedPreferences.getString("userName","");
        return userName;
    }
    public String getImageUrl (){
        String imageUrl = sharedPreferences.getString("imageUrl","");
        return imageUrl;
    }
    public String getNumber (){
        String number = sharedPreferences.getString("number","");
        return number;
    }

    public String getEmail (){
        String email = sharedPreferences.getString("email","");
        return email;
    }

    public void delete (){
        editor.clear();
        editor.commit();

    }







}
