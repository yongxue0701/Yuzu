package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class ForumDisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);

        ImageView climbIV = (ImageView) findViewById(R.id.forum_display_climb_IV);
        climbIV.setImageResource(R.drawable.climb);

        ImageView overlayIV = (ImageView) findViewById(R.id.forum_display_overlay_IV);
        overlayIV.setImageResource(R.drawable.overlay);

        TextView developerTV = (TextView) findViewById(R.id.forum_display_developer_TV);
        developerTV.setMovementMethod(LinkMovementMethod.getInstance());

        TextView androidliftTV = (TextView) findViewById(R.id.forum_display_androidlift_TV);
        androidliftTV.setMovementMethod(LinkMovementMethod.getInstance());

        TextView vogellaTV = (TextView) findViewById(R.id.forum_display_vogella_TV);
        vogellaTV.setMovementMethod(LinkMovementMethod.getInstance());

        TextView kotlinTV = (TextView) findViewById(R.id.forum_display_kotlin_TV);
        kotlinTV.setMovementMethod(LinkMovementMethod.getInstance());

        TextView googleTV = (TextView) findViewById(R.id.forum_display_google_TV);
        googleTV.setMovementMethod(LinkMovementMethod.getInstance());

        TextView appnewsIV = (TextView) findViewById(R.id.forum_display_appnews_TV);
        appnewsIV.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
