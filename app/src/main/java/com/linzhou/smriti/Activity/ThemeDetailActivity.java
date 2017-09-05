package com.linzhou.smriti.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cooloongwu.emoji.utils.EmojiTextUtils;
import com.linzhou.smriti.Adapter.RepOnClickListener;
import com.linzhou.smriti.Adapter.ThmemFirstrepApdater;
import com.linzhou.smriti.Base.AppConfig;
import com.linzhou.smriti.Base.BaseActivity;
import com.linzhou.smriti.Data.Firstreply;
import com.linzhou.smriti.Data.Secondreply;
import com.linzhou.smriti.Data.Theme;
import com.linzhou.smriti.Data.User;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.CustomDialog;
import com.linzhou.smriti.fragment.EmojiFragment;
import com.linzhou.smriti.utils.JsonToClass;
import com.linzhou.smriti.utils.KeyboardUtils;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.PicassoUtils;
import com.linzhou.smriti.utils.Url;
import com.linzhou.smriti.utils.UtilTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.RequestBody;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Activity
 *创建者:  linzhou
 *创建时间:17/08/06
 *描述:   贴子详情页
 */


public class ThemeDetailActivity extends BaseActivity implements View.OnClickListener {

    //一级回复标志
    private static int FIRST_REPLY = 1;
    //二级回复标志
    private static int SECOND_REPLY = 2;

    private Theme mTheme = new Theme();

    public CircleImageView eimage;//用户头像
    public TextView tv_username;//用户名称
    public TextView tv_firsttime;//发布时间
    public TextView tv_title;//标题
    public TextView tv_content;//内容

    public EditText et_text;
    public ImageButton imgbtn_send;
    public ImageView reply_img;
    public ImageView emoji;
    public ImageView back;
    private LinearLayout emoji_layout;

    private EmojiFragment ef = new EmojiFragment();

    private RecyclerView mRecyclerView;

    private ThmemFirstrepApdater apdater;

    //回复键盘
    private CustomDialog mReplyDialog;

    //回复类型
    private int reply = FIRST_REPLY;


    @Override
    protected int getlayout() {
        return R.layout.activity_themedetail;
    }

    @Override
    protected void initview() {
        Intent i = getIntent();
        mTheme.setId(i.getIntExtra("tid", -1));
        if (mTheme.getId() == -1) {
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
            finish();
        }
        eimage = (CircleImageView) findViewById(R.id.eimage);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_firsttime = (TextView) findViewById(R.id.tv_firsttime);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

        mRecyclerView = (RecyclerView) findViewById(R.id.firstrep);

        apdater = new ThmemFirstrepApdater(this, mTheme.getFirstreplylies());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(apdater);

        mReplyDialog = new CustomDialog(this, 0, 0, R.layout.dialog_reply_themedetail,
                R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);

        et_text = (EditText) mReplyDialog.findViewById(R.id.et_text);
        imgbtn_send = (ImageButton) mReplyDialog.findViewById(R.id.imgbtn_send);
        emoji = (ImageView) mReplyDialog.findViewById(R.id.emoji);
        reply_img= (ImageView) findViewById(R.id.reply);
        back= (ImageView) findViewById(R.id.back);
        emoji_layout = (LinearLayout) mReplyDialog.findViewById(R.id.emoji_layout);

    }

    @Override
    protected void initData() {
        try {
            getdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Firstreply freply;

    private User touser;

    @Override
    protected void setListener() {
        //二级回复点击事件监听
        apdater.setRepOnClickListener(new RepOnClickListener() {

            @Override
            public void onClickListener(Firstreply firstreply, User user) {
                reply = SECOND_REPLY;
                freply = firstreply;
                touser = user;
                mReplyDialog.show();
            }
        });
        //输入框dialog消失监听，恢复相关数据
        mReplyDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (reply == SECOND_REPLY) {
                    reply = FIRST_REPLY;
                    freply = null;
                    touser = null;
                }
                hideMulti();
            }
        });

        imgbtn_send.setOnClickListener(this);
        emoji.setOnClickListener(this);
        reply_img.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    //当前表情键盘是否处于打开状态标记
    private boolean isemoji = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_send:

