package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yongxue
 */

public class DynamicFragmentExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_example);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_crossbtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_with_crossbtn);
        actionBarTitleTV.setText(R.string.course_fragments);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_cross_with_crossbtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DynamicFragmentInfoActivity.class);
                startActivity(myIntent);
            }
        });

        WebView webView = (WebView) findViewById(R.id.dynamic_fragment_example_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/dynamic_fragment_example.html");
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), DynamicFragmentInfoActivity.class);
        startActivity(myIntent);
    }
}
