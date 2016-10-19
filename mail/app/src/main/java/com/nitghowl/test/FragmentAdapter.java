package com.nitghowl.test;

import android.app.*;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<MessageItemModel> data ;
    private int selectedPage = 0 ;
    public FragmentAdapter(FragmentManager fragmentManager, List<MessageItemModel> listOfItems, int selectedPage){
        super(fragmentManager);
        this.data = listOfItems;
        this.selectedPage = selectedPage;
    }

    @Override
    public Fragment getItem(int position) {
        FragmentHolder fragmentHolder = new FragmentHolder();
        Bundle args = new Bundle();
        args.putSerializable(FragmentHolder.DATA_ARG,data.get(position));
        args.putInt(FragmentHolder.TAG_ARG,this.selectedPage);
        fragmentHolder.setArguments(args);
        return fragmentHolder;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
