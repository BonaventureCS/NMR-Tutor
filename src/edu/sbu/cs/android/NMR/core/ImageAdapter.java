package edu.sbu.cs.android.NMR.core;
import edu.sbu.cs.android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some
                                    // attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = { R.drawable.chalkboardicon_2a, R.drawable.chalkboardicon_2b, 
    								R.drawable.chalkboardicon_2c, R.drawable.chalkboardicon_2d, 
    								R.drawable.chalkboardicon_2e, R.drawable.chalkboardicon_2f, 
    								R.drawable.chalkboardicon_2g, R.drawable.chalkboardicon_2h,
    		                        R.drawable.chalkboardicon_2i, R.drawable.chalkboardicon_2j, 
    								R.drawable.chalkboardicon_2k, R.drawable.chalkboardicon_2l, 
    								R.drawable.chalkboardicon_2m, R.drawable.chalkboardicon_2n, 
    								R.drawable.chalkboardicon_2o, R.drawable.chalkboardicon_2p,
    		                        R.drawable.chalkboardicon_2q, R.drawable.chalkboardicon_2r,
    		                        R.drawable.chalkboardicon_2s, R.drawable.chalkboardicon_2t,
    		                        };
}
