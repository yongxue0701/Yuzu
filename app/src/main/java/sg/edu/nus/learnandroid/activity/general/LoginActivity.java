package sg.edu.nus.learnandroid.activity.general;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.activity.course.CourseMapActivity;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class LoginActivity extends Activity {

    UserAccountDB userAccountDB;

    private EditText usernameET;
    private EditText pwdET;
    private String username;
    private String pwd;

    public static final String MY_SHAREDPREF_NAME = "UserInforSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        userAccountDB = new UserAccountDB(this);

        Button loginBtn = (Button) findViewById(R.id.login_login_Btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                usernameET = findViewById(R.id.login_username_ET);
                pwdET = findViewById(R.id.login_pwd_ET);

                username = usernameET.getText().toString();
                pwd = pwdET.getText().toString();

                if (username == null || username.isEmpty()) {
                    usernameET.setError("Please enter your username!");
                }

                if (pwd == null || pwd.isEmpty()) {
                    pwdET.setError("Please enter your password!");
                }

                String username = usernameET.getText().toString();
                String pwd = pwdET.getText().toString();

                userAccountDB.open();
                Cursor mCursor = userAccountDB.getRecordByUsername(username);

                if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
                    do {
                        String pwdFromDB = mCursor.getString(mCursor.getColumnIndex("password"));

                        if (pwdFromDB.equals(pwd)) {
                            userAccountDB.updateIsLoginByUsername(username, true);

                            SharedPreferences.Editor editor = getSharedPreferences(MY_SHAREDPREF_NAME,
                                    MODE_PRIVATE).edit();

                            editor.putString("username", mCursor.getString(mCursor.getColumnIndex("username")));
                            editor.putString("password", mCursor.getString(mCursor.getColumnIndex("password")));
                            editor.putString("email", mCursor.getString(mCursor.getColumnIndex("email")));
                            editor.putString("gender", mCursor.getString(mCursor.getColumnIndex("gender")));
                            editor.commit();

                            Intent myIntent = new Intent(getApplicationContext(), CourseMapActivity.class);
                            startActivity(myIntent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        } else {
                            Toast.makeText(getApplicationContext(), "Your password is incorrect, please try again!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } while (mCursor.moveToNext());
                } else {
                    Toast.makeText(getApplicationContext(), "Your username is invalid, please try again!",
                            Toast.LENGTH_SHORT).show();
                }

                mCursor.close();
                userAccountDB.close();
            }
        });

        LinearLayout registerLL = findViewById(R.id.login_signUp_LL);
        registerLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
