package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForumActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
    }

    public void onClick_GoToForumActivity(View view) {
        Intent myIntent = new Intent(this, ForumActivity.class);
        startActivity(myIntent);
    }
}
