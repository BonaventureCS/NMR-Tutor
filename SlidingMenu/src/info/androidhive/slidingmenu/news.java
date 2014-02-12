package info.androidhive.slidingmenu;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import info.androidhive.slidingmenu.R;

public class news extends Fragment
{
    View rootView;

public news () 
{
    // Empty constructor required for fragment subclasses
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle     savedInstanceState)
{   

getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

// Apply the layout for the fragment
rootView = inflater.inflate(R.layout.news, container, false);

return rootView;

}
}