package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActivity extends Activity {

    private TextView usernameTV;
    private RecyclerView userAccountRVWithAccountBtn;
    private RecyclerView userAccountRVWithContactBtn;
    private RecyclerView userAccountRVWithAckBtn;
    private LinearLayoutManager layoutManager;
    private UserAccountRVAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationMenuView bottomNavigationMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_user_account);

        // Set up bottom navigation view
        bottomNavigationView = findViewById(R.id.userAccount_bottom_navigation);

        // Change icon size of bottom navigation bar
        bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        for (int i = 0; i < bottomNavigationMenuView.getChildCount(); i++) {
            final View iconView = bottomNavigationMenuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }

        // // Set the first item as unChecked
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent myIntent;

                switch (item.getItemId()) {
                    case R.id.navigation_title_course:
                        myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        break;
                    case R.id.navigation_title_forum:
                        myIntent = new Intent(getApplicationContext(), ForumActivity.class);
                        startActivity(myIntent);
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        break;
                }
                return false;
            }
        });

        usernameTV = findViewById(R.id.userAccount_userName_TV);
        usernameTV.setText("Yongxue");

        // Set up recycler view with account buttons
        List<String> accountButtonNames = new ArrayList<>();
        accountButtonNames.add("Edit Profile");
        accountButtonNames.add("Achievements");
        accountButtonNames.add("Logout");

        adapter = new UserAccountRVAdapter(accountButtonNames, this, this);
        userAccountRVWithAccountBtn = findViewById(R.id.userAccount_account_RV);
        userAccountRVWithAccountBtn.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        userAccountRVWithAccountBtn.setLayoutManager(layoutManager);
        userAccountRVWithAccountBtn.setAdapter(adapter);
        userAccountRVWithAccountBtn.setHasFixedSize(true);

        // Set up recycler view with contact buttons
        List<String> contactButtonNames = new ArrayList<>();
        contactButtonNames.add("Feedback");
        contactButtonNames.add("Terms and Conditions");
        contactButtonNames.add("Privacy");

        adapter = new UserAccountRVAdapter(contactButtonNames, this, this);
        userAccountRVWithContactBtn = findViewById(R.id.userAccount_contact_RV);
        userAccountRVWithContactBtn.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        userAccountRVWithContactBtn.setLayoutManager(layoutManager);
        userAccountRVWithContactBtn.setAdapter(adapter);
        userAccountRVWithContactBtn.setHasFixedSize(true);

        // Set up recycler view with ack buttons
        List<String> ackButtonNames = new ArrayList<>();
        ackButtonNames.add("Acknowledgements");

        adapter = new UserAccountRVAdapter(ackButtonNames, this, this);
        userAccountRVWithAckBtn = findViewById(R.id.userAccount_ack_RV);
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        userAccountRVWithAckBtn.setLayoutManager(layoutManager);
        userAccountRVWithAckBtn.setAdapter(adapter);
        userAccountRVWithAckBtn.setHasFixedSize(true);
    }
}
