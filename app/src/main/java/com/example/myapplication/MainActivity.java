package com.example.myapplication;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure your layout file includes a WebView with id "webview"

        // Use a local final variable for the WebView instance
        final WebView webView = findViewById(R.id.webview);

        // Enable JavaScript (required by many modern websites such as YouTube)
        // Note: Enabling JavaScript may expose your app to XSS vulnerabilities.
        // Only use this if you're loading trusted content.
        webView.getSettings().setJavaScriptEnabled(true);

        // Optional: Improve security by disabling file and content access if not needed
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setAllowContentAccess(false);

        // Set a WebViewClient so links load within the WebView rather than in a browser
        webView.setWebViewClient(new WebViewClient());

        // Load the YouTube URL
        webView.loadUrl("https://www.youtube.com");

        // Handle the back button using onBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    // No more web history, so finish the activity
                    finish();
                }
            }
        });
    }
}
