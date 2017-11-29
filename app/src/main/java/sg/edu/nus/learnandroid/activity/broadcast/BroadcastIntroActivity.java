package sg.edu.nus.learnandroid.activity.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;

/**
 * Created by Yongxue
 */

public class BroadcastIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_intro);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_passing_data);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), BroadcastInfoActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        WebView webView = (WebView) findViewById(R.id.broadcast_intro_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/broadcast_introduction.html");
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), BroadcastInfoActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
