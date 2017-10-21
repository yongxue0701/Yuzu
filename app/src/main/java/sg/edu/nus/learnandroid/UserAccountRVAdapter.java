package sg.edu.nus.learnandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Yongxue on 17/10/17.
 */

public class UserAccountRVAdapter extends RecyclerView.Adapter<UserAccountRVAdapter.ViewHolder> {

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

            viewHolder.buttonIconIV.setImageResource(R.drawable.ic_forum_black_24dp);
        }

        if (buttonName.equals("Feedback")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Hello I am feedback", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.ic_forum_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return userAccountListButtons.size();
    }
}
