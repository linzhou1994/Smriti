package com.linzhou.smriti.Adapter.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.linzhou.smriti.Adapter.RepOnClickListener;
import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.Listview.NoScrollListView;

import de.hdodenhof.circleimageview.CircleImageView;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter.ViewHolder
 *创建者:  linzhou
 *创建时间:17/08/09
 *描述:   
 */


public abstract class AbstractFirstrepViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_TEXT = 1;
    public final static int TYPE_IMAGE = 2;

    protected int T_u_id;

    protected Context mContext;

    protected RepOnClickListener mRepOnClickListener;

    protected CircleImageView headimage;
    protected TextView tv_username;
    protected TextView tv_firsttime;
    protected TextView louzhu_tv;

    //不同的地方--------------------------------------------------------------------
    protected ViewStub viewStub;
    //-----------------------------------------------------------------------------

    protected NoScrollListView noscrollListview;

    protected View mitemView;


    public AbstractFirstrepViewHolder(View itemView, int viewType, Context mContext,int u_id) {
        super(itemView);
        this.mContext = mContext;
        T_u_id=u_id;

        headimage = (CircleImageView) itemView.findViewById(R.id.headimage);
        tv_username = (TextView) itemView.findViewById(R.id.tv_username);
        tv_firsttime = (TextView) itemView.findViewById(R.id.tv_firsttime);
        louzhu_tv= (TextView) itemView.findViewById(R.id.louzhu_tv);
        viewStub = (ViewStub) itemView.findViewById(R.id.content);

        noscrollListview = (NoScrollListView) itemView.findViewById(R.id.noscrollListview);


        mitemView = itemView;
        initSubView(viewType);
    }


    public abstract void initSubView(int viewType);

    /**
     * 数据绑定
     *
     * @param firstreply 一级回复对象
     * @param context    上下文
     * @param repOnClickListener 点击事件监听器
     */
    public abstract void band(Firstreply firstreply, Context context, RepOnClickListener repOnClickListener);

    public void band(Firstreply firstreply, Context context) {
        band(firstreply, context, null);
    }

    ;


}
