package sg.edu.nus.learnandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentQuizResultsActivity extends AppCompatActivity {

    public static final String MY_SHAREDPREF_NAME = "FragmentQuizAnsSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_quiz_results);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_fragments);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebView webView = (WebView) findViewById(R.id.fragment_quiz_result_webview);
        JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(this, webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(myJavaScriptInterface, "FragmentQuizResults");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/fragment_quiz_result.html");
    }

    public class JavaScriptInterface {

        private FragmentQuizResultsActivity parentActivity;
        private WebView webView;

        public JavaScriptInterface(FragmentQuizResultsActivity fragmentQuizResultsActivity, WebView mWebView) {
            parentActivity = fragmentQuizResultsActivity;
            webView = mWebView;
        }

        @JavascriptInterface
        public void returnQuizAnsToWebView() {
            Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();

//            return "hi I am a string";
        }
    }
}
