package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentQuizInfoActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_quiz_info);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button
        if (getActionBar() != null && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar_with_crossbtn);
        }

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) findViewById(R.id.action_bar_title_with_crossbtn);
        actionBarTitleTV.setText(R.string.course_fragments);

        ImageView backBtnIV = (ImageView) findViewById(R.id.action_bar_cross_with_crossbtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button submitBtn = (Button) findViewById(R.id.fragment_quiz_info_submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), FragmentQuizActivity.class);
                startActivity(myIntent);
            }
        });

        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);
        TextView scoreTV = (TextView) findViewById(R.id.fragment_quiz_info_score_content_TV);

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                int fragmentQuizPts = Integer.valueOf(mCursor.getString(mCursor.getColumnIndex("fragmentConceptQuizPts")));

                if (fragmentQuizPts == 0) {
                    scoreTV.setText(R.string.fragment_quiz_info_score_incomplete);
                    submitBtn.setText(R.string.fragment_quiz_info_submitbtn_start);
                } else if (fragmentQuizPts > 0) {
                    scoreTV.setText(fragmentQuizPts + "/2 points");
                    submitBtn.setText(R.string.fragment_quiz_info_submitbtn_retake);
                }
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();
    }
}
