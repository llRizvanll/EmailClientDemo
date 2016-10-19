package com.nitghowl.test;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CustomToast {
    public static void show(Activity context, String body){
            // create instance
            Toast toast = new Toast(context);

    // inflate custom view
            View view = context.getLayoutInflater().inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.txtMessage);
        textView.setText(body);
    // set custom view
            toast.setView(view);

    // set duration
            toast.setDuration(Toast.LENGTH_LONG);

    // set position
            int margin = context.getResources().getDimensionPixelSize(R.dimen.toast_margin);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, margin);

    // show toast
            toast.show();
    }
}
