package com.linzhou.smriti.View.Listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 自定义不滑动的listview
 */

public class NoScrollListView extends LinearLayout {


    private BaseAdapter mAdapter;

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseAdapter mAdapter) {
        if (mAdapter==null) return;
        this.mAdapter = mAdapter;
        mAdapter.setmNotifyDataSetChanged(mNotifyDataSetChanged);
        notifyData();
    }

    public NoScrollListView(Context context) {
        super(context);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    public void notifyData(){

        removeAllViews();
        if(mAdapter.getSize() == 0){
            return;
        }
        LayoutParams layoutParams =
                new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i=0; i<mAdapter.getSize(); i++){
            final int index = i;
            View view = getView(index);
            if(view == null){
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }
            addView(view, index, layoutParams);
        }
    }

    private View getView(int position){
        if (mAdapter==null)
            throw new NullPointerException("mBaseAdapter is null, please check getView()...");
        return mAdapter.getView(position);
    }


    public NotifyDataSetChanged getmNotifyDataSetChanged() {
        return mNotifyDataSetChanged;
    }

    public void setmNotifyDataSetChanged(NotifyDataSetChanged mNotifyDataSetChanged) {
        this.mNotifyDataSetChanged = mNotifyDataSetChanged;
    }

    private NotifyDataSetChanged mNotifyDataSetChanged = new NotifyDataSetChanged() {
        @Override
        public void notifyDataSetChanged() {
            notifyData();
        }
    };
}
