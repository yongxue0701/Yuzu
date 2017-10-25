package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_GoToLoginActivity(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);
    }

    public void onClick_GoToRegisterActivity(View view) {
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }

    public void onClick_GoToUserAccountActivity(View view) {
        Intent myIntent = new Intent(this, UserAccountActivity.class);
        startActivity(myIntent);
    }

    public void onClick_GoToCourseMapActivity(View view) {
        Intent myIntent = new Intent(this, CourseMapActivity.class);
        startActivity(myIntent);
    }

}
