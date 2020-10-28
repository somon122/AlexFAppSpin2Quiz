package com.world_tech_point.visiting_earnapp;

import android.content.Context;
import android.content.SharedPreferences;

public class App_Controller {

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public App_Controller(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("App_Controller",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void dataStore (String wait_time,String B_R_N_limit, String paypal_limit,
                           String bitcoin_limit ,String recharge_limit, String vpn_country,
                           String quiz_limit ,String point_divider, String withdraw_status){
        editor.putString("wait_time",wait_time);
        editor.putString("B_R_N_limit",B_R_N_limit);
        editor.putString("paypal_limit",paypal_limit);
        editor.putString("bitcoin_limit",bitcoin_limit);
        editor.putString("recharge_limit",recharge_limit);
        editor.putString("vpn_country",vpn_country);
        editor.putString("quiz_limit",quiz_limit);
        editor.putString("point_divider",point_divider);
        editor.putString("withdraw_status",withdraw_status);
        editor.commit();

    }

        public String getWaitTime (){
        String wait_time = sharedPreferences.getString("wait_time","0");
        return wait_time;
    }
        public String getB_R_N_limit (){
        String B_R_N_limit = sharedPreferences.getString("B_R_N_limit","0");
        return B_R_N_limit;
    }
        public String getpaypal_limit (){
        String paypal_limit = sharedPreferences.getString("paypal_limit","0");
        return paypal_limit;
    }
        public String getbitcoin_limit (){
        String bitcoin_limit = sharedPreferences.getString("bitcoin_limit","0");
        return bitcoin_limit;
    }
        public String getrecharge_limit(){
        String recharge_limit = sharedPreferences.getString("recharge_limit","0");
        return recharge_limit;
    }
        public String getvpn_country (){
        String vpn_country = sharedPreferences.getString("vpn_country","");
        return vpn_country;
    }
        public String getquiz_limit (){
        String quiz_limit = sharedPreferences.getString("quiz_limit","0");
        return quiz_limit;
    }
        public String getpoint_divider (){
        String point_divider = sharedPreferences.getString("point_divider","0");
        return point_divider;
    }
        public String getwithdraw_status (){
        String withdraw_status = sharedPreferences.getString("withdraw_status","");
        return withdraw_status;
    }

}
