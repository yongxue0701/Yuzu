package sg.edu.nus.learnandroid.activity.course;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.activity.general.ExploreActivity;
import sg.edu.nus.learnandroid.activity.general.UserAccountActivity;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class CourseMapActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private BottomNavigationMenuView bottomNavigationMenuView;
    private ImageView mapPinTwoIV;
    private ImageView mapPinThreeIV;
    private ImageView mapPinFourIV;
    private ImageView mapPinFiveIV;
    private ImageView mapPinSixIV;

    UserAccountDB userAccountDB;
    int uiCoursePassedFromDB;
    int intentCoursePassedFromDB;
    int dataPassingCoursePassedFromDB;
    int fragmentCoursePassedFromDB;
    int databaseCoursePassedFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_map);

        userAccountDB = new UserAccountDB(this);
        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                int newUser = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("newUser")));
                uiCoursePassedFromDB = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("uiCoursePassed")));
                intentCoursePassedFromDB = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("intentCoursePassed")));
                dataPassingCoursePassedFromDB = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("dataPassingCoursePassed")));
                fragmentCoursePassedFromDB = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("fragmentCoursePassed")));
                databaseCoursePassedFromDB = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("databaseCoursePassed")));

                if (newUser == 1) {
                    initiateBeginnerTipOneDialog();
                }
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();

        // Set up bottom navigation view
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.courseMap_bottom_navigation);

        // Change icon size of bottom navigation bar
        bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
            final View iconView = bottomNavigationMenuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }

        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent myIntent;

                switch (item.getItemId()) {
                    case R.id.navigation_title_account:
                        myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        break;
                    case R.id.navigation_title_explore:
                        myIntent = new Intent(getApplicationContext(), ExploreActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        break;
                }
                return false;
            }
        });

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_only_has_title);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_only_has_title_title);
        actionBarTitleTV.setText(R.string.action_bar_title_course_map);

        ImageView mapPinOneIV = (ImageView) findViewById(R.id.course_map_pin_1_IV);
        mapPinOneIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CourseUserInterfaceActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        mapPinTwoIV = (ImageView) findViewById(R.id.course_map_pin_2_IV);
        mapPinTwoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uiCoursePassedFromDB == 1) {
                    Intent myIntent = new Intent(getApplicationContext(), CourseIntentActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else if (uiCoursePassedFromDB == 0) {
                    initiateLockedCourseDialog();
                }
            }
        });

        mapPinThreeIV = (ImageView) findViewById(R.id.course_map_pin_3_IV);
        mapPinThreeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (intentCoursePassedFromDB == 2) {
                    Intent myIntent = new Intent(getApplicationContext(), CoursePassingDataActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else {
                    initiateLockedCourseDialog();
                }
            }
        });

        mapPinFourIV = (ImageView) findViewById(R.id.course_map_pin_4_IV);
        mapPinFourIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dataPassingCoursePassedFromDB == 1) {
                    Intent myIntent = new Intent(getApplicationContext(), CourseFragmentActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else {
                    initiateLockedCourseDialog();
                }
            }
        });

        mapPinSixIV = (ImageView) findViewById(R.id.course_map_pin_6_IV);
        mapPinSixIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (fragmentCoursePassedFromDB == 3) {
                    Intent myIntent = new Intent(getApplicationContext(), CourseDatabaseActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else {
                    initiateLockedCourseDialog();
                }
            }
        });

        mapPinFiveIV = (ImageView) findViewById(R.id.course_map_pin_5_IV);
        mapPinFiveIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (databaseCoursePassedFromDB == 1) {
                    Intent myIntent = new Intent(getApplicationContext(), CoursePermissionActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                } else {
                    initiateLockedCourseDialog();
                }
            }
        });

        if (uiCoursePassedFromDB == 1) {
            mapPinTwoIV.setImageResource(R.drawable.ic_map_pin_red_1);
        }

        if (intentCoursePassedFromDB == 2) {
            mapPinThreeIV.setImageResource(R.drawable.ic_map_pin_red_1);
        }

        if (dataPassingCoursePassedFromDB == 1) {
            mapPinFourIV.setImageResource(R.drawable.ic_map_pin_red_1);
        }

        if (fragmentCoursePassedFromDB == 3) {
            mapPinSixIV.setImageResource(R.drawable.ic_map_pin_red_1);
        }

        if (databaseCoursePassedFromDB == 1) {
            mapPinFiveIV.setImageResource(R.drawable.ic_map_pin_red_1);
        }
    }

    @Override
    public void onBackPressed() {

    }

    private void initiateLockedCourseDialog() {

        final Dialog dialog = new Dialog(CourseMapActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beginner_tips_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        WebView webView = (WebView) dialogWindow.findViewById(R.id.beginner_tips_content_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/locked_course.html");

        ImageView nextIV = (ImageView) dialogWindow.findViewById(R.id.beginner_tips_arrow_to_right_IV);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void initiateBeginnerTipOneDialog() {

        final Dialog dialog = new Dialog(CourseMapActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beginner_tips_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        WebView webView = (WebView) dialogWindow.findViewById(R.id.beginner_tips_content_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/beginner_tips_one.html");

        ImageView nextIV = (ImageView) dialogWindow.findViewById(R.id.beginner_tips_arrow_to_right_IV);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                initiateBeginnerTipTwoDialog();
            }
        });
    }

    private void initiateBeginnerTipTwoDialog() {

        final Dialog dialog = new Dialog(CourseMapActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beginner_tips_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        WebView webView = (WebView) dialogWindow.findViewById(R.id.beginner_tips_content_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/beginner_tips_two.html");

        ImageView nextIV = (ImageView) dialogWindow.findViewById(R.id.beginner_tips_arrow_to_right_IV);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                initiateBeginnerTipThreeDialog();
            }
        });
    }

    private void initiateBeginnerTipThreeDialog() {

        final Dialog dialog = new Dialog(CourseMapActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beginner_tips_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        WebView webView = (WebView) dialogWindow.findViewById(R.id.beginner_tips_content_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/beginner_tips_three.html");

        ImageView nextIV = (ImageView) dialogWindow.findViewById(R.id.beginner_tips_arrow_to_right_IV);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                initiateBeginnerTipFourDialog();
            }
        });
    }

    private void initiateBeginnerTipFourDialog() {

        final Dialog dialog = new Dialog(CourseMapActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.beginner_tips_popup);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        WebView webView = (WebView) dialogWindow.findViewById(R.id.beginner_tips_content_webview);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/beginner_tips_four.html");

        ImageView nextIV = (ImageView) dialogWindow.findViewById(R.id.beginner_tips_arrow_to_right_IV);
        nextIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                userAccountDB.open();
                Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

                if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
                    do {
                        userAccountDB.updateUserStatusByIsLogin(1, 0);
                    } while (mCursor.moveToNext());
                }
                mCursor.close();
                userAccountDB.close();
            }
        });
    }
}
