package com.linzhou.smriti.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linzhou.smriti.Base.MouldAdapter;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.PicassoUtils;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.utils.UtilTools;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/07/31
 *描述:   
 */


public class CommunityApdater extends MouldAdapter {

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
    protected MouldAdapter.ViewHolder setViewHolder(View convertView) {
        return new CommunityViewHolder(convertView,mList,mContext);
    }


    static class CommunityViewHolder extends MouldAdapter.ViewHolder {
        public CircleImageView eimage;//用户头像
        public TextView tv_username;//用户名称
        public TextView tv_firsttime;//发布时间
        public TextView tv_title;//标题
        public TextView tv_content;//内容
        public TextView tv_replienum;//回复数

        protected List mList;

        protected Context mContext;


        public CommunityViewHolder(View view ,List mList,Context mContext) {
            eimage = (CircleImageView) view.findViewById(R.id.eimage);
            tv_username = (TextView) view.findViewById(R.id.tv_username);
            tv_firsttime = (TextView) view.findViewById(R.id.tv_firsttime);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_replienum = (TextView) view.findViewById(R.id.tv_replienum);

            this.mList = mList;
            this.mContext = mContext;
        }

        public void bandData(int position) {

            Theme mTheme = (Theme) mList.get(position);
            if (!TextUtils.isEmpty(mTheme.getUser().getHead()))
                PicassoUtils.loadImageViewSize(mContext
                        , Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead()
                        , 25, 25, eimage);
            L.d("head:        " + Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead());
            tv_title.setText(mTheme.getTitle());
            tv_username.setText(mTheme.getUser().getUsername());
            if (UtilTools.getStartTime().getTime() < mTheme.getFirsttime().getTime())
                tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime(),"HH:mm"));
            else tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime()));

            tv_content.setText(mTheme.getContent());
            tv_replienum.setText(mTheme.getReplienum() + "");
        }

    }


}
