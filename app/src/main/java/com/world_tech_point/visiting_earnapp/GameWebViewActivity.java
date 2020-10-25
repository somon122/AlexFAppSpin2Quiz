package com.world_tech_point.visiting_earnapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class GameWebViewActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_web_view);


        webView = findViewById(R.id.gameWebView_id);
        progressBar = findViewById(R.id.gameProgressBar);

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            url = bundle.getString("url");
            webView.loadUrl("https://showcase.codethislab.com/games/zippy_pixie/");
        }

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setVerticalScrollBarEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBar.setVisibility(View.GONE);

                if (!HaveNetwork()){
                    view.stopLoading();
                }
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


            }

        });


    }

    private boolean HaveNetwork() {
        boolean have_WiFi = false;
        boolean have_Mobile = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfo) {

            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected() && info.isAvailable()) {
                    have_WiFi = true;
                } else if (info.isRoaming() && info.isFailover()) {
                    have_WiFi = false;
                }
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected() && info.isAvailable()) {

                    have_Mobile = true;
                } else if (info.isRoaming() && info.isFailover()) {
                    have_WiFi = false;
                }
            }
        }
        return have_WiFi || have_Mobile;
    }

}