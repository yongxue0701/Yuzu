package sg.edu.nus.learnandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class FragmentConceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_concept);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        String title = "Fragments";
        String dataFirstLine = "•" + "&emsp;" + "A Fragment represents a behavior " +
                "or a portion of user interface in an Activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments are re-usable components hosted by an Activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments do not subclass Context. " +
                "Use context.getActivity() to get the hosting activity.&ensp;<br/>" +
                "•" + "&emsp;" + "Fragments should not communicate with each other directly. " +
                "They should communicate through the host activity.&ensp;<br/>";

        WebView webView = (WebView) findViewById(R.id.fragment_concept_webview);
        webView.loadData("<h1>" + title + "</h1>" + "<p style=\"text-align: justify\" \"margin:50dp\">" +
                dataFirstLine + "</p>", "text/html", "utf-8");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
