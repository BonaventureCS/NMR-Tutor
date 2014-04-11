/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.sbu.cs.android.NMR.expandingcells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.tabsswipe.R;

import java.util.List;

import edu.sbu.cs.android.NMR.core.HomeFragment;

/**
 * This is a custom array adapter used to populate the listview whose items will
 * expand to display extra content in addition to the default display.
 */
public class CustomArrayAdapter extends ArrayAdapter<ExpandableListItem>{
	EditText etAns;
	Button btsub;
	LinearLayout linearLayout;
    private List<ExpandableListItem> mData;
    
    private int mLayoutViewResourceId;

    public CustomArrayAdapter(Context context, int layoutViewResourceId,
                              List<ExpandableListItem> data) {
        super(context, layoutViewResourceId, data);
        mData = data;
        mLayoutViewResourceId = layoutViewResourceId;
    }

    /**
     * Populates the item in the listview cell with the appropriate data. This method
     * sets the thumbnail image, the title and the extra text. This method also updates
     * the layout parameters of the item's view so that the image and title are centered
     * in the bounds of the collapsed view, and such that the extra text is not displayed
     * in the collapsed state of the cell.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	final ExpandableListItem object = mData.get(position);
        if(convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(mLayoutViewResourceId, parent, false);
        }

        linearLayout = (LinearLayout)(convertView.findViewById(
                R.id.item_linear_layout));
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams
                (AbsListView.LayoutParams.MATCH_PARENT, object.getCollapsedHeight());
        linearLayout.setLayoutParams(linearLayoutParams);

        TextView titleView = (TextView)convertView.findViewById(R.id.title_view);
        TextView subtitleView = (TextView)convertView.findViewById(R.id.tvSubTitle);
        TextView textView = (TextView)convertView.findViewById(R.id.text_view);
        btsub= (Button)convertView.findViewById(R.id.btSubmit);
        btsub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {                   
            	Toast.makeText(v.getContext(), "onclick",Toast.LENGTH_SHORT).show();
            	btsub.setBackgroundColor(Color.CYAN);
            }
        });
         etAns= (EditText)convertView.findViewById(R.id.etAns);
         etAns.setOnLongClickListener(new OnLongClickListener() {
             public boolean onLongClick(View v) {
            	 
            	 etAns.setFocusableInTouchMode(false);
            	 if(etAns.isFocused()){
 					Toast.makeText(v.getContext(), "editText is focused",Toast.LENGTH_SHORT).show();
 				}else{
 					Toast.makeText(v.getContext(), "editText is  not focused",Toast.LENGTH_SHORT).show();
 				}
                 return true;
             }
         });

        titleView.setText(object.getTitle());
        subtitleView.setText(object.getSubTitle());
        textView.setText(object.getText());
        etAns.setHint("Answer here");
        convertView.setLayoutParams(new ListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT));

        ExpandingLayout expandingLayout = (ExpandingLayout)convertView.findViewById(R.id
                .expanding_layout);
        
        expandingLayout.setExpandedHeight(object.getExpandedHeight());
        expandingLayout.setSizeChangedListener(object);

        if (!object.isExpanded()) {
            expandingLayout.setVisibility(View.GONE);
        } else {
            expandingLayout.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    /**
     * Crops a circle out of the thumbnail photo.
     */
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Config.ARGB_8888);
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        int halfWidth = bitmap.getWidth()/2;
        int halfHeight = bitmap.getHeight()/2;
        canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth, halfHeight), paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//	    case R.id.etAns:
////	    	Toast.makeText(this.getContext(), "onclick",Toast.LENGTH_SHORT).show();
////	    	etAns.setFocusable(true);
////	    	etAns.setFocusableInTouchMode(true);
//	       break;
//		case R.id.btSubmit:
//			//Toast.makeText(this.getContext(), "btclick",Toast.LENGTH_SHORT).show();
//			//btsub.setBackgroundColor(Color.BLUE);
////	        linearLayout.setFocusable(true);
////	        linearLayout.setFocusableInTouchMode(true);
////			etAns.clearFocus();
//		break;
//	    }
//
//	}
}