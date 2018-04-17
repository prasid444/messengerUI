package com.psyclotron.messenger.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.psyclotron.messenger.fragments.ChatFragment;
import com.psyclotron.messenger.fragments.ContactsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 2;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ChatFragment.newInstance();
            case 1:
                return ContactsFragment.newInstance();

        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ChatFragment.TITLE;

            case 1:
                return ContactsFragment.TITLE;


        }
        return super.getPageTitle(position);
    }
}

