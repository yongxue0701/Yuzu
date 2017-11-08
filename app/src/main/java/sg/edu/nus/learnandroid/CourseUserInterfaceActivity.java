package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Yongxue
 */

public class CourseUserInterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_user_interface);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText(R.string.course_user_interface);

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        // Start a new activity for the image view of android introduction
        ImageView androidIntroIV = (ImageView) findViewById(R.id.ui_map_pin_introduction_IV);
        androidIntroIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), AndroidIntroductionActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        // Start a new activity for the image view of layouts
        ImageView uiLayoutIV = (ImageView) findViewById(R.id.ui_map_pin_layout_IV);
        uiLayoutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), UserInterfaceLayoutActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        // Start a new activity for the image view of ui elements
        ImageView uiElementsIV = (ImageView) findViewById(R.id.ui_map_pin_elements_IV);
        uiElementsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), UserInterfaceElementsActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        // Start a new activity for the image view of ui elements
        ImageView uiOnClickIV = (ImageView) findViewById(R.id.ui_map_pin_onclick_IV);
        uiOnClickIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), UserInterfaceOnClickActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }
}
