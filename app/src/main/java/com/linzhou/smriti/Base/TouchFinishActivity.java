package com.linzhou.smriti.Base;

/*
 *项目名： Smriti
 *包名：   com.linzhou.smriti.Base
 *创建者:  linzhou
 *创建时间:17/07/25
 *描述:   
 */


import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.linzhou.smriti.R;
import com.linzhou.smriti.utils.KeyboardUtils;
import com.linzhou.smriti.utils.L;

public abstract class TouchFinishActivity extends BaseActivity implements View.OnTouchListener {
    /**
     * 整个Activity视图的根视图
     */
    protected View decorView;

    /**
     * 手指按下时的坐标
     */
    protected float downX, downY;

    /**
     * 手机屏幕的宽度和高度
     */
    protected float screenWidth, screenHeight;


    @Override
    protected void initview() {
        // 获得decorView
        decorView = getWindow().getDecorView();
        // 获得手机屏幕的宽度和高度，单位像素
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        downX = screenWidth;
    }


    /**
     * 通过重写该方法，对触摸事件进行处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchFinish(event);
        return super.onTouchEvent(event);
    }

    public void touchFinish(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {// 当按下时
            // L.d("ACTION_DOWN-----------activity");
            // 获得按下时的X坐标
            downX = event.getX();
            L.d(downX + "----------------------downX");

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {// 当手指滑动时
            //L.d("ACTION_MOVE-----------activity");
            // 获得滑过的距离
            if (downX == screenWidth)
                downX = event.getX();
            float moveDistanceX = event.getX() - downX;
            //L.d(moveDistanceX+"----------------------moveDistanceX");
            if (moveDistanceX > 200) {// 如果是向右滑动
                decorView.setX(moveDistanceX-200); // 设置界面的X到滑动到的位置
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP) {// 当抬起手指时
            L.d("ACTION_UP-----------activity");
            // 获得滑过的距离
            float moveDistanceX = event.getX() - downX;
            L.d(moveDistanceX + "     " + event.getX() + "   " + downX + "----------------------moveDistanceX");
            if (moveDistanceX > screenWidth / 2) {
                // 如果滑动的距离超过了手机屏幕的一半, 结束当前Activity
                finish();
                overridePendingTransition(0, 0);
            } else { // 如果滑动距离没有超过一半
                // 恢复初始状态
                decorView.setX(0);
            }

            downX = screenWidth;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        touchFinish(event);
        return false;
    }


}
