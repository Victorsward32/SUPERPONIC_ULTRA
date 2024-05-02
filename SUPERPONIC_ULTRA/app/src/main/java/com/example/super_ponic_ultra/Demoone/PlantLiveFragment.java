package com.example.super_ponic_ultra.Demoone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.super_ponic_ultra.R;

public class PlantLiveFragment extends Fragment {

    private TextView liveText;
    private WebView liveWebView;
    private ImageButton zoomInButton;
    private ImageButton zoomOutButton;

    private static final String IP_ADDRESS = "http://192.168.160.242";

    public PlantLiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant_live, container, false);

        liveText = view.findViewById(R.id.livetext);
        liveWebView = view.findViewById(R.id.live_webview);
        zoomInButton = view.findViewById(R.id.ZoomIn);
        zoomOutButton = view.findViewById(R.id.ZoomOut);

        // Display "Live" text
        liveText.setText("Live");

        // Configure WebView
        WebSettings settings = liveWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        // Set WebView client to handle page load errors
        liveWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Display error message when WebView fails to load
                liveText.setText("You need to connect with the same Wi-Fi that your device is connected to.");
            }
        });

        // Load the provided IP address
        liveWebView.loadUrl(IP_ADDRESS);

        // Set click listeners for zoom buttons
        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomIn();
            }
        });

        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomOut();
            }
        });

        // Enable touch screen ability for WebView
        liveWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return view;
    }

    private void zoomIn() {
        liveWebView.zoomIn();
    }

    private void zoomOut() {
        liveWebView.zoomOut();
    }
}
