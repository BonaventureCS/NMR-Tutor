package info.androidhive.tabsswipe;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.tabsswipe.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.expandingcells.CustomArrayAdapter;
import com.example.android.expandingcells.ExpandableListItem;
import com.example.android.expandingcells.ExpandingCells;
import com.example.android.expandingcells.ExpandingListView;
public class QuestionsFragment extends Fragment {
	private ExpandingListView mListView;
	private CustomArrayAdapter adapter;
	private List<ExpandableListItem> mData;
	  private final int CELL_DEFAULT_HEIGHT = 200;
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
		//mData = new ArrayList<ExpandableListItem>();
		 List<ExpandableListItem> mData = new ArrayList<ExpandableListItem>();
		adapter = new CustomArrayAdapter(getActivity(), R.layout.list_view_item, mData);
		mListView = (ExpandingListView) rootView.findViewById(R.id.main_list_view);
		mListView.setAdapter(adapter);
		mListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
		//mListView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		   for (int i = 0; i <NUM_OF_CELLS; i++) {
	            ExpandableListItem obj = values[i % values.length];
	            mData.add(new ExpandableListItem(obj.getTitle(),obj.getSubTitle(), 
	                    obj.getCollapsedHeight(), obj.getText()));
	        }
		return rootView;
	}
	
}
