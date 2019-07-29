package com.fatmanurrr.gmail;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Lenovo on 5.09.2018.
 */

public class MailActivity  extends AppCompatActivity {

    private int position = 0;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<mailFragment> listFragments = new ArrayList<>();
    private ArrayList<gmail> mailItems = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        getArguments();
        createFragments();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);


    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {


        private ArrayList<mailFragment> listFragment;


        public ViewPagerAdapter(FragmentManager fm, ArrayList<mailFragment> listFragment) {

            super(fm);
            this.listFragment = listFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

    }


    public void getArguments() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

          /*  mailItems = MainActivity.mailList;
            position = bundle.getInt("position");
*/
          mailItems=bundle.getParcelableArrayList("gmails");
          position=bundle.getInt("position");

        }
    }

    public void createFragments() {
        for (int i = 0; i < mailItems.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putString("gönderenn",mailItems.get(i).getSender());
            bundle.putString("mesaj",mailItems.get(i).getMessage());
            mailFragment mailFragment = new mailFragment(mailItems, i);
            mailFragment.setArguments(bundle);
            listFragments.add(mailFragment);

        }

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);

    }




}