package sg.edu.nus.learnandroid;

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

/**
 * Created by Yongxue
 */

public class UserInterfaceQuizResultsActivity extends AppCompatActivity {

    public static final String MY_SHAREDPREF_NAME = "UserInterfaceQuizAnsSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface_quiz_results);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_crossbtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_with_crossbtn);
        actionBarTitleTV.setText(R.string.course_user_interface);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_cross_with_crossbtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), StaticFragmentQuizInfoActivity.class);
                startActivity(myIntent);
            }
        });

        WebView webView = (WebView) findViewById(R.id.ui_quiz_result_webview);
        UserInterfaceQuizResultsActivity.JavaScriptInterface myJavaScriptInterface =
                new UserInterfaceQuizResultsActivity.JavaScriptInterface(this, webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(myJavaScriptInterface, "UserInterfaceQuizResults");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/ui_quiz_result.html");
    }

    public class JavaScriptInterface {

        private UserInterfaceQuizResultsActivity parentActivity;
        private WebView webView;

        public JavaScriptInterface(UserInterfaceQuizResultsActivity userInterfaceQuizResultsActivity, WebView mWebView) {
            parentActivity = userInterfaceQuizResultsActivity;
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

            return q1 + "," + q2 + "," + q3 + "," + q4;
        }
    }
}
