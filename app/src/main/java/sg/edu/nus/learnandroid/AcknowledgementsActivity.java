package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AcknowledgementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgements);

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
                Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        PercentRelativeLayout backgroundImageRefPRL = (PercentRelativeLayout) findViewById(R.id.background_image_references_PRL);
        backgroundImageRefPRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
