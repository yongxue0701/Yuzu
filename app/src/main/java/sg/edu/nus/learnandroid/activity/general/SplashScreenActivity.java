package sg.edu.nus.learnandroid.activity.general;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.activity.course.CourseMapActivity;
import sg.edu.nus.learnandroid.database.UserAccountDB;

public class SplashScreenActivity extends Activity {

    UserAccountDB userAccountDB;

    private static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userAccountDB = new UserAccountDB(this);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                userAccountDB.open();
                Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

                if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
                    do {
                        Intent i = new Intent(getApplicationContext(), CourseMapActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    } while (mCursor.moveToNext());
                } else {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }

                mCursor.close();
                userAccountDB.close();

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
