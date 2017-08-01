package com.linzhou.smriti.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.linzhou.smriti.Adapter.CommunityApdater;
import com.linzhou.smriti.Base.AppConfig;
import com.linzhou.smriti.Base.BaseFragment;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.fragment
 *创建者:  linzhou
 *创建时间:17/07/29
 *描述:   
 */


public class CommunityFragment extends BaseFragment {

    private TextView tv_profession;
    private ListView elist;
    private CommunityApdater mApdater;
    private SwipeRefreshLayout mSwipeRefresh;


    @Override
    public int getlayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {
        tv_profession= (TextView) view.findViewById(R.id.tv_profession);
        elist= (ListView) view.findViewById(R.id.elist);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefresh);

        tv_profession.setText(AppConfig.mUser.getProfession().getPName());

    }

    @Override
    protected void initData() {
        List<Theme> list = getdate();

        mApdater = new CommunityApdater(getActivity(),list);
        elist.setAdapter(mApdater);
    }

    @Override
    protected void setListener() {

    }

    public List<Theme> getdate() {
        List<Theme> date = new ArrayList<>();
        for (int i =0;i<=9;i++){
            Theme theme = new Theme();
            theme.setTitle("title"+i);
            theme.setFirsttime(new Date());
            theme.setContent("content content content content content"+i);
            theme.setReplienum(i);
            User user=new User();
            user.setUsername("username:"+i);
            theme.setUser(user);
            date.add(theme);
        }
        return date;
    }
}
