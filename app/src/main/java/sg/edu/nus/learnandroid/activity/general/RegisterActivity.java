package sg.edu.nus.learnandroid.activity.general;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import sg.edu.nus.learnandroid.R;
import sg.edu.nus.learnandroid.database.UserAccountDB;

/**
 * Created by Yongxue
 */

public class RegisterActivity extends Activity {

    UserAccountDB userAccountDB;

    private EditText usernameET;
    private EditText pwdET;
    private EditText confirmPwdET;
    private EditText emailET;
    private RadioGroup genderRG;
    private RadioButton genderRB;

    private String username;
    private String pwd;
    private String confirmPwd;
    private String email;
    private String gender;
    private boolean isLogin;
    private boolean newUser;
    private int uiCoursePassed;
    private int intentCoursePassed;
    private int dataPassingCoursePassed;
    private int fragmentCoursePassed;
    private int points;
    private int fragmentConceptQuizPts;
    private int staticFragmentQuizPts;
    private int dynamicFragmentQuizPts;
    private int androidActivityQuizPts;
    private int userInterfaceQuizPts;
    private int intentQuizPts;
    private int broadcastQuizPts;
    private int databaseQuizPts;
    private int databaseCoursePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        setContentView(R.layout.activity_register);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        userAccountDB = new UserAccountDB(this);

        RadioGroup genderRadioGroup = findViewById(R.id.register_gender_RG);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genderRG = findViewById(R.id.register_gender_RG);
                genderRB = findViewById(genderRG.getCheckedRadioButtonId());
                gender = genderRB.getText().toString();
            }
        });

        Button confirmBtn = findViewById(R.id.register_confirm_Btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                boolean allDone = true;

                usernameET = findViewById(R.id.register_username_ET);
                pwdET = findViewById(R.id.register_pwd_ET);
                confirmPwdET = findViewById(R.id.register_confirmPwd_ET);
                emailET = findViewById(R.id.register_email_ET);

                username = usernameET.getText().toString();
                pwd = pwdET.getText().toString();
                confirmPwd = confirmPwdET.getText().toString();
                email = emailET.getText().toString();
                isLogin = false;
                newUser = true;
                uiCoursePassed = 0;
                intentCoursePassed = 0;
                dataPassingCoursePassed = 0;
                fragmentCoursePassed = 0;
                points = 0;
                fragmentConceptQuizPts = 0;
                staticFragmentQuizPts = 0;
                dynamicFragmentQuizPts = 0;
                androidActivityQuizPts = 0;
                userInterfaceQuizPts = 0;
                intentQuizPts = 0;
                broadcastQuizPts = 0;
                databaseQuizPts = 0;
                databaseCoursePassed = 0;

                genderRG = findViewById(R.id.register_gender_RG);

                if (username.isEmpty() || username == null) {
                    usernameET.setError("Please enter your username!");
                    allDone = false;
                }
                if (pwd.isEmpty() || pwd == null) {
                    pwdET.setError("Please enter your password!");
                    allDone = false;
                }
                if (email.isEmpty() || email == null) {
                    emailET.setError("Please enter your email address!");
                    allDone = false;
                }
                if (genderRG.getCheckedRadioButtonId() <= 0) {
                    Toast.makeText(getApplicationContext(), "Please select your gender!", Toast.LENGTH_SHORT).show();
                    allDone = false;
                }
                if (!pwd.equals(confirmPwd)) {
                    confirmPwdET.setError("Password Mismatch!");
                    allDone = false;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Email address is invalid!", Toast.LENGTH_SHORT).show();
                    allDone = false;
                }
                if (checkUserDuplication(username)) {
                    Toast.makeText(getApplicationContext(), "The username is occupied!", Toast.LENGTH_SHORT).show();
                    allDone = false;
                }
                if (pwd.length() < 8) {
                    pwdET.setError("You must have 8 characters in your password");
                    allDone = false;
                }

                if (allDone) {
                    userAccountDB.open();
                    userAccountDB.insertRecord(username, pwd, email, gender, isLogin, newUser,
                            uiCoursePassed, intentCoursePassed, dataPassingCoursePassed, fragmentCoursePassed, databaseCoursePassed,
                            String.valueOf(points), String.valueOf(userInterfaceQuizPts), String.valueOf(androidActivityQuizPts),
                            String.valueOf(intentQuizPts), String.valueOf(broadcastQuizPts), String.valueOf(fragmentConceptQuizPts),
                            String.valueOf(staticFragmentQuizPts), String.valueOf(dynamicFragmentQuizPts),
                            String.valueOf(databaseQuizPts));
                    userAccountDB.close();

                    Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            }
        });

        Button backBtn = findViewById(R.id.register_back_Btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    private boolean checkUserDuplication(String username) {
        userAccountDB.open();
        Cursor mCursor = userAccountDB.getRecordByUsername(username);

        if (mCursor.getCount() == 0) {
            mCursor.close();
            userAccountDB.close();

            return false;
        } else {
            mCursor.close();
            userAccountDB.close();

            return true;
        }
    }
}
