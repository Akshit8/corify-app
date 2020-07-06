package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    private WebView webViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        webViewActivity = findViewById(R.id.webView);

        webViewActivity.getSettings().setJavaScriptEnabled(true);
        webViewActivity.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        Toast.makeText(this, "Loading News...", Toast.LENGTH_SHORT).show();

        webViewActivity.loadUrl(link);
    }
}
