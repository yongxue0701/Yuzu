package sg.edu.nus.learnandroid.activity.general;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class AchievementsActivity extends AppCompatActivity {

    UserAccountDB userAccountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        userAccountDB = new UserAccountDB(this);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Set up custom action bar with back button and done button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.action_bar_title_achievements);

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

        String points = "";
        String uiCoursePassed = "";
        String intentCoursePassed = "";
        String dataPassingCoursePassed = "";
        String fragmentCoursePassed = "";
        String databaseCoursePassed = "";

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                points = mCursor.getString(mCursor.getColumnIndex("points"));
                uiCoursePassed = mCursor.getString(mCursor.getColumnIndex("uiCoursePassed"));
                intentCoursePassed = mCursor.getString(mCursor.getColumnIndex("intentCoursePassed"));
                dataPassingCoursePassed = mCursor.getString(mCursor.getColumnIndex("dataPassingCoursePassed"));
                fragmentCoursePassed = mCursor.getString(mCursor.getColumnIndex("fragmentCoursePassed"));
                databaseCoursePassed = mCursor.getString(mCursor.getColumnIndex("databaseCoursePassed"));
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();

        TextView yourScore = (TextView) findViewById(R.id.achievements_points_content_TV);
        yourScore.setText(points + "/25 points");

        TextView userInterface = (TextView) findViewById(R.id.achievements_ui_content_TV);
        if (Integer.valueOf(uiCoursePassed) == 1) {
            userInterface.setText(R.string.achievements_passed);
        } else {
            userInterface.setText(R.string.achievements_incomplete);
        }

        TextView intent = (TextView) findViewById(R.id.achievements_intent_content_TV);
        if (Integer.valueOf(intentCoursePassed) == 2) {
            intent.setText(R.string.achievements_passed);
        } else {
            intent.setText(R.string.achievements_incomplete);
        }

        TextView dataPassing = (TextView) findViewById(R.id.achievements_data_passing_content_TV);
        if (Integer.valueOf(dataPassingCoursePassed) == 1) {
            dataPassing.setText(R.string.achievements_passed);
        } else {
            dataPassing.setText(R.string.achievements_incomplete);
        }

        TextView fragment = (TextView) findViewById(R.id.achievements_fragment_content_TV);
        if (Integer.valueOf(fragmentCoursePassed) == 3) {
            fragment.setText(R.string.achievements_passed);
        } else {
            fragment.setText(R.string.achievements_incomplete);
        }

        TextView database = (TextView) findViewById(R.id.achievements_database_content_TV);
        if (Integer.valueOf(databaseCoursePassed) == 1) {
            database.setText(R.string.achievements_passed);
        } else {
            database.setText(R.string.achievements_incomplete);
        }

        TextView permission = (TextView) findViewById(R.id.achievements_permission_content_TV);
        permission.setText(R.string.achievements_for_your_information);
    }
}
