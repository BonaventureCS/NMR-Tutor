package edu.sbu.cs.android.NMR.core;

import java.util.ArrayList;
import java.util.List;



import info.androidhive.tabsswipe.R;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import edu.sbu.cs.android.NMR.expandingcells.CustomArrayAdapter;
import edu.sbu.cs.android.NMR.expandingcells.ExpandableListItem;
import edu.sbu.cs.android.NMR.expandingcells.ExpandingListView;
public class QuestionsFragment extends Fragment {
	private ExpandingListView mListView;
	private CustomArrayAdapter adapter;
	  private final int CELL_DEFAULT_HEIGHT = 300;
	    private final int NUM_OF_CELLS = 30;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_questions, container, false);
	    ExpandableListItem[] values = new ExpandableListItem[] {
                new ExpandableListItem("Question 1","Peak: A", CELL_DEFAULT_HEIGHT,
                        getResources().getString(R.string.short_lorem_ipsum)),
                new ExpandableListItem("Question 2", "Peak: B", CELL_DEFAULT_HEIGHT,
                        getResources().getString(R.string.medium_lorem_ipsum)),
                new ExpandableListItem("Question 3","Peak: C", CELL_DEFAULT_HEIGHT,
                        getResources().getString(R.string.long_lorem_ipsum)),
        };

		 List<ExpandableListItem> mData = new ArrayList<ExpandableListItem>();
		adapter = new CustomArrayAdapter(getActivity(), R.layout.list_view_item, mData);
		mListView = (ExpandingListView) rootView.findViewById(R.id.main_list_view);
		mListView.setAdapter(adapter);
		mListView.setItemsCanFocus(true);
		   for (int i = 0; i <NUM_OF_CELLS; i++) {
	            ExpandableListItem obj = values[i % values.length];
	            mData.add(new ExpandableListItem("Question:"+i,obj.getSubTitle(), 
	                    obj.getCollapsedHeight(), obj.getText()));
	        }
		   mListView.setOnItemLongClickListener(onListClick);
		
		return rootView;
	}
	private AdapterView.OnItemLongClickListener onListClick = new AdapterView.OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
				long id) {
			
				//mListView.setFocusable(true);
				mListView.setFocusableInTouchMode(true);
			
				if(mListView.isFocused()){
					Toast.makeText(view.getContext(), "listView position "+position+" is focused",Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(view.getContext(), "listView position "+position+" is  not focused",Toast.LENGTH_SHORT).show();
				}
				return true;
		}
	};
	
}
