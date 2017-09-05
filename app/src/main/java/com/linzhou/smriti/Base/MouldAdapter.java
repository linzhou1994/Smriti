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



    //数据源
    protected List mList;

    protected Context mContext;

    protected LayoutInflater inflater;

    public MouldAdapter(Context context, List list) {
        this(context,list,false);
    }

    public MouldAdapter(Context context, List list, boolean onTouch) {
        this.mContext = context;
        this.mList = list;
        inflater = (LayoutInflater) mContext.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmList(List list) {
        this.mList = list;
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
            mViewHolder = setViewHolder(convertView,position);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
            updateViewHolder(mViewHolder);
        }

        mViewHolder.bandData(mList.get(position));

        mViewHolder.setListener(position);

        convertView.setTag(mViewHolder);

        return convertView;
    }


    /**
     * 设置布局文件
     *
     * @return 布局文件
     */
    protected abstract int setLayout();

    /**
     * 设置viewHolder
     *
     * @param convertView
     * @param position
     * @return viewHolder
     */
    protected abstract ViewHolder setViewHolder(View convertView,int position);

    /**
     * 更新viewholder的操作函数
     *
     * @param viewHolder 需要更新的viewHolder类
     */
    protected void updateViewHolder(ViewHolder viewHolder){};


    static  protected abstract class ViewHolder {

        /**
         * 绑定数据
         *
         */
        public abstract void bandData(Object o);

        /**
         * 设置监听事件
         *
         * @param position
         */
        public abstract void setListener(int position);

    }



}
