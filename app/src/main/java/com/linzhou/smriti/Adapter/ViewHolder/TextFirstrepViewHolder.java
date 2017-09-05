package com.linzhou.smriti.Adapter.ViewHolder;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter.ViewHolder
 *创建者:  linzhou
 *创建时间:17/08/09
 *描述:   
 */


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.linzhou.smriti.Adapter.RepOnClickListener;
import com.linzhou.smriti.Adapter.SecondreplyApdater;
import com.linzhou.smriti.Base.AppConfig;
import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.PicassoUtils;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.utils.UtilTools;

public class TextFirstrepViewHolder extends AbstractFirstrepViewHolder {

    TextView content;


    public TextFirstrepViewHolder(View itemView, int viewType,Context mContext,int uid) {
        super(itemView, viewType,mContext,uid);
    }

    @Override
    public void initSubView( int viewType) {
        if (viewStub == null) {
            throw new IllegalArgumentException("viewStub is null...");
        }

        viewStub.setLayoutResource(R.layout.viewstub_text);
        View subViw = viewStub.inflate();
        content= (TextView) subViw.findViewById(R.id.content);
    }

    @Override
    public void band(Firstreply firstreply, Context mContext, RepOnClickListener repOnClickListener) {

        mRepOnClickListener = repOnClickListener;
        if (!TextUtils.isEmpty(firstreply.getUser().getHead()))
            PicassoUtils.loadImageViewSize(mContext
                    , Url.HTTP_USER_HEAD_URL + firstreply.getUser().getHead()
                    , 45, 45, headimage);

        tv_username.setText(firstreply.getUser().getUsername());
        if (UtilTools.getStartTime().getTime() < firstreply.getTime().getTime())
            tv_firsttime.setText(UtilTools.dateToString(firstreply.getTime(),"HH:mm"));
        else tv_firsttime.setText(UtilTools.dateToString(firstreply.getTime()));

        louzhu_tv.setVisibility(T_u_id==firstreply.getUser().getId()?View.VISIBLE:View.GONE);
        content.setText(firstreply.getContent());
        SecondreplyApdater secondreplyApdater = new SecondreplyApdater(mContext,firstreply,mRepOnClickListener);

        noscrollListview.setAdapter(secondreplyApdater);

        mitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepOnClickListener.onClickListener(firstreply,firstreply.getUser());
            }
        });


    }
}
