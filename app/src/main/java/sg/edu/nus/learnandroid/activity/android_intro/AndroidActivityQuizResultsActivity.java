package sg.edu.nus.learnandroid.activity.android_intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;

/**
 * Created by Yongxue
 */

public class AndroidActivityQuizResultsActivity extends AppCompatActivity {

    public static final String MY_SHAREDPREF_NAME = "AndroidActivityQuizAnsSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_activity_quiz_results);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_crossbtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_with_crossbtn);
        actionBarTitleTV.setText(R.string.course_intent);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_cross_with_crossbtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), AndroidActivityInfoActivity.class);
                startActivity(myIntent);
            }
        });

        WebView webView = (WebView) findViewById(R.id.android_activity_quiz_result_webview);
        AndroidActivityQuizResultsActivity.JavaScriptInterface myJavaScriptInterface =
                new AndroidActivityQuizResultsActivity.JavaScriptInterface(this, webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(myJavaScriptInterface, "AndroidActivityQuizResults");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/android_activity_quiz_result.html");
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), AndroidActivityInfoActivity.class);
        startActivity(myIntent);
    }

    public class JavaScriptInterface {

        private AndroidActivityQuizResultsActivity parentActivity;
        private WebView webView;

        public JavaScriptInterface(AndroidActivityQuizResultsActivity androidActivityQuizResultsActivity, WebView mWebView) {
            parentActivity = androidActivityQuizResultsActivity;
            webView = mWebView;
        }

        @JavascriptInterface
        public String returnQuizAnsToWebView() {

            SharedPreferences prefs = getSharedPreferences(MY_SHAREDPREF_NAME,
                    MODE_PRIVATE);

            String q1 = prefs.getString("q1", "default value");
            String q2 = prefs.getString("q2", "default value");
            String q3 = prefs.getString("q3", "default value");
            String q4 = prefs.getString("q4", "default value");
            String q5 = prefs.getString("q5", "default value");

            return q1 + "," + q2 + "," + q3 + "," + q4 + "," + q5;
        }
    }
}
