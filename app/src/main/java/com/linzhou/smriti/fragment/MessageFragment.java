package com.linzhou.smriti.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.linzhou.smriti.Adapter.ItemOnClick;
import com.linzhou.smriti.Adapter.MymeslvAdapter;
import com.linzhou.smriti.Base.BaseFragment;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.Data.Conversation;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.fragment
 *创建者:  linzhou
 *创建时间:17/07/29
 *描述:   
 */


public class MessageFragment extends BaseFragment {

    private ImageView addfriend;
    private ImageView friendslist;

    private SwipeRefreshLayout mSwipeRefresh;

    private ListView lv_mes;
    private MymeslvAdapter adapter;
    private List<Conversation> mList;


    @Override
    public int getlayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefresh);
        mSwipeRefresh.setRefreshing(true);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefresh.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeRefresh.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小

        lv_mes = (ListView) view.findViewById(R.id.lv_mes);

        addfriend = (ImageView) view.findViewById(R.id.addfriend);

        friendslist = (ImageView) view.findViewById(R.id.friendslist);

        mList = new ArrayList<>();
        adapter = new MymeslvAdapter(getActivity(), mList);

        lv_mes.setAdapter(adapter);

    }

    @Override
    protected void setListener() {

        adapter.setItemOnclick(new ItemOnClick() {
            @Override
            public void Onclick(int i) {
                Toast.makeText(getActivity(), "i:" + i, Toast.LENGTH_SHORT).show();
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getdata();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initData() {

        try {
            getdata();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void getdata() throws IOException {


        OkHttp.asynpost(Url.C_GETCONVERSATIONS, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(str);
                            L.d(str);
                            String result = json.optString(StaticClass.RESULT);
                            switch (result) {
                                case "GetConversations_success":
                                    JSONArray c_json = json.optJSONArray("content");
                                    mList.clear();
                                    for (int i = 0; i < c_json.length(); i++) {
                                        JSONObject c = c_json.optJSONObject(i);
                                        Conversation conversation = new Conversation();
                                        conversation.setId(c.getInt("c_id"))
                                                .setUsername(c.optString("u_name"))
                                                .setTo_u_id(c.optInt("u_id"))
                                                .setHead(c.optString("u_head"))
                                                .setMsg(c.getString("m_content"))
                                                .setTime(c.optInt("m_time"))
                                                .setUnread(c.optInt("m_count"));
                                        mList.add(conversation);
                                    }
                                    adapter.notifyDataSetChanged();
                                    mSwipeRefresh.setRefreshing(false);
                                    break;
                                default:

                                    Toast.makeText(getActivity(), "未知错误导致获取数据失败！", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void error(String str) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "网络异常,获取数据失败，请重试！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }




}
