package com.example.roshan.seasonalbuddy6.Template;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Erandi Dissanayaka on 11/06/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> fragments=new ArrayList<>();
//    ArrayList<String>tabTitles=new ArrayList<>();
//    ArrayList<Integer>imgTitles=new ArrayList<>();

    public void addFragments(Fragment fragments){

        this.fragments.add(fragments);
//        this.tabTitles.add(titles);
//        this.imgTitles.add(img);
    }
    public ViewPagerAdapter(FragmentManager fm){

        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles.get(position);}
}
