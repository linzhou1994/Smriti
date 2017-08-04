package com.linzhou.smriti.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cooloongwu.emoji.entity.Emoji;
import com.cooloongwu.emoji.utils.EmojiTextUtils;
import com.linzhou.smriti.Base.BaseActivity;
import com.linzhou.smriti.Base.StaticClass;
import com.linzhou.smriti.R;
import com.linzhou.smriti.View.CustomDialog;
import com.linzhou.smriti.fragment.EmojiFragment;
import com.linzhou.smriti.utils.KeyboardChangeListener;
import com.linzhou.smriti.utils.KeyboardUtils;
import com.linzhou.smriti.utils.L;
import com.linzhou.smriti.utils.OkHttp;
import com.linzhou.smriti.utils.Url;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;


/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Activity
 *创建者:  linzhou
 *创建时间:17/08/03
 *描述:   
 */


public class AddThemeAvtivity extends BaseActivity implements View.OnClickListener
        , EmojiFragment.OnEmojiClickListener {


    private static final String ADD_SUCCESS="Add_success";


    private EditText add_title;
    private EditText add_content;
    private LinearLayout emoji_layout;
    private ImageView emoji;
    private ImageView back;
    private Button add_bt;

    private CustomDialog mAddDialog;

    private boolean isemoji = false;

    private boolean isKeyboard = false;

    private EmojiFragment ef = new EmojiFragment();

    @Override
    protected int getlayout() {
        return R.layout.activity_addtheme;
    }

    @Override
    protected void initview() {

        add_title = (EditText) findViewById(R.id.add_title);
        add_content = (EditText) findViewById(R.id.add_content);
        emoji_layout = (LinearLayout) findViewById(R.id.emoji_layout);
        emoji = (ImageView) findViewById(R.id.emoji);
        back = (ImageView) findViewById(R.id.back);
        add_bt= (Button) findViewById(R.id.add_bt);

        mAddDialog = new CustomDialog(this, 0, 0, R.layout.dialog_loding,
                R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);

        ((TextView)mAddDialog.findViewById(R.id.loding_text)).setText("正在发送");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {


        emoji.setOnClickListener(this);
        add_title.setOnClickListener(this);
        add_content.setOnClickListener(this);
        add_bt.setOnClickListener(this);
        back.setOnClickListener(this);
        ef.addOnEmojiClickListener(this);


        add_title.setOnFocusChangeListener(focusChangeListener);
        add_content.setOnFocusChangeListener(focusChangeListener);





        KeyboardChangeListener softKeyboardStateHelper = new KeyboardChangeListener(this);
        softKeyboardStateHelper.setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow) {
                    //键盘的弹出
                    L.d("键盘的弹出 ");
                    emoji.setVisibility(View.VISIBLE);
                } else {
                    //键盘的收起
                    L.d("键盘的收起 ");
                    if (!isemoji)
                        emoji.setVisibility(View.GONE);
                    
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.add_title:
            case R.id.add_content:
                hideMultiLayout();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.add_bt:
                try {
                    addtheme();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    private void addtheme() throws IOException {
        mAddDialog.show();
        String title = add_title.getText().toString().trim();
        String content = add_content.getText().toString().trim();

        if (title.equals(""))
            Toast.makeText(this,"标题不能为空",Toast.LENGTH_SHORT).show();
        if (title.length()>100)
            Toast.makeText(this,"标题长度不能超过100",Toast.LENGTH_SHORT).show();
        if (content.equals(""))
            Toast.makeText(this,"内容不能为空",Toast.LENGTH_SHORT).show();
        if (content.length()>200)
            Toast.makeText(this,"内容长度不能超过100",Toast.LENGTH_SHORT).show();

        RequestBody addThemebody = new FormBody.Builder()
                .add("title", title)
                .add("content",content)
                .build();

        OkHttp.asynpost(Url.ADDTHEME, addThemebody, new OkHttp.OkHttpListener() {
            @Override
            public void success(String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAddDialog.dismiss();
                        try {
                            JSONObject loginjson = new JSONObject(str);
                            String result = loginjson.optString(StaticClass.RESULT);
                            L.d(result);
                            switch (result) {
                                case ADD_SUCCESS:
                                    Toast.makeText(AddThemeAvtivity.this, "发送成功！", Toast.LENGTH_SHORT).show();
                                    finish();
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
                        mAddDialog.dismiss();
                        Toast.makeText(AddThemeAvtivity.this, "网络异常,发送失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }


    private void showMultiLayout() {
        isemoji = true;
        //显示多功能布局，隐藏键盘
        KeyboardUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        KeyboardUtils.hideKeyboard(getCurrentFocus());
        emoji_layout.setVisibility(View.VISIBLE);

    }

    /**
     * 隐藏多功能布局
     */
    private void hideMultiLayout() {
        L.d("hideMultiLayout");
        isemoji = false;
        emoji_layout.setVisibility(View.GONE);
        KeyboardUtils.updateSoftInputMethod(this, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        KeyboardUtils.showKeyboard(gethasFocusEditText());
    }


    private EditText gethasFocusEditText() {
        if (add_title.hasFocus()) {
            return add_title;
        } else if (add_content.hasFocus()) {
            return add_content;
        } else return null;
    }

    @Override
    public void onEmojiDelete() {
        EditText editText = gethasFocusEditText();
        if (editText == null) return;

        String str = editText.getText().toString();
        int selection = editText.getSelectionStart();
        if (str.isEmpty() || selection == 0) {
            return;
        }
        String text1 = str.substring(0, selection);
        String text2 = str.substring(selection, str.length());
        if (!text1.substring(selection - 1).equals("]")) {
            text1 = text1.substring(0, selection - 1);
            text1 = text1 + text2;
            editText.setText(EmojiTextUtils.getEditTextContent(text1, this, editText));
            editText.setSelection(selection - 1);
        } else {
            L.d("text1:" + text1 + "  text2:" + text2 + " selection:" + selection);
            int index = text1.lastIndexOf("[");
            L.d("index:" + index);

            text1 = text1.substring(0, index);
            text1 = text1 + text2;


            editText.setText(EmojiTextUtils.getEditTextContent(text1, this, editText));
            editText.setSelection(index);
        }
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        EditText editText = gethasFocusEditText();
        if (editText == null) return;

        int selection = editText.getSelectionStart();
        String str = editText.getText().toString();
        String text = str.substring(0, selection) + emoji.getContent()
                + str.substring(selection, str.length());
        editText.setText(EmojiTextUtils.getEditTextContent(text, this, editText));
        editText.setSelection(selection + emoji.getContent().length());
    }



    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                hideMultiLayout();
            } else {
                hideMultiLayout();
            }

        }
    };


}
