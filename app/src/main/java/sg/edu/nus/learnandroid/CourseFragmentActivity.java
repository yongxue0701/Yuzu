package sg.edu.nus.learnandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class CourseFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_fragment);

//        centerTitle();

        // Set up action bar with back button
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setTitle("Fragments        ");

        // Set up color of back arrow icon
//        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        // Set up custom action bar with back button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_without_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_without_donebtn);
        actionBarTitleTV.setText("Fragments");

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_without_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // Start a new activity for the first image view
        ImageView mapPinOneIV = (ImageView) findViewById(R.id.fragment_map_pin_1_IV);
        mapPinOneIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), FragmentIntroductionActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return true;
//    }

    // To center the title on action bar
    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
}
