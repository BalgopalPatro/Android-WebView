package com.balgopal.multiproduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private WebView myweb ;
    private int p;
    private AdView mAdView,mAdview2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        mAdview2 = findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdview2.loadAd(adRequest2);


        myweb = findViewById(R.id.myweb);
        myweb.getSettings().setJavaScriptEnabled(true);
        myweb.getSettings().setAppCacheEnabled(true);
        myweb.getSettings().setUseWideViewPort(true);
        myweb.setWebViewClient(new WebViewController());
        myweb.loadUrl("https://www.multiproduct.net/");
    }

    @Override
    protected void onResume() {
        super.onResume();
        myweb.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myweb.onPause();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myweb.canGoBack()) {
                        myweb.goBack();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Are You Want to exit").setCancelable(false) ;
                        final EditText ed = new EditText(MainActivity.this);
//                        ed.setInputType(InputType.TYPE_CLASS_TEXT);
//                        builder.setView(R.layout.alert_view);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
//                        if (doubleBackToExitPressedOnce) {
//                            super.onBackPressed();
//                            return true;
//                        }
//
//                        this.doubleBackToExitPressedOnce = true;
//                        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//                        new Handler().postDelayed(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                doubleBackToExitPressedOnce=false;
//                            }
//                        }, 2000);
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

class WebViewController extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}


