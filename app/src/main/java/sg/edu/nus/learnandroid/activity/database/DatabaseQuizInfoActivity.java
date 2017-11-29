package sg.edu.nus.learnandroid.activity.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class DatabaseQuizInfoActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_quiz_info);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_database);

        // Set up the back button and title on action bar
        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DatabaseInfoActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        // Set onclick function for submit button
        Button submitBtn = (Button) findViewById(R.id.database_quiz_info_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DatabaseQuizActivity.class);
                startActivity(myIntent);
            }
        });

        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);
        TextView scoreTV = (TextView) findViewById(R.id.database_quiz_info_score_content_TV);

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                int databaseQuizPts = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("databaseQuizPts")));

                if (databaseQuizPts == 1 || databaseQuizPts == 0) {
                    scoreTV.setText(databaseQuizPts + "/2 point");

                } else if (databaseQuizPts > 0) {
                    scoreTV.setText(databaseQuizPts + "/2 points");
                }
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();
    }

    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), DatabaseInfoActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
