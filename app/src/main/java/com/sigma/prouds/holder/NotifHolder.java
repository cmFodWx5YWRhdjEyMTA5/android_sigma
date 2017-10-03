package com.sigma.prouds.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sigma.prouds.CustomTypefaceSpan;
import com.sigma.prouds.NotifActivity;
import com.sigma.prouds.R;
import com.sigma.prouds.model.NotificationModel;

import de.greenrobot.event.EventBus;

/**
 * Created by ruske on 22/08/17.
 */

public class NotifHolder extends RecyclerView.ViewHolder {

    private Context context;
    private LinearLayout llNotif;
    private TextView tvNotif, tvAccept, tvTime;
    private Typeface latoRegular;

    public NotifHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        tvNotif = (TextView) itemView.findViewById(R.id.tv_notif);
        tvAccept = (TextView) itemView.findViewById(R.id.tv_notif_acc);
        tvTime = (TextView) itemView.findViewById(R.id.tv_notif_time);
        llNotif = (LinearLayout) itemView.findViewById(R.id.ll_notif);

        latoRegular = Typeface.createFromAsset(itemView.getContext().getAssets(), "lato_regular.ttf");
    }

    public void bind(final NotificationModel model) {
        String user = "null"; // isi nama user
        String activity = "null"; // isi kegiatannya (ex: update, remove, approve)
        String object = "null"; // isi objek yang diupdate

        Spannable spannable = new SpannableString(user + activity + object);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#cf000f")),
                0, user.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#cf000f")),
                user.length() + activity.length(), user.length() + activity.length() + object.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvNotif.setText(spannable);

        tvNotif.setTypeface(latoRegular);
        tvAccept.setTypeface(latoRegular);
        tvTime.setTypeface(latoRegular);

        tvNotif.setText(model.getUserName() + " " + model.getText());
        tvTime.setText(model.getDatetime());

        llNotif.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putString("key_project_id", model.getProjectId());
                bundle.putString(NotifActivity.KEY_NOTIF_PROJECT_NAME, model.getProjectName());
                bundle.putString(NotifActivity.KEY_NOTIF_PROJECT_STATUS, model.getProjectStatus());
                bundle.putString(NotifActivity.KEY_NOTIF_PROJECT_COMPLETE, model.getProjectStatus());
                EventBus.getDefault().post(bundle);
            }
        });


    }

}
