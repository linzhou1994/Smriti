package com.linzhou.smriti.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.Data.Secondreply;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.Listview.BaseAdapter;
import com.linzhou.smriti.utils.UtilTools;

import java.util.List;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/08/10
 *描述:   
 */


public class SecondreplyApdater extends BaseAdapter {

    protected RepOnClickListener mRepOnClickListener;
    protected Firstreply firstreply;


    public SecondreplyApdater(Context context, Firstreply firstreply, RepOnClickListener mRepOnClickListener) {
        super(context, firstreply.getSecondreplies());
        this.mRepOnClickListener=mRepOnClickListener;
        this.firstreply = firstreply;
    }

    @Override
    protected View getView(int position) {
        if(mInflater == null){
            mInflater = LayoutInflater.from(mContext);
        }
        View convertView = mInflater.inflate(R.layout.apdater_secondreply_item, null, false);

        TextView username = (TextView) convertView.findViewById(R.id.username);
//        TextView tousername = (TextView) convertView.findViewById(R.id.tousername);
//        TextView content = (TextView) convertView.findViewById(R.id.content);

        Secondreply secondreply = (Secondreply) getItem(position);

        int len;//记录长度
        //初始化对象
        SpannableStringBuilder sb = new SpannableStringBuilder();
        //发送人
        sb.append(secondreply.getuser().getUsername());
        sb.setSpan(new ForegroundColorSpan(Color.BLUE),//字体颜色
                0, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        len = sb.length();
        //回复
        sb.append("回复");
        sb.setSpan(new ForegroundColorSpan(Color.GRAY),//字体颜色
                len, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        len = sb.length();
        //回复对象
        sb.append(secondreply.gettouser().getUsername());
        sb.setSpan(new ForegroundColorSpan(Color.BLUE),//字体颜色
                len, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        len = sb.length();
        //内容
        sb.append(UtilTools.ToDBC("："+secondreply.getContext()));
        sb.setSpan(new ForegroundColorSpan(Color.GRAY),//字体颜色
                len, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        username.setText(sb);



//        tousername.setText(secondreply.gettouser().getUsername());
//        content.setText(secondreply.getContext());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRepOnClickListener!=null)
                    mRepOnClickListener.onClickListener(firstreply,secondreply.getuser());
            }
        });

        return convertView;
    }


}
