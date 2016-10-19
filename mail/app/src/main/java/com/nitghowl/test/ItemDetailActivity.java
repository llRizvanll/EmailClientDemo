package com.nitghowl.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDetailActivity extends AppCompatActivity {
    FragmentAdapter fragmentAdapter = null;
    private final String POS_TAG = "positionSelected";
    private final String TOTPAGES_TAG = "TOTAL_PAGES_DOWNLOAD";
    private int totPagesToDownload = 0 , selectedPage = 0 ;
    private ViewPager pager;

    List<MessageItemModel> listOfData = new ArrayList<MessageItemModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        totPagesToDownload = getIntent().getIntExtra(TOTPAGES_TAG,0);
        selectedPage = getIntent().getIntExtra(POS_TAG,0);

        // show loader till fetches all data
        for (int i = 1 ; i <= totPagesToDownload ; i++) {
            fetchData(i);
        }

        pager = (ViewPager) findViewById(R.id.item_viewpager);

    }

    public void fetchData (final int position) {
        if (InternetRequest.isConnectedToInternet(this)) {
            Call<MessageItemModel> request = HttpRequestMaker.createService().getSingleMessage(position);
            request.enqueue(new Callback<MessageItemModel>() {
                @Override
                public void onResponse(Call<MessageItemModel> call, Response<MessageItemModel> response) {
                    if (response!= null) {
                        listOfData.add(response.body());
                    }

                    if (listOfData.size() == totPagesToDownload){
                        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),listOfData,selectedPage);
                        pager.setAdapter(fragmentAdapter);
                        pager.setCurrentItem(selectedPage,true);
                    }
                }

                @Override
                public void onFailure(Call<MessageItemModel> call, Throwable t) {

                }
            });
        }
        else{
            CustomToast.show(this,"No Internet Connection!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
