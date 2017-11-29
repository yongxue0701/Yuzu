package sg.edu.nus.learnandroid.activity.general;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText currentPasswordET;
    private EditText newPasswordET;
    private EditText confirmNewPasswordET;

    UserAccountDB userAccountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

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
                initiateDialog();
            }
        });

        TextView doneBtn = (TextView) findViewById(R.id.action_bar_done_with_donebtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
            }
        });
    }

    @Override
    public void onBackPressed() {
        initiateDialog();
    }

    private void initiateDialog() {

        final Dialog dialog = new Dialog(ChangePasswordActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        TextView title = (TextView) dialogWindow.findViewById(R.id.submit_popup_title_TV);
        title.setText(R.string.discard_change_password_popup_title_TV);

        Button cancelBtn = (Button) dialog.findViewById(R.id.submit_popup_cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button yesBtn = (Button) dialog.findViewById(R.id.submit_popup_yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent myIntent = new Intent(getApplicationContext(), ProfileEditActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void updatePassword() {

        currentPasswordET = (EditText) findViewById(R.id.current_password_ET);
        newPasswordET = (EditText) findViewById(R.id.new_password_ET);
        confirmNewPasswordET = (EditText) findViewById(R.id.confirm_new_password_ET);

        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

        boolean allDone = true;

        if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
            do {
                String currentPassword = mCursor.getString(mCursor.getColumnIndex("password"));
                if (!currentPassword.equals(currentPasswordET.getText().toString())) {
                    allDone = false;
                    currentPasswordET.setError("Your current password is incorrect!");
                }
                if (!newPasswordET.getText().toString().equals(confirmNewPasswordET.getText().toString())) {
                    allDone = false;
                    confirmNewPasswordET.setError("Passwords do not match!");
                }
                if (allDone) {
                    userAccountDB.updatePasswordByIsLogin(1, newPasswordET.getText().toString());

                    LayoutInflater inflater = (LayoutInflater)
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.successful_message_toast, (ViewGroup)
                            findViewById(R.id.successful_message_toast_PRL));

                    TextView successContent = (TextView) layout.findViewById(R.id.successful_message_toast_content_TV);
                    successContent.setText(R.string.update_password_success_toast_content_TV);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, -200);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);

                    toast.show();

                    Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }

            } while (mCursor.moveToNext());

            mCursor.close();
            userAccountDB.close();
        }
    }
}
