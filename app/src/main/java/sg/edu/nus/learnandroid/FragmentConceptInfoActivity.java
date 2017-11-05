package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentConceptInfoActivity extends AppCompatActivity {

    private FragmentConceptInfoRVAdapter adapter;
    private RecyclerView fragmentInfoRV;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_concept_info);

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
                Intent myIntent = new Intent(getApplicationContext(), CourseFragmentActivity.class);
                startActivity(myIntent);
            }
        });


        WebView webView = (WebView) findViewById(R.id.fragment_info_background_info_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/fragment_info.html");

        // Set up recycler view with account buttons
        List<String> accountButtonNames = new ArrayList<>();
        accountButtonNames.add("Introduction of Fragments");
        accountButtonNames.add("Fragment Life Cycle");
        accountButtonNames.add("Types of Fragments");
        accountButtonNames.add("Quiz");

        adapter = new FragmentConceptInfoRVAdapter(accountButtonNames, this, this);
        fragmentInfoRV = (RecyclerView) findViewById(R.id.fragment_info_RV);
        fragmentInfoRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        fragmentInfoRV.setLayoutManager(layoutManager);
        fragmentInfoRV.setAdapter(adapter);
        fragmentInfoRV.setHasFixedSize(true);
    }
}
