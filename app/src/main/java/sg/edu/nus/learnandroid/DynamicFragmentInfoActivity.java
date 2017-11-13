package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
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
    private ImageView loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_info);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ScrollView dynamicFragmentInfoSV = (ScrollView) findViewById(R.id.dynamic_fragment_info_SV);
                dynamicFragmentInfoSV.setVisibility(View.VISIBLE);
            }
        }, 2000);

        loadingAnim = (ImageView) findViewById(R.id.dynamic_fragment_loading_anim);
        loadingAnim.setBackgroundResource(R.drawable.spinning_globe_grey);
        AnimationDrawable frameAnimation = (AnimationDrawable) loadingAnim.getBackground();
        frameAnimation.start();

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
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        WebView webView = (WebView) findViewById(R.id.dynamic_fragment_info_webview);
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

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), CourseFragmentActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
