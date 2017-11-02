package sg.edu.nus.learnandroid;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

public class FragmentQuizActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_quiz);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText("Fragments");

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebView webView = (WebView) findViewById(R.id.fragment_quiz_webview);
        JavaScriptInterface myJavaScriptInterface = new JavaScriptInterface(this, webView);

        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.addJavascriptInterface(myJavaScriptInterface, "MyHandler");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/www/fragment_quiz.html");
    }

    public class JavaScriptInterface {

        private FragmentQuizActivity parentActivity;
        private WebView webView;

        public JavaScriptInterface(FragmentQuizActivity fragmentQuizActivity, WebView mWebView) {
            parentActivity = fragmentQuizActivity;
            webView = mWebView;
        }

        @JavascriptInterface
        public void getQuizAnsFromWebView(String[] answers, int counts) {
            initiateSubmitQuizDialog(answers, counts);
        }
    }

    private void initiateSubmitQuizDialog(final String[] answers, final int counts) {

        dialog = new Dialog(FragmentQuizActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_quiz_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView title = (TextView) dialogWindow.findViewById(R.id.submit_quiz_popup_title_TV);

        if ((2 - counts) == 1) {
            title.setText(R.string.one_question_unanswered);
        } else if (counts != 2 && (2 - counts) != 1) {
            title.setText("There are " + (2 - counts) + " questions that you didn't answer. "
                    + "Are you sure you want to submit your quiz?");
        } else {
            title.setText(R.string.some_questions_unanswered);
        }

        dialog.show();

        Button cancelBtn = (Button) dialog.findViewById(R.id.submit_quiz_popup_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button yesBtn = (Button) dialog.findViewById(R.id.submit_quiz_popup_yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                calculatePoints(answers, counts);
                Intent myIntent = new Intent(getApplicationContext(), FragmentQuizResultsActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void calculatePoints(String[] answers, int counts) {

        int points = 0;
        String[] correctAns = {"c", "c"};

        for (int i = 0; i < correctAns.length; i++) {
            if (answers[i].equals(correctAns[i])) {
                points = points + 1;
            }
        }

        userAccountDB.open();
        Cursor pointsCursor = userAccountDB.getRecordByIsLogin(1);

        int totalPtsFromDB = 0;
        int finalPoints = 0;
        int fragmentConceptQuizPtsFromDB = 0;

        if (pointsCursor != null && pointsCursor.moveToFirst() && (pointsCursor.getCount() == 1)) {
            do {
                totalPtsFromDB = Integer.valueOf(pointsCursor.getString(pointsCursor.getColumnIndex("points")));
                fragmentConceptQuizPtsFromDB = Integer.valueOf(pointsCursor.getString(pointsCursor.getColumnIndex("fragmentConceptQuizPts")));
            } while (pointsCursor.moveToNext());
        }

        pointsCursor.close();

        if (fragmentConceptQuizPtsFromDB == 0) {
            finalPoints = totalPtsFromDB + points;
        } else {
            finalPoints = totalPtsFromDB - fragmentConceptQuizPtsFromDB + points;
        }

        Toast.makeText(getApplicationContext(), "finalPoints " + finalPoints + "quizPoints " + points, Toast.LENGTH_LONG).show();
        userAccountDB.updatePointsByIsLogin(1, finalPoints);
        userAccountDB.updateFragmentConceptQuizPtsByIsLogin(1, points);
        userAccountDB.close();
    }
}
