package com.linzhou.smriti.Activity;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linzhou.smriti.Adapter.CommunityApdater;
import com.linzhou.smriti.Adapter.ItemOnClick;
import com.linzhou.smriti.Base.BaseActivity;
import com.linzhou.smriti.Base.MouldAdapter;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.Base.TouchFinishActivity;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.CustomDialog;
import com.linzhou.smriti.utils.JsonToClass;
import com.linzhou.smriti.utils.KeyboardUtils;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Activity
 *创建者:  linzhou
 *创建时间:17/08/02
 *描述:   
 */


public class ThemeSearchActivity extends TouchFinishActivity implements View.OnClickListener {


    /**
     * 获取数据成功
     */
    private static final String SEARCH_THEMES_SUCCESS = "Search_success";


    private ImageView back;
    private EditText et_search;
    private TextView search;
    private TextView search_null;
    private ListView search_theme;

    private CustomDialog mSearchDialog;

    private CommunityApdater mApdater;

    private List<Theme> mThemes = null;


    @Override
    protected int getlayout() {
        return R.layout.activity_themesearch;
    }

    @Override
    protected void initview() {
        super.initview();

        back = (ImageView) findViewById(R.id.back);
        et_search = (EditText) findViewById(R.id.et_search);
        search = (TextView) findViewById(R.id.search);
        search_null = (TextView) findViewById(R.id.search_null);
        search_theme = (ListView) findViewById(R.id.search_theme);

        mSearchDialog = new CustomDialog(this, 0, 0, R.layout.dialog_loding,
                R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);

        ((TextView)mSearchDialog.findViewById(R.id.loding_text)).setText("正在查询");

        mThemes = new ArrayList<>();
        mApdater = new CommunityApdater(this, mThemes,true);
        search_theme.setAdapter(mApdater);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        back.setOnClickListener(this);
        search.setOnClickListener(this);

        mApdater.setItemOnclick(new ItemOnClick() {
            @Override
            public void Onclick(int i) {
                Toast.makeText(ThemeSearchActivity.this
                        , "点击了：" + i
                        , Toast.LENGTH_SHORT).show();
            }
        });

        search_theme.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search:
                String str = et_search.getText().toString();
                if (TextUtils.isEmpty(str))
                    return;
                try {
                    startsearch(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mSearchDialog.show();
                KeyboardUtils.hideKeyboard(et_search);
                break;
        }
    }

    private void startsearch(String str) throws IOException {

        RequestBody loginbody = new FormBody.Builder()
                .add("content", str)
                .build();

        OkHttp.asynpost(Url.SEARCH_THEME, loginbody, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject Themejson = new JSONObject(str);
                            String result = Themejson.optString(StaticClass.RESULT);
                            switch (result) {
                                case SEARCH_THEMES_SUCCESS:
                                    getThemes(Themejson.optJSONArray(StaticClass.CONTENT));
                                    break;
                                default:
                                    search_null.setVisibility(View.VISIBLE);
                                    search_theme.setVisibility(View.GONE);
                                    search_null.setText("无搜索结果");
                                    //Toast.makeText(ThemeSearchActivity.this, "获取数据失败！", Toast.LENGTH_SHORT).show();
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ThemeSearchActivity.this, "网络异常,获取数据失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getThemes(JSONArray jsonArray) throws JSONException {
        mThemes.clear();

        for (int i = 0; i < jsonArray.length(); i++)
            mThemes.add(JsonToClass.JsonToTheme(jsonArray.optJSONObject(i)));


        mApdater.notifyDataSetChanged();
        search_null.setVisibility(View.GONE);
        search_theme.setVisibility(View.VISIBLE);
        mSearchDialog.dismiss();

    }


}
