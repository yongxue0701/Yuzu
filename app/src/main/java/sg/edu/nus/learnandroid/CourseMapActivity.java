package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

public class CourseMapActivity extends Activity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_course_map);

//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.courseMap_bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.navigation_title_account:
//                        Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
//                        startActivity(myIntent);
//                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//
//                        break;
//                    case R.id.navigation_title_forum:
//
//                        break;
//                }
//                return false;
//            }
//        });
    }
}
