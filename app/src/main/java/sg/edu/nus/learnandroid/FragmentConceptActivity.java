package sg.edu.nus.learnandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentConceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_concept);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText("Fragments");

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Webview content
        String title = "Fragments";
        String data = "•" + "&emsp;" + "A Fragment represents a behavior " +
                "or a portion of user interface in an Activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments are re-usable components hosted by an Activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments do not subclass Context. " +
                "Use context.getActivity() to get the hosting activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments should not communicate with each other directly. " +
                "They should communicate through the host activity.&ensp;<br/>";

        WebView webView = (WebView) findViewById(R.id.fragment_concept_webview);
        webView.loadData("<h1>" + title + "</h1>" + "<p style=\"text-align: justify\" \"margin:50dp\">" +
                data + "</p>", "text/html", "utf-8");
    }
}
