package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActivity extends Activity {

    private TextView usernameTV;
    private RecyclerView userAccountButtonRV;
    private LinearLayoutManager layoutManager;
    private UserAccountRVAdapter adapter;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_user_account);

        usernameTV = findViewById(R.id.userAccount_userName_TV);
        usernameTV.setText("Yongxue");

        List<String> buttonNames = new ArrayList<>();
        buttonNames.add("Edit Profile");
        buttonNames.add("Logout");

        adapter = new UserAccountRVAdapter(buttonNames, this);
        userAccountButtonRV = findViewById(R.id.userAccount_RV);
        userAccountButtonRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        userAccountButtonRV.setLayoutManager(layoutManager);
        userAccountButtonRV.setAdapter(adapter);
        userAccountButtonRV.setHasFixedSize(true);

        bottomNavigationView = findViewById(R.id.userAccount_bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_title_course:
                        Intent myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        break;
                    case R.id.navigation_title_forum:

                        break;
                }
                return false;
            }
        });
    }
}