                String context = et_text.getText().toString().trim();
                L.d("imgbtn_send---------"+(context==null ? "null":context));
                if (TextUtils.isEmpty(context) && context != null) return;
                if (reply == FIRST_REPLY) {//一级回复
                    try {
                        addFirstReply(mTheme.getId(), context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (reply == SECOND_REPLY) {//二级回复
                    try {
                        addSecondReply(context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mReplyDialog.dismiss();
                et_text.setText("");
                //不管是一级回复还是二级回复操作，结束或都重置成默认一级回复状态
                reply = FIRST_REPLY;
                break;
            case R.id.emoji:
                if (!isemoji) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.emoji_layout, ef);
                    fragmentTransaction.commit();
                    showMultiLayout();
                } else {
                    hideMultiLayout();
                }
                break;
            case R.id.reply:
                reply=FIRST_REPLY;
                mReplyDialog.show();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    //添加二级级回复
    private void addSecondReply(String content) throws IOException {
        RequestBody request = new FormBody
                                .Builder()
                                .add("id", freply.getId() + "")
                                .add("touserid", touser.getId() + "")
                                .add("content", content)
                                .build();
        OkHttp.asynpost(Url.ADD_SECOND_REPLY, request, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        L.d(str);
                        if (freply != null) {
                            Secondreply secondreply = new Secondreply();
                            secondreply.setuser(AppConfig.mUser);
                            secondreply.setContext(content);
                            secondreply.settouser(touser);
                            freply.getSecondreplies().add(secondreply);
                            apdater.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void error(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ThemeDetailActivity.this, "网络异常，评论失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //添加文字一级回复
    private void addFirstReply(int t_id, String content) throws IOException {
        RequestBody request = new FormBody
                                .Builder()
                                .add("t_id", t_id + "")
                                .add("content", content)
                                .add("type", "1")
                                .build();
        OkHttp.asynpost(Url.ADD_FRIST_REPLY, request, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        L.d(str);
                        Firstreply f = new Firstreply();
                        f.setUser(AppConfig.mUser);
                        f.setContent(content);
                        f.setType(1);
                        f.setTime(new Date());
                        mTheme.getFirstreplylies().add(f);
                        apdater.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void error(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ThemeDetailActivity.this, "网络异常，评论失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /**
     * 隐藏键盘，隐藏多功能布局
     */
    private void hideMulti() {
        KeyboardUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        KeyboardUtils.hideKeyboard(getCurrentFocus());
        isemoji = false;
        emoji_layout.setVisibility(View.GONE);
    }


    /**
     * 显示多功能布局，隐藏键盘
     */
    private void showMultiLayout() {
        isemoji = true;

        KeyboardUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        KeyboardUtils.hideKeyboard(getCurrentFocus());
        emoji_layout.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏多功能布局,显示键盘
     */
    private void hideMultiLayout() {
        L.d("hideMultiLayout");
        isemoji = false;
        emoji_layout.setVisibility(View.GONE);
        KeyboardUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        KeyboardUtils.showKeyboard(getCurrentFocus());
    }

    public void getdata() throws IOException {

        RequestBody request = new FormBody
                                    .Builder()
                                    .add("t_id", mTheme.getId() + "")
                                    .build();

        OkHttp.asynpost(Url.GETTHEME, request, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            L.d(str);
                            JSONObject json = new JSONObject(str);
                            switch (json.optString("result")) {
                                case "Theme_detail_success":
                                    mTheme = JsonToClass.JsonToTheme(json.optJSONObject("content"));
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setdata();
                    }
                });
            }

            @Override
            public void error(String str) {

            }
        });
    }

    private void setdata() {

        if (!TextUtils.isEmpty(mTheme.getUser().getHead()))
            PicassoUtils.loadImageViewSize(this
                                           , Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead()
                                           , 45, 45, eimage);
        L.d("head:        " + Url.HTTP_USER_HEAD_URL + mTheme.getUser().getHead());
        tv_title.setText(EmojiTextUtils.getEditTextContent(mTheme.getTitle(), this, tv_title));
        tv_username.setText(mTheme.getUser().getUsername());
        if (UtilTools.getStartTime().getTime() < mTheme.getFirsttime().getTime())
            tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime(), "HH:mm"));
        else tv_firsttime.setText(UtilTools.dateToString(mTheme.getFirsttime()));
        tv_content.setText(EmojiTextUtils.getEditTextContent(mTheme.getContent(), this, tv_content));
        apdater.setData(mTheme.getFirstreplylies(),mTheme.getUser().getId());
    }


}
