package com.linzhou.smriti.View.Listview;

/*
 *项目名： MyView
 *包名：   com.linzhou.myview.listview
 *创建者:  linzhou
 *创建时间:17/08/07
 *描述:   
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import java.util.List;

public abstract class BaseAdapter {

    //数据源
    protected List mList;

    protected Context mContext;

    protected LayoutInflater mInflater;

    public BaseAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(mContext);
        //(LayoutInflater) mContext.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmList(List list) {
        this.mList = list;
        notifyDataSetChanged();
    }


    private  NotifyDataSetChanged mNotifyDataSetChanged;

    public NotifyDataSetChanged getmNotifyDataSetChanged() {
        return mNotifyDataSetChanged;
    }

    public void setmNotifyDataSetChanged(NotifyDataSetChanged mNotifyDataSetChanged) {
        this.mNotifyDataSetChanged = mNotifyDataSetChanged;
    }

    public void notifyDataSetChanged(){
        if (mNotifyDataSetChanged!=null)
            mNotifyDataSetChanged.notifyDataSetChanged();
    }

    protected int getSize() {
        return mList == null ? 0 : mList.size();
    }

    protected Object getItem(int position){return mList.get(position);};

    protected abstract View getView(int position);

}
