package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.percent.PercentRelativeLayout;
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

import java.util.List;

/**
 * Created by Yongxue on 17/10/17.
 */

public class UserAccountRVAdapter extends RecyclerView.Adapter<UserAccountRVAdapter.ViewHolder> {

    UserAccountDB userAccountDB;

    private List<String> userAccountListButtons;
    private Context context;
    private Activity activity;

    public UserAccountRVAdapter(List<String> userAccountListButtons, Context context, Activity activity) {
        this.userAccountListButtons = userAccountListButtons;
        this.context = context;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView buttonNameTV;
        public ImageView buttonIconIV;
        public PercentRelativeLayout buttonPRL;

        public ViewHolder(View itemView) {
            super(itemView);
            buttonNameTV = itemView.findViewById(R.id.userAccount_buttonName_TV);
            buttonIconIV = itemView.findViewById(R.id.userAccount_buttonIcon_IV);
            buttonPRL = itemView.findViewById(R.id.user_account_recyclerview_PRL);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_account_recycler_view, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        String buttonName = userAccountListButtons.get(position);

        viewHolder.buttonNameTV.setText(buttonName);

        if (buttonName.equals("Edit Profile")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), ProfileEditActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.profile_edit);
        }

        if (buttonName.equals("Achievements")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.achievements);
        }

        if (buttonName.equals("Logout")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initiateLogoutDialog(view);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.logout);
        }

        if (buttonName.equals("Feedback")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), UserFeedbackActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.feedback);
        }

        if (buttonName.equals("Terms and Conditions")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), TermsAndConditionsActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.terms_and_conditions);
        }

        if (buttonName.equals("Privacy Policy")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), PrivacyActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.privacy);
        }

        if (buttonName.equals("Acknowledgements")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), AcknowledgementsActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.acknowledgement);
        }
    }

    @Override
    public int getItemCount() {
        return userAccountListButtons.size();
    }

    private void initiateLogoutDialog(View view) {

        userAccountDB = new UserAccountDB(activity);

        final Dialog dialog = new Dialog(activity, R.style.Theme_Dialog_Cancel_Btn);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submit_popup);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER_VERTICAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogWindow.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();

        TextView title = (TextView) dialogWindow.findViewById(R.id.submit_popup_title_TV);
        title.setText(R.string.logout_popup_title_TV);

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

                userAccountDB.open();
                Cursor mCursor = userAccountDB.getRecordByIsLogin(1);

                if (mCursor != null && mCursor.moveToFirst() && (mCursor.getCount() == 1)) {
                    do {
                        String username = mCursor.getString(mCursor.getColumnIndex("username"));
                        userAccountDB.updateIsLoginByUsername(username, false);
                    } while (mCursor.moveToNext());
                }

                mCursor.close();
                userAccountDB.close();

                Intent myIntent = new Intent(context, LoginActivity.class);
                activity.startActivity(myIntent);
            }
        });
    }
}
