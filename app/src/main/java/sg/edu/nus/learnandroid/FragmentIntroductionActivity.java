package sg.edu.nus.learnandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_introduction);

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
                finish();
            }
        });

//        // Webview content hard code
//        String title = "Fragments";
//        String data = "•" + "&emsp;" + "A Fragment represents a behavior " +
//                "or a portion of user interface in an Activity.&ensp;<br/>" +
//                "•" + "&emsp;" + "Fragments are re-usable components hosted by an Activity.&ensp;<br/>" +
//                "•" + "&emsp;" + "Fragments do not subclass Context. " +
//                "Use context.getActivity() to get the hosting activity.&ensp;<br/>" +
//                "•" + "&emsp;" + "Fragments should not communicate with each other directly. " +
//                "They should communicate through the host activity.&ensp;<br/>";
//
//        WebView webView = (WebView) findViewById(R.id.fragment_concept_webview);
//        webView.loadData("<h1>" + title + "</h1>" + "<p style=\"text-align: justify\" \"margin:50dp\">" +
//                data + "</p>", "text/html", "utf-8");

        WebView webView = (WebView) findViewById(R.id.fragment_introduction_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/fragment_introduction.html");
    }
}
