package com.linzhou.smriti.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.UtilTools;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/08/01
 *描述:   
 */


public abstract class MouldAdapter extends BaseAdapter {

    protected itemOnclick mItemOnclick;

    boolean onTouch;


    //数据源
    protected List mList;

    protected Context mContext;

    protected LayoutInflater inflater;

    public MouldAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
        inflater = (LayoutInflater) mContext.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        onTouch = false;
    }

    public MouldAdapter(Context context, List list, boolean onTouch) {
        this.mContext = context;
        this.mList = list;
        inflater = (LayoutInflater) mContext.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.onTouch = onTouch;
    }

    public void setmList(List list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setonTouch(boolean onTouch) {
        this.onTouch = onTouch;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(setLayout(), null);
            mViewHolder = setViewHolder(convertView);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.bandData(position);
        if (mItemOnclick != null) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemOnclick.Onclick(position);
                }
            });

            if (onTouch)
                convertView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });


        }

        convertView.setTag(mViewHolder);

        return convertView;
    }

    public void setItemOnclick(itemOnclick mOnClickListener) {
        this.mItemOnclick = mOnClickListener;
    }

    public void removeItemOnclick() {
        this.mItemOnclick = null;
    }

    protected abstract int setLayout();

    protected abstract ViewHolder setViewHolder(View convertView);


    static  protected abstract class ViewHolder {
        /**
         * 绑定数据
         *
         * @param position 当前项
         */
        public abstract void bandData(int position);

    }

    public interface itemOnclick {
        void Onclick(int i);
    }

}
