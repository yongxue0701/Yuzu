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

/**
 * Created by Yongxue
 */

public class DynamicFragmentInfoActivity extends AppCompatActivity {

    private DynamicFragmentInfoRVAdapter adapter;
    private RecyclerView dynamicFragmentInfoRV;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_info);

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

        WebView webView = (WebView) findViewById(R.id.dynamic_fragment_info_background_info_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/dynamic_fragment_info.html");

        // Set up recycler view with account buttons
        List<String> dynamicFragmentButtonNames = new ArrayList<>();
        dynamicFragmentButtonNames.add("Introduction");
        dynamicFragmentButtonNames.add("Example");
        dynamicFragmentButtonNames.add("Quiz");

        adapter = new DynamicFragmentInfoRVAdapter(dynamicFragmentButtonNames, this, this);
        dynamicFragmentInfoRV = (RecyclerView) findViewById(R.id.dynamic_fragment_info_RV);
        dynamicFragmentInfoRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        dynamicFragmentInfoRV.setLayoutManager(layoutManager);
        dynamicFragmentInfoRV.setAdapter(adapter);
        dynamicFragmentInfoRV.setHasFixedSize(true);
    }
}
