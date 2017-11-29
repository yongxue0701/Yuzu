package sg.edu.nus.learnandroid.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.activity.database.DatabaseInfoActivity;

/**
 * Created by Yongxue
 */

public class CourseDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_database);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_database);

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });

        // Start a new activity for the image view of database sqlite
        ImageView dynamicFragmentIV = (ImageView) findViewById(R.id.database_map_pin_concept_IV);
        dynamicFragmentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), DatabaseInfoActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
