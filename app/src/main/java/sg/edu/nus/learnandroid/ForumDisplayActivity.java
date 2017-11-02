package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ForumDisplayActivity extends Activity {
    private ImageView IV1;
    private ImageView IV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);


        IV1 = (ImageView) findViewById(R.id.forum_display_img1);
        IV1.setImageResource(R.drawable.climb);

        IV2 = (ImageView) findViewById(R.id.forum_display_img2);
        IV2.setImageResource(R.drawable.overlay);
    }
}
