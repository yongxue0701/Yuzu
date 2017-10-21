package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditActivity extends Activity {

    private ProfileEditRVAdapter adapter;
    private RecyclerView profileEditRV;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_profile_edit);

        // Set up recycler view
        List<ItemView> profileEditItemView = new ArrayList<>();
        profileEditItemView.add(new ItemView("Photo", "Img"));
        profileEditItemView.add(new ItemView("Username", "ET"));
        profileEditItemView.add(new ItemView("Email", "ET"));
        profileEditItemView.add(new ItemView("Gender", "Btn"));
        profileEditItemView.add(new ItemView("Birthday", "Btn"));

        adapter = new ProfileEditRVAdapter(profileEditItemView, this, this);
        profileEditRV = findViewById(R.id.profile_edit_RV);
        profileEditRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        profileEditRV.setLayoutManager(layoutManager);
        profileEditRV.setAdapter(adapter);
        profileEditRV.setHasFixedSize(true);

        // Set up recycler view for change password
        List<ItemView> profileEditChangePasswordItemView = new ArrayList<>();
        profileEditChangePasswordItemView.add(new ItemView("Change Password", "Btn"));

        adapter = new ProfileEditRVAdapter(profileEditChangePasswordItemView, this, this);
        profileEditRV = findViewById(R.id.profile_edit_change_password_RV);
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        profileEditRV.setLayoutManager(layoutManager);
        profileEditRV.setAdapter(adapter);
        profileEditRV.setHasFixedSize(true);
    }
}
