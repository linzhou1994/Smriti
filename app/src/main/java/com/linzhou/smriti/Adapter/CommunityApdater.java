package com.linzhou.smriti.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.R;
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


public class CommunityApdater extends BaseAdapter {

    private List<Theme> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public CommunityApdater(Context context ,List<Theme> list){
        this.mContext=context;
        this.mList=list;
        inflater= (LayoutInflater) mContext.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setmList(List<Theme> list){
        this.mList=list;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        return mList.size();
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
        ViewHolder mViewHolder=null;
        Theme mTheme = mList.get(position);
        if (convertView==null){
            convertView=inflater.inflate(R.layout.apdater_community,null);
            mViewHolder = new ViewHolder(convertView);
        }else {
            mViewHolder= (ViewHolder) convertView.getTag();
        }

//        if (!TextUtils.isEmpty(mTheme.getUser().getHead()))
//            PicassoUtils.loadImageViewSize(mContext
//                    , Url.HTTP_USER_HEAD_URL+mTheme.getUser().getHead()
//                    , 25, 25, mViewHolder.eimage);
        mViewHolder.tv_title.setText(mTheme.getTitle());
        mViewHolder.tv_username.setText(mTheme.getUser().getUsername());
        mViewHolder.tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime()));

        mViewHolder.tv_content.setText(mTheme.getContent());
        mViewHolder.tv_replienum.setText(mTheme.getReplienum()+"");

        convertView.setTag(mViewHolder);

        return convertView;
    }

    class ViewHolder{
        public CircleImageView eimage;//用户头像
        public TextView tv_username;//用户名称
        public TextView tv_firsttime;//发布时间
        public TextView tv_title;//标题
        public TextView tv_content;//内容
        public TextView tv_replienum;//回复数


        public ViewHolder(View view){
            eimage= (CircleImageView) view.findViewById(R.id.eimage);
            tv_username= (TextView) view.findViewById(R.id.tv_username);
            tv_firsttime= (TextView) view.findViewById(R.id.tv_firsttime);
            tv_title= (TextView) view.findViewById(R.id.tv_title);
            tv_content= (TextView) view.findViewById(R.id.tv_content);
            tv_replienum= (TextView) view.findViewById(R.id.tv_replienum);
        }

    }





}
