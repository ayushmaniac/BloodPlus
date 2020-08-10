package in.socialninja.bloodplus.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import in.socialninja.bloodplus.fragments.RootFragment;

public class FrontFragmentAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public FrontFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                RootFragment fragment1 = new RootFragment();
                return fragment1;
            case 1:
                RootFragment fragment2 = new RootFragment();
                return fragment2;
            case 2:
                RootFragment fragment3 = new RootFragment();
                return fragment3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}