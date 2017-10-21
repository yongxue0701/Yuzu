package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;

public class ProfileEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_profile_edit);
    }
}
