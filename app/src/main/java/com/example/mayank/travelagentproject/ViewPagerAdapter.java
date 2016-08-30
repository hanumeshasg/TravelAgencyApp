package com.example.mayank.travelagentproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by mayank on 30-08-2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragment=new ArrayList<>();
    ArrayList title=new ArrayList<>();

    public void addfragment (Fragment fragment, String title) {
        this.fragment.add(fragment);
        this.title.add(title);
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) title.get(position);
    }
}
