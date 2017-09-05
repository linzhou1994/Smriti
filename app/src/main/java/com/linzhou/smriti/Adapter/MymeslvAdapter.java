package com.linzhou.smriti.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.linzhou.smriti.Base.MouldAdapter;
import com.linzhou.smriti.Data.Conversation;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.PicassoUtils;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.utils.UtilTools;
import java.util.Date;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Adapter
 *创建者:  linzhou
 *创建时间:17/09/03
 *描述:   
 */


public class MymeslvAdapter extends MouldAdapter {

    protected ItemOnClick mItemOnclick;

    public MymeslvAdapter(Context context, List list) {
        super(context, list);
    }


    public void setItemOnclick(ItemOnClick mOnClickListener) {
        this.mItemOnclick = mOnClickListener;
    }

    public void removeItemOnclick() {
        this.mItemOnclick = null;
    }

    @Override
    protected int setLayout() {
        return R.layout.apdater_message_lv_item;
    }

    @Override
    protected ViewHolder setViewHolder(View convertView, int position) {
        return new MymeslvViewHolder(mContext, convertView, (Conversation) mList.get(position), mItemOnclick);
    }

    @Override
    protected void updateViewHolder(ViewHolder viewHolder) {
        ((MymeslvViewHolder) viewHolder).setmItemOnclick(mItemOnclick);
    }

    static class MymeslvViewHolder extends MouldAdapter.ViewHolder {

        private Conversation conversation;
        public View convertView;
        public CircleImageView profile_image;//用户头像
        public TextView tv_name;//用户名称
        public TextView tv_mes;//最后一条消息内容
        public TextView tv_time;//最后一条时间
        public TextView mesnew;//未读消息数

        private Context mContext;

        private ItemOnClick mItemOnclick;


        public MymeslvViewHolder(Context mContext, View view, Conversation conversation, ItemOnClick mItemOnclick) {
            this.convertView = view;
            profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_mes = (TextView) convertView.findViewById(R.id.tv_mes);
            tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            mesnew = (TextView) convertView.findViewById(R.id.mesnew);

            this.conversation = conversation;
            this.mItemOnclick = mItemOnclick;

            this.mContext = mContext;
        }

        public ItemOnClick getmItemOnclick() {
            return mItemOnclick;
        }

        public void setmItemOnclick(ItemOnClick mItemOnclick) {
            this.mItemOnclick = mItemOnclick;
        }


        public void bandData(Object o) {
            if (conversation != null) {
                if (!TextUtils.isEmpty(conversation.getHead())) {
                    PicassoUtils.loadImageViewSize(mContext
                            , Url.HTTP_USER_HEAD_URL + conversation.getHead()
                            , 45, 45, profile_image);
                }
                tv_name.setText(conversation.getUsername());
                tv_mes.setText(conversation.getMsg());
                if (UtilTools.getStartTime().getTime() < conversation.getTime()) {
                    tv_time.setText(UtilTools.dateToString(new Date(conversation.getTime()), "HH:mm"));
                } else {
                    tv_time.setText(UtilTools.dateToString(new Date(conversation.getTime())));
                }


                if (conversation.getUnread() > 0 ){
                    mesnew.setText(conversation.getUnread() + "");
                } else{
                    mesnew.setVisibility(View.INVISIBLE);
                }


            }
        }

        @Override
        public void setListener(int position) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.d(mItemOnclick == null ? "null" : "nonull");
                    if (mItemOnclick != null)
                        mItemOnclick.Onclick(position);
                }
            });
        }

    }


}
