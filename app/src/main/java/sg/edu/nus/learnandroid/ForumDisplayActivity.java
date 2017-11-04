package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class ForumDisplayActivity extends Activity {
    private ImageView IV1;
    private ImageView IV2;
    private TextView TV2;
    private TextView TV3;
    private TextView TV4;
    private TextView TV5;
    private TextView TV6;
    private TextView TV7;
    private TextView TV8;
    private TextView TV9;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);


        IV1 = (ImageView) findViewById(R.id.forum_display_img1);
        IV1.setImageResource(R.drawable.climb);

        IV2 = (ImageView) findViewById(R.id.forum_display_img2);
        IV2.setImageResource(R.drawable.overlay);

        TV3 = (TextView) findViewById(R.id.forum_display_TV3);
        TV3.setMovementMethod(LinkMovementMethod.getInstance());

        TV4 = (TextView) findViewById(R.id.forum_display_TV4);
        TV4.setMovementMethod(LinkMovementMethod.getInstance());

        TV5 = (TextView) findViewById(R.id.forum_display_TV5);
        TV5.setMovementMethod(LinkMovementMethod.getInstance());

        TV7 = (TextView) findViewById(R.id.forum_display_TV7);
        TV7.setMovementMethod(LinkMovementMethod.getInstance());

        TV8 = (TextView) findViewById(R.id.forum_display_TV8);
        TV8.setMovementMethod(LinkMovementMethod.getInstance());

        TV9 = (TextView) findViewById(R.id.forum_display_TV9);
        TV9.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
