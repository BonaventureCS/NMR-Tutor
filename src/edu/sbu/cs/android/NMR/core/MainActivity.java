package edu.sbu.cs.android.NMR.core;


import edu.sbu.cs.android.NMR.adapter.TabsPagerAdapter;
import edu.sbu.cs.android.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements
	ActionBar.TabListener{
	Bundle bd;
	private NonSwipeableViewPager viewPager;
	//DrawingView dv=new DrawingView(this,);
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Spectra", "Questions", "SolveIt" };
	private boolean lock=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
		bd=new Bundle();
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setIcon(R.drawable.chalkboardicon_2);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
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

	    	HomeFragment.w.loadUrl("file:///android_asset/nmr.html");
	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
	       return true;
	    case R.id.action_IR:
	    	HomeFragment.w.loadUrl("file:///android_asset/ir.html");
	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
	        return true;
	    case R.id.action_CNMR:
	    	HomeFragment.w.loadUrl("file:///android_asset/cnmr.html");
	    	HomeFragment.w.getSettings().setBuiltInZoomControls(true);
	    	HomeFragment.w.getSettings().setDisplayZoomControls(false);
	        return true;
	    case R.id.action_Lock:
	    	lock=!lock;
	    	if(lock)
	    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    	else
	    		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    	
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
	public void onBackPressed() {
	    moveTaskToBack(true);
		//wv.loadUrl("file:///android_asset/index.html");
	}
	
}
