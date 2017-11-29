package sg.edu.nus.learnandroid.activity.permission;

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

import sg.edu.nus.learnandroid.activity.course.CoursePermissionActivity;
import sg.edu.nus.learnandroid.adapter.recycler_view.PermissionInfoRVAdapter;
import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.common.SimpleDividerItemDecoration;

/**
 * Created by Yongxue
 */

public class PermissionInfoActivity extends AppCompatActivity {

    private PermissionInfoRVAdapter adapter;
    private RecyclerView sharePrefInfoRV;
    private LinearLayoutManager layoutManager;
    private ImageView loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_info);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ScrollView permissionInfoSV = (ScrollView) findViewById(R.id.permission_info_SV);
                permissionInfoSV.setVisibility(View.VISIBLE);
            }
        }, 2000);

        loadingAnim = (ImageView) findViewById(R.id.permission_loading_anim);
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
        actionBarTitleTV.setText(R.string.course_permission);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CoursePermissionActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        WebView webView = (WebView) findViewById(R.id.permission_info_webview);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/permission_info.html");

        // Set up recycler view with account buttons
        List<String> permissionButtonNames = new ArrayList<>();
        permissionButtonNames.add("Introduction");

        adapter = new PermissionInfoRVAdapter(permissionButtonNames, this, this);
        sharePrefInfoRV = (RecyclerView) findViewById(R.id.permission_info_RV);
        sharePrefInfoRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        sharePrefInfoRV.setLayoutManager(layoutManager);
        sharePrefInfoRV.setAdapter(adapter);
        sharePrefInfoRV.setHasFixedSize(true);
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), CoursePermissionActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
