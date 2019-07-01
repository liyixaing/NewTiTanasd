package lanjing.com.titan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList;
    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragmentList=list;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList!=null?mFragmentList.size():0;
    }
}
