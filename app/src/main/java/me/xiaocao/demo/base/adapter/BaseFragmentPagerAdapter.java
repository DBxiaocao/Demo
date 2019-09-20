package me.xiaocao.demo.base.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTabTitleList;
    private List<Fragment> mFragments;

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }
    public BaseFragmentPagerAdapter(FragmentManager fm,
                                    List<String> tabTitleList, List<Fragment> fragments) {
        super(fm);
        mTabTitleList = tabTitleList;
        mFragments = fragments;
    }

    public void setTabTitleList(List<String> tabTitleList) {
        mTabTitleList = tabTitleList;
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
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
        return mTabTitleList.get(position);
    }

}
