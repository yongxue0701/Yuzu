package sg.edu.nus.learnandroid;

import android.content.Context;
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

    public UserAccountRVAdapter(List<String> userAccountListButtons, Context context) {
        this.userAccountListButtons = userAccountListButtons;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView buttonName;
        public ImageView buttonIcon;
        public PercentRelativeLayout buttonPRL;

        public ViewHolder(View itemView) {
            super(itemView);
            buttonName = itemView.findViewById(R.id.userAccount_buttonName_TV);
            buttonIcon = itemView.findViewById(R.id.userAccount_buttonIcon_IV);
            buttonPRL = itemView.findViewById(R.id.user_account_recyclerview_PRL);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_account_recyclerview, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        String buttonName = userAccountListButtons.get(position);

        viewHolder.buttonName.setText(buttonName);

        if (buttonName.equals("Edit Profile")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Hello I am edit profile", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.buttonIcon.setImageResource(R.drawable.ic_forum_black_24dp);
        }

        if (buttonName.equals("Feedback")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Hello I am feedback", Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.buttonIcon.setImageResource(R.drawable.ic_forum_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return userAccountListButtons.size();
    }
}
