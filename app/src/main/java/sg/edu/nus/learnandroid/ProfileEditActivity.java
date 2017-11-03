package sg.edu.nus.learnandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfileEditActivity extends AppCompatActivity {

    private ProfileEditRVAdapter adapter;
    private RecyclerView profileEditRV;
    private LinearLayoutManager layoutManager;

    UserAccountDB userAccountDB;
    public static final String MY_SHAREDPREF_NAME = "UserInforSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_profile_edit);

        userAccountDB = new UserAccountDB(this);

        // Set up custom action bar with back button and done button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_with_donebtn);
        actionBarTitleTV.setText(R.string.action_bar_title_profile_edit);

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_with_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateDialog(view);
            }
        });

        // Set up recycler view
        List<ItemView> profileEditItemView = new ArrayList<>();
        profileEditItemView.add(new ItemView("Photo", "Img"));
        profileEditItemView.add(new ItemView("Username", "ET"));
        profileEditItemView.add(new ItemView("Email", "ET"));
        profileEditItemView.add(new ItemView("Gender", "BtnWithText"));

        adapter = new ProfileEditRVAdapter(profileEditItemView, this, this);
        profileEditRV = (RecyclerView) findViewById(R.id.profile_edit_RV);
        profileEditRV.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        profileEditRV.setLayoutManager(layoutManager);
        profileEditRV.setAdapter(adapter);
        profileEditRV.setHasFixedSize(true);

        // Set up recycler view for change password
        List<ItemView> profileEditChangePasswordItemView = new ArrayList<>();
        profileEditChangePasswordItemView.add(new ItemView("Change Password", "Btn"));

        adapter = new ProfileEditRVAdapter(profileEditChangePasswordItemView, this, this);
        profileEditRV = (RecyclerView) findViewById(R.id.profile_edit_change_password_RV);
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        profileEditRV.setLayoutManager(layoutManager);
        profileEditRV.setAdapter(adapter);
        profileEditRV.setHasFixedSize(true);

        // Save the updated information into database
        TextView doneBtnTV = (TextView) view.findViewById(R.id.action_bar_done_with_donebtn);
        doneBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences(MY_SHAREDPREF_NAME,
                        MODE_PRIVATE);

                userAccountDB.open();
                Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

                if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
                    do {

                        String username = prefs.getString("username", "default value");
                        String email = prefs.getString("email", "default value");
                        String gender = prefs.getString("gender", "default value");

                        userAccountDB.updateSomeRecordsByUsername(username, email, gender);

                    } while (mCursor.moveToNext());
                }

                mCursor.close();
                userAccountDB.close();

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.profile_edit_success_toast, (ViewGroup)
                        findViewById(R.id.profile_edit_success_toast_PRL));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, -200);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);

                toast.show();

                finish();
            }
        });
    }

    private void initiateDialog(View view) {

        final Dialog dialog = new Dialog(ProfileEditActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cancel_btn_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        Button cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn_popup_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button yesBtn = (Button) dialog.findViewById(R.id.cancel_btn_popup_yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discardChanges();
                finish();
            }
        });
    }

    private void discardChanges() {
        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {

                SharedPreferences.Editor editor = getSharedPreferences(MY_SHAREDPREF_NAME,
                        MODE_PRIVATE).edit();

                editor.putString("username", mCursor.getString(mCursor.getColumnIndex("username")));
                editor.putString("password", mCursor.getString(mCursor.getColumnIndex("password")));
                editor.putString("email", mCursor.getString(mCursor.getColumnIndex("email")));
                editor.putString("gender", mCursor.getString(mCursor.getColumnIndex("gender")));
                editor.commit();

            } while (mCursor.moveToNext());
        }

        mCursor.close();
        userAccountDB.close();
    }
}
