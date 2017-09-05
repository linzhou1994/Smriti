package com.linzhou.smriti.Adapter;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/08/09
 *描述:   
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linzhou.smriti.Adapter.ViewHolder.AbstractFirstrepViewHolder;
import com.linzhou.smriti.Adapter.ViewHolder.TextFirstrepViewHolder;
import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.R;

import java.util.List;

public class ThmemFirstrepApdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Firstreply> mData;
    private int t_u_id;


    protected RepOnClickListener mRepOnClickListener;


    public ThmemFirstrepApdater(Context context, List<Firstreply> listData) {
        this.mContext = context;
        this.mData = listData;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public ThmemFirstrepApdater(Context context, List<Firstreply> listData,RepOnClickListener mRepOnClickListener) {
        this.mContext = context;
        this.mData = listData;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mRepOnClickListener=mRepOnClickListener;

    }


    public void setRepOnClickListener(RepOnClickListener mRepOnClickListener) {
        this.mRepOnClickListener = mRepOnClickListener;
    }

    public void setData(List<Firstreply> mData,int t_u_id) {
        this.mData = mData;
        this.t_u_id = t_u_id;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.apdater_firstrep_item, parent, false);
        switch (viewType) {
            case AbstractFirstrepViewHolder.TYPE_TEXT:
                return new TextFirstrepViewHolder(view, viewType,mContext,t_u_id);
            //case AbstractFirstrepViewHolder.TYPE_IMAGE:
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) return;
        ((AbstractFirstrepViewHolder) holder).band(mData.get(position),mContext,mRepOnClickListener);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }




}
