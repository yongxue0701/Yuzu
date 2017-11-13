package sg.edu.nus.learnandroid;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yongxue
 */

public class AndroidActivityQuizActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;
    Dialog dialog;

    public static final String MY_SHAREDPREF_NAME = "AndroidActivityQuizAnsSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_activity_quiz);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_crossbtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_with_crossbtn);
        actionBarTitleTV.setText(R.string.course_intent);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_cross_with_crossbtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateCancelQuizDialog();
            }
        });

        WebView webView = (WebView) findViewById(R.id.android_activity_quiz_webview);
        AndroidActivityQuizActivity.JavaScriptInterface myJavaScriptInterface =
                new AndroidActivityQuizActivity.JavaScriptInterface(this, webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(myJavaScriptInterface, "AndroidActivityQuiz");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/android_activity_quiz.html");
    }

    @Override
    public void onBackPressed() {
        initiateCancelQuizDialog();
    }

    public class JavaScriptInterface {

        private AndroidActivityQuizActivity parentActivity;
        private WebView webView;

        public JavaScriptInterface(AndroidActivityQuizActivity androidActivityQuizActivity, WebView mWebView) {
            parentActivity = androidActivityQuizActivity;
            webView = mWebView;
        }

        @JavascriptInterface
        public void getQuizAnsFromWebView(String[] answers, int counts) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_SHAREDPREF_NAME,
                    MODE_PRIVATE).edit();

            editor.putString("q1", answers[0]);
            editor.putString("q2", answers[1]);
            editor.putString("q3", answers[2]);
            editor.putString("q4", answers[3]);
            editor.putString("q5", answers[4]);
            editor.commit();

            initiateSubmitQuizDialog(answers, counts);
        }
    }

    private void initiateCancelQuizDialog() {

        dialog = new Dialog(AndroidActivityQuizActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cancel_quiz_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        TextView title = (TextView) dialogWindow.findViewById(R.id.cancel_quiz_popup_title_TV);
        title.setText(R.string.cancel_quiz_popup_title_TV);

        Button leaveBtn = (Button) dialog.findViewById(R.id.cancel_quiz_popup_leave_btn);
        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent myIntent = new Intent(getApplicationContext(), AndroidActivityQuizInfoActivity.class);
                startActivity(myIntent);
            }
        });

        Button stayBtn = (Button) dialog.findViewById(R.id.cancel_quiz_popup_stay_btn);
        stayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void initiateSubmitQuizDialog(final String[] answers, final int counts) {

        dialog = new Dialog(AndroidActivityQuizActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        TextView title = (TextView) dialogWindow.findViewById(R.id.submit_popup_title_TV);

        if ((5 - counts) == 1) {
            title.setText(R.string.one_question_unanswered);
        } else if (counts != 5 && (5 - counts) != 1) {
            title.setText("There are " + (5 - counts) + " questions that you didn't answer. "
                    + "Are you sure you want to submit your quiz?");
        } else {
            title.setText(R.string.some_questions_unanswered);
        }

        Button cancelBtn = (Button) dialog.findViewById(R.id.submit_popup_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button yesBtn = (Button) dialog.findViewById(R.id.submit_popup_yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                calculatePoints(answers, counts);
                Intent myIntent = new Intent(getApplicationContext(), AndroidActivityQuizResultsActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void calculatePoints(String[] answers, int counts) {

        int points = 0;
        String[] correctAns = {"d", "d", "d", "b", "d"};

        for (int i = 0; i < correctAns.length; i++) {
            if (answers[i].equals(correctAns[i])) {
                points = points + 1;
            }
        }

        userAccountDB.open();
        Cursor pointsCursor = userAccountDB.getRecordByIsLogin(1);

        int totalPtsFromDB = 0;
        int finalPoints = 0;
        int androidActivityQuizPtsFromDB = 0;

        if (pointsCursor != null && pointsCursor.moveToFirst() && (pointsCursor.getCount() == 1)) {
            do {
                totalPtsFromDB = Integer.valueOf(pointsCursor.getString(pointsCursor.getColumnIndex("points")));
                androidActivityQuizPtsFromDB = Integer.valueOf(pointsCursor.getString(pointsCursor
                        .getColumnIndex("androidActivityQuizPts")));
            } while (pointsCursor.moveToNext());
        }

        pointsCursor.close();

        if (androidActivityQuizPtsFromDB == 0) {
            finalPoints = totalPtsFromDB + points;
        } else {
            finalPoints = totalPtsFromDB - androidActivityQuizPtsFromDB + points;
        }

        userAccountDB.updatePointsByIsLogin(1, finalPoints);
        userAccountDB.updateAndroidActivityQuizPtsByIsLogin(1, points);
        userAccountDB.close();
    }
}