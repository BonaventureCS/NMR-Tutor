package edu.sbu.cs.android.NMR.adapter;

import edu.sbu.cs.android.NMR.core.DrawFragment;
import edu.sbu.cs.android.NMR.core.HomeFragment;
import edu.sbu.cs.android.NMR.core.QuestionsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Home fragment activity
			return new HomeFragment();
		case 1:
			// Questions fragment activity
			return new QuestionsFragment();
		case 2:
			// draw fragment activity
			return new DrawFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
