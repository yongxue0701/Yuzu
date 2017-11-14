package sg.edu.nus.learnandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Yongxue
 */

public class UserFeedbackActivity extends AppCompatActivity {

    FeedbackDB feedbackDB;
    private EditText feedbackTitleET;
    private EditText feedbackContentET;
    private EditText feedbackContactET;
    private String feedbackDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        feedbackDB = new FeedbackDB(this);

        // Set up custom action bar with back button and done button
        getSupportActionBar().setDisplayOptions(getActionBar().DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_with_donebtn);

        // Set up the back button and title on action bar
        View view = getSupportActionBar().getCustomView();

        TextView actionBarTitleTV = (TextView) view.findViewById(R.id.action_bar_title_with_donebtn);
        actionBarTitleTV.setText(R.string.action_bar_title_user_feedback);

        ImageView backBtnIV = (ImageView) view.findViewById(R.id.action_bar_back_with_donebtn);
        backBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateCancelDialog();
            }
        });

        TextView doneBtn = (TextView) findViewById(R.id.action_bar_done_with_donebtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiateSubmitDialog(view);
            }
        });
    }

    @Override
    public void onBackPressed() {
        initiateCancelDialog();
    }

    private void initiateCancelDialog() {

        final Dialog dialog = new Dialog(UserFeedbackActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.cancel_feedback_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        Button noBtn = (Button) dialog.findViewById(R.id.cancel_feedback_popup_no_btn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button yesBtn = (Button) dialog.findViewById(R.id.cancel_feedback_popup_yes_btn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),UserAccountActivity.class);
                startActivity(myIntent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        });
    }

    private void initiateSubmitDialog(View view) {

        final Dialog dialog = new Dialog(UserFeedbackActivity.this, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        TextView title = (TextView) dialogWindow.findViewById(R.id.submit_popup_title_TV);
        title.setText(R.string.submit_feedback_popup_title_TV);

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
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {

        boolean allDone = true;

        feedbackTitleET = (EditText) findViewById(R.id.feedback_title_ET);
        feedbackContentET = (EditText) findViewById(R.id.feedback_content_ET);
        feedbackContactET = (EditText) findViewById(R.id.feedback_contact_detail_content_ET);
        feedbackDate = DateFormat.getDateTimeInstance().format(new Date());

        if (feedbackTitleET.getText().toString().isEmpty()) {
            allDone = false;
            feedbackTitleET.setError("Please provide feedback title!");
        }
        if (feedbackContentET.getText().toString().isEmpty()) {
            allDone = false;
            feedbackContentET.setError("Please provide feedback content!");
        }
        if (feedbackContactET.getText().toString().isEmpty()) {
            allDone = false;
            feedbackContactET.setError("Please provide your email address!");
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(feedbackContactET.getText().toString()).matches()) {
            Toast.makeText(getApplicationContext(), "Your email address is invalid!", Toast.LENGTH_SHORT).show();
            allDone = false;
        }
        if (allDone) {

            feedbackDB.open();
            feedbackDB.insertRecord(feedbackTitleET.getText().toString(), feedbackDate,
                    feedbackContentET.getText().toString(), feedbackContactET.getText().toString());
            feedbackDB.close();

            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.successful_message_toast, (ViewGroup)
                    findViewById(R.id.successful_message_toast_PRL));

            TextView successContent = (TextView) layout.findViewById(R.id.successful_message_toast_content_TV);
            successContent.setText(R.string.feedback_submit_success_toast_content_TV);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, -200);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);

            toast.show();

            Intent myIntent = new Intent(getApplicationContext(), UserAccountActivity.class);
            startActivity(myIntent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        }
    }
}
