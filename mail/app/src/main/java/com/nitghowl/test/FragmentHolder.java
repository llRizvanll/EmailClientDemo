package com.nitghowl.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Iterator;

public class FragmentHolder extends android.support.v4.app.Fragment {

    public static final String TAG_ARG = "selected_page";
    public static final String DATA_ARG = "data";
    private MessageItemModel messageItemModel;
    public FragmentHolder() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_item_summary_page, container, false);
        Bundle args = getArguments();
        messageItemModel = (MessageItemModel) args.getSerializable(DATA_ARG);

        ((TextView) rootView.findViewById(R.id.summary_header_prof_name)).setText(messageItemModel.getParticipants().get(0).getName());
        ((TextView) rootView.findViewById(R.id.summary_header_prof_emailid)).setText(messageItemModel.getParticipants().get(0).getEmail());
        ((TextView) rootView.findViewById(R.id.summary_header_top_subject)).setText(messageItemModel.getSubject());
        ((TextView) rootView.findViewById(R.id.summary_body_content)).setText(messageItemModel.getBody());
        String emailIdsRecList = "";
        for (int i = 0 ; i < messageItemModel.getParticipants().size(); i ++) {
            if (emailIdsRecList.trim().length() > 0 ){
                emailIdsRecList = emailIdsRecList +", "+ messageItemModel.getParticipants().get(i).getEmail();
            }
            else{
                emailIdsRecList = messageItemModel.getParticipants().get(i).getEmail();
            }
        }
        ((TextView) rootView.findViewById(R.id.summary_header_prof_to_emailid)).setText(emailIdsRecList);

        return rootView;
    }
}
