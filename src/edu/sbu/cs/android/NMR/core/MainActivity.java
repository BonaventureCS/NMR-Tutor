package edu.sbu.cs.android.NMR.core;

import java.util.ArrayList;

import edu.sbu.cs.android.NMR.adapter.TabsPagerAdapter;
import edu.sbu.cs.android.NMR.adapter.TitleNavigationAdapter;
import edu.sbu.cs.android.R;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements
	ActionBar.TabListener, OnNavigationListener{
	Bundle bd;
	private NonSwipeableViewPager viewPager;
	//DrawingView dv=new DrawingView(this,);
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Spectra", "Questions", "SolveIt" };
	private boolean lock=false;

	// Title navigation Spinner data
	private ArrayList<SpinnerNavItem> navSpinner;
	private TitleNavigationAdapter adapter;
static PeakHolder ph;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
		bd=new Bundle();
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		 ph= new PeakHolder();
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		ph.setPeak("A");
		// actionbar spinner 
			//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			navSpinner = new ArrayList<SpinnerNavItem>();
			navSpinner.add(new SpinnerNavItem("Peak A"));
			navSpinner.add(new SpinnerNavItem("Peak B"));
			navSpinner.add(new SpinnerNavItem("Peak C"));
			navSpinner.add(new SpinnerNavItem("Peak D" ));
			navSpinner.add(new SpinnerNavItem("Peak E" ));
			navSpinner.add(new SpinnerNavItem("Peak F" ));
			// title drop down adapter
			adapter = new TitleNavigationAdapter(getApplicationContext(),
					navSpinner);
			actionBar.setListNavigationCallbacks(adapter, this);
			// assigning the spinner navigation


		}
		

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if(lock){
				}else{
					actionBar.setSelectedNavigationItem(position);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_NMR:

	    	//HomeFragment.img.setImageResource(R.drawable.nmrq1blcklable);
	       return true;
	    case R.id.action_IR:
	    	//HomeFragment.img.setImageResource(R.drawable.nmrlabels);
	        return true;
	    case R.id.action_Lock:
	    	lock=!lock;
	    	Toast.makeText(getApplicationContext(), "lock value="+lock,Toast.LENGTH_SHORT).show();
	    	viewPager.setBlockSwipe(lock);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(this, " peak selected"+itemPosition,Toast.LENGTH_LONG).show();
		ph.setPeak(navSpinner.get(itemPosition).getTitle());
		
		return false;
	}
	
	
}
