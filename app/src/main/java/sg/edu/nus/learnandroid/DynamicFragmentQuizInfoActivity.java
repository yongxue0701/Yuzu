package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yongxue
 */

public class DynamicFragmentQuizInfoActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment_quiz_info);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_fragments);

        // Set up the back button and title on action bar
        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DynamicFragmentInfoActivity.class);
                startActivity(myIntent);
            }
        });

        // Set onclick function for submit button
        Button submitBtn = (Button) findViewById(R.id.dynamic_fragment_quiz_info_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DynamicFragmentQuizActivity.class);
                startActivity(myIntent);
            }
        });

        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);
        TextView scoreTV = (TextView) findViewById(R.id.dynamic_fragment_quiz_info_score_content_TV);

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                int dynamicFragmentQuizPts = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("dynamicFragmentQuizPts")));

                if (dynamicFragmentQuizPts == 1 || dynamicFragmentQuizPts == 0) {
                    scoreTV.setText(dynamicFragmentQuizPts + "/3 point");

                } else if (dynamicFragmentQuizPts > 0) {
                    scoreTV.setText(dynamicFragmentQuizPts + "/3 points");
                }
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();
    }
}
