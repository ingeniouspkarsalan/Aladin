package ustaad.aladin.com.Layout_Fragment_Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ustaad.aladin.com.classes.bus_list_class;

public class fragment_adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    private List<bus_list_class> bus_list_classList;
    public fragment_adapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title,ArrayList<bus_list_class> bus_list_classList) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        Bundle data = new Bundle();//create bundle instance
        data.putParcelableArrayList("data_list",bus_list_classList);
        fragment.setArguments(data);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }



}





