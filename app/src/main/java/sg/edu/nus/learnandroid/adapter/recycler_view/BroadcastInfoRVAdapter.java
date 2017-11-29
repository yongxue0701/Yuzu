package sg.edu.nus.learnandroid.adapter.recycler_view;

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

import java.util.List;

import sg.edu.nus.learnandroid.activity.broadcast.BroadcastIntroActivity;
import sg.edu.nus.learnandroid.activity.broadcast.BroadcastQuizInfoActivity;
import sg.edu.nus.learnandroid.R;

/**
 * Created by Yongxue on 13/11/17.
 */

public class BroadcastInfoRVAdapter extends RecyclerView.Adapter<BroadcastInfoRVAdapter.ViewHolder> {

    private List<String> broadcastButtonNames;
    private Context context;
    private Activity activity;

    public BroadcastInfoRVAdapter(List<String> broadcastButtonNames, Context context, Activity activity) {
        this.broadcastButtonNames = broadcastButtonNames;
        this.context = context;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView buttonNameTV;
        public ImageView buttonIconIV;
        public PercentRelativeLayout buttonPRL;

        public ViewHolder(View itemView) {
            super(itemView);
            buttonNameTV = itemView.findViewById(R.id.broadcast_info_buttonName_TV);
            buttonIconIV = itemView.findViewById(R.id.broadcast_info_buttonIcon_IV);
            buttonPRL = itemView.findViewById(R.id.broadcast_info_recyclerview_PRL);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.broadcast_info_recycler_view, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String buttonName = broadcastButtonNames.get(position);

        viewHolder.buttonNameTV.setText(buttonName);

        if (buttonName.equals("Introduction")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), BroadcastIntroActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.introduction);
        }

        if (buttonName.equals("Quiz")) {
            viewHolder.buttonPRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editProfileIntent = new Intent(view.getContext(), BroadcastQuizInfoActivity.class);
                    context.startActivity(editProfileIntent);
                    activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                }
            });

            viewHolder.buttonIconIV.setImageResource(R.drawable.quiz);
        }
    }

    @Override
    public int getItemCount() {
        return broadcastButtonNames.size();
    }
}
