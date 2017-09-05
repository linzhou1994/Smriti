package com.linzhou.smriti.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.linzhou.smriti.Base.MouldAdapter;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.PicassoUtils;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.utils.UtilTools;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/07/31
 *描述:   
 */


public class CommunityApdater extends MouldAdapter {

    protected ItemOnClick mItemOnclick;


    public CommunityApdater(Context context, List list) {
        super(context, list);
    }

    public CommunityApdater(Context context, List list, boolean onTouch) {
        super(context, list, onTouch);
    }

    @Override
    protected int setLayout() {
        return R.layout.apdater_community;
    }

    @Override
    protected MouldAdapter.ViewHolder setViewHolder(View convertView,int position) {
        return new CommunityViewHolder(mContext,convertView, (Theme) mList.get(position),mItemOnclick);
    }

    @Override
    protected void updateViewHolder(ViewHolder viewHolder) {
        ((CommunityViewHolder)viewHolder).setmItemOnclick(mItemOnclick);
    }

    public void setItemOnclick(ItemOnClick mOnClickListener) {
        this.mItemOnclick = mOnClickListener;
    }

    public void removeItemOnclick() {
        this.mItemOnclick = null;
    }





    static class CommunityViewHolder extends MouldAdapter.ViewHolder {

        public View convertView;
        public CircleImageView eimage;//用户头像
        public TextView tv_username;//用户名称
        public TextView tv_firsttime;//发布时间
        public TextView tv_title;//标题
        public TextView tv_content;//内容
        public TextView tv_replienum;//回复数

        private Theme mTheme;

        private Context mContext;

        private ItemOnClick mItemOnclick;


        public CommunityViewHolder(Context mContext,View view ,Theme mTheme,ItemOnClick mItemOnclick) {
            this.convertView=view;
            eimage = (CircleImageView) convertView.findViewById(R.id.eimage);
            tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            tv_firsttime = (TextView) convertView.findViewById(R.id.tv_firsttime);
            tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            tv_replienum = (TextView) convertView.findViewById(R.id.tv_replienum);

            this.mTheme = mTheme;
            this.mContext = mContext;
            this.mItemOnclick=mItemOnclick;
        }

        public ItemOnClick getmItemOnclick() {
            return mItemOnclick;
        }

        public void setmItemOnclick(ItemOnClick mItemOnclick) {
            this.mItemOnclick = mItemOnclick;
        }

        public void bandData(Object o) {
            mTheme= (Theme) o;
            if (!TextUtils.isEmpty(mTheme.getUser().getHead()))
                PicassoUtils.loadImageViewSize(mContext
                        , Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead()
                        , 32, 32, eimage);
            L.d("head:        " + Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead());
            tv_title.setText(mTheme.getTitle());
            tv_username.setText(mTheme.getUser().getUsername());
            if (UtilTools.getStartTime().getTime() < mTheme.getFirsttime().getTime())
                tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime(),"HH:mm"));
            else tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime()));

            tv_content.setText(mTheme.getContent());
            tv_replienum.setText(mTheme.getReplienum() + "");
        }

        @Override
        public void setListener(int position) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemOnclick!=null)
                        mItemOnclick.Onclick(position);
                }
            });
        }

    }


}
