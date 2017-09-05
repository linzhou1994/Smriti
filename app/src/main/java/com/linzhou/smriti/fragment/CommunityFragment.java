package com.linzhou.smriti.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linzhou.smriti.Activity.AddThemeAvtivity;
import com.linzhou.smriti.Activity.LoginActivity;
import com.linzhou.smriti.Activity.ThemeDetailActivity;
import com.linzhou.smriti.Activity.ThemeSearchActivity;
import com.linzhou.smriti.Adapter.CommunityApdater;
import com.linzhou.smriti.Adapter.ItemOnClick;
import com.linzhou.smriti.Base.AppConfig;
import com.linzhou.smriti.Base.BaseFragment;
import com.linzhou.smriti.Base.MouldAdapter;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;
import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.JsonToClass;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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


public class CommunityFragment extends BaseFragment implements View.OnClickListener{

    /**
     * 获取数据成功
     */
    private static final String THEMES_SUCCESS="GetThemes_success";

    private TextView tv_profession;
    private ListView mListView;
    private CommunityApdater mApdater;
    private SwipeRefreshLayout mSwipeRefresh;
    private ImageView search;
    private FloatingActionButton addtheme;

    private List<Theme> mThemes = null;


    @Override
    public int getlayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefresh);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefresh.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeRefresh.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小


        tv_profession= (TextView) view.findViewById(R.id.tv_profession);
        mListView= (ListView) view.findViewById(R.id.elist);
        search = (ImageView) view.findViewById(R.id.search);
        addtheme = (FloatingActionButton) view.findViewById(R.id.addtheme);

        tv_profession.setText(AppConfig.mUser.getProfession().getPName());
        mThemes = new ArrayList<>();
        mApdater = new CommunityApdater(getActivity(),mThemes);
        mListView.setAdapter(mApdater);
    }

    @Override
    protected void initData()  {

        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void setListener() {

        search.setOnClickListener(this);
        addtheme.setOnClickListener(this);

        mApdater.setItemOnclick(new ItemOnClick() {
            @Override
            public void Onclick(int i) {
                Intent intent = new Intent(getActivity(), ThemeDetailActivity.class);
                intent.putExtra("tid",mThemes.get(i).getId());
                startActivity(intent);
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    getData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getData() throws IOException {
        mSwipeRefresh.setRefreshing(true);

        List<Theme> date = new ArrayList<>();


        OkHttp.asynpost(Url.GETTHEMES, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject Themejson = new JSONObject(str);
                            String result = Themejson.optString(StaticClass.RESULT);
                            switch (result) {
                                case THEMES_SUCCESS:
                                    getThemes(Themejson.optJSONArray(StaticClass.CONTENT));
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

    private void getThemes(JSONArray jsonArray) throws JSONException {
        mThemes.clear();

        for (int i =0;i<jsonArray.length();i++)
            mThemes.add(JsonToClass.JsonToTheme(jsonArray.optJSONObject(i)));


        mApdater.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.search:
                intent= new Intent(getActivity(),ThemeSearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
            case R.id.addtheme:
                intent = new Intent(getActivity(),AddThemeAvtivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;

        }
    }
}
