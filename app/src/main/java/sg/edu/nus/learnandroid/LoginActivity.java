package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LoginActivity extends Activity {

    UserAccountDB userAccountDB;

    private EditText usernameET;
    private EditText pwdET;

    public static final String MY_SHAREDPREF_NAME = "UserInforSharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_login);

        userAccountDB = new UserAccountDB(this);

        Button loginBtn = (Button) findViewById(R.id.login_login_Btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                usernameET = findViewById(R.id.login_username_ET);
                pwdET = findViewById(R.id.login_pwd_ET);

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
                        }
                    } while (mCursor.moveToNext());
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

        LinearLayout needHelpLL = findViewById(R.id.login_needHelp_LL);
        needHelpLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), NeedHelpActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
    }
}
