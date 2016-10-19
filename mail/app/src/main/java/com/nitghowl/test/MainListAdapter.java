package com.nitghowl.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Iterator;
import java.util.List;

import static com.nitghowl.test.R.id.switcher;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {

    private Context context;
    private List<MessageListModel> dataObject;
    final String POS_TAG = "positionSelected";
    final String TOTPAGES_TAG = "TOTAL_PAGES_DOWNLOAD";
    public MainListAdapter(Context context,List<MessageListModel> curDataManager){
        this.context = context;
        this.dataObject = curDataManager;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView participantTxtView,subjectTxtView,summaryTxtView;
        public ViewSwitcher viewSwitcher;
        public FrameLayout card_view;
        public ViewHolder(View rootView){
            super(rootView);
        }
    }

    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item,parent,false);
        MainListAdapter.ViewHolder viewHolder = new MainListAdapter.ViewHolder(view);
        viewHolder.participantTxtView = (TextView) view.findViewById(R.id.info_text);
        viewHolder.subjectTxtView = (TextView) view.findViewById(R.id.main_list_item_subject);
        viewHolder.summaryTxtView = (TextView) view.findViewById(R.id.main_list_item_summary);
        viewHolder.viewSwitcher = (ViewSwitcher) view.findViewById(switcher);
        viewHolder.card_view = (FrameLayout) view.findViewById(R.id.card_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainListAdapter.ViewHolder holder, final int position) {

        final MessageListModel list = dataObject.get(position);
        Iterator iterator = list.getParticipants().iterator();

        String _participants = "";
        while (iterator.hasNext()) {
            if (_participants.trim().length() > 0 ) {
                _participants = _participants+", "+iterator.next();
            }
            else{
                _participants = (String) iterator.next();
            }
        }

        holder.participantTxtView.setText(_participants);

        holder.subjectTxtView.setText(list.getSubject());

        holder.summaryTxtView.setText(list.getPreview());

        if (list.getIsStarred()) {
            holder.viewSwitcher.showNext();
        }
        else {
            holder.viewSwitcher.showPrevious();
        }

        holder.viewSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewSwitcher switcher = (ViewSwitcher) view;
                if (switcher.getDisplayedChild() == 0) {
                    switcher.showNext();
                }
                else {
                    switcher.showPrevious();
                }
            }
        });

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itemDetailActivity = new Intent(context,ItemDetailActivity.class);
                itemDetailActivity.putExtra(POS_TAG,position);
                itemDetailActivity.putExtra(TOTPAGES_TAG,dataObject.size());
                context.startActivity(itemDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataObject.size();
    }


}
