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

public abstract class TouchFinishActivity extends BaseActivity {
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
    }


    /**
     * 通过重写该方法，对触摸事件进行处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){// 当按下时
            // 获得按下时的X坐标
            downX = event.getX();

        }else if(event.getAction() == MotionEvent.ACTION_MOVE){// 当手指滑动时
            // 获得滑过的距离
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > 0){// 如果是向右滑动
                decorView.setX(moveDistanceX); // 设置界面的X到滑动到的位置
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){// 当抬起手指时
            // 获得滑过的距离
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > screenWidth / 2){
                // 如果滑动的距离超过了手机屏幕的一半, 结束当前Activity
                finish();
            }else{ // 如果滑动距离没有超过一半
                // 恢复初始状态
                decorView.setX(0);
            }
        }
        return super.onTouchEvent(event);
    }
}
