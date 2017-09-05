package com.linzhou.smriti.View.Progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.linzhou.smriti.R;


/*
 *项目名： MyView
 *包名：   com.linzhou.myview
 *创建者:  linzhou
 *创建时间:17/08/05
 *描述:   
 */

public class MyProgress extends View {

    private static final int LINE_HEIGHT = 2;//进度条高度;dp
    private static final int TEXT_SIZE = 13;//显示进度的字体的大小；sp
    private static final int FINISHLINE_COLOR = 0xFFFF0000;//完成部分进度的颜色
    private static final int UNFINISHLINE_COLOR = 0xFF00FFFF;//未完成部分进度的颜色
    private static final int TEXT_COLOR = FINISHLINE_COLOR;//字体的颜色
    private static final int PROGRESS = 0;//进度；

    private int mLineHeight = dptopx(LINE_HEIGHT);
    private int mTextSize = sptopx(TEXT_SIZE);
    private int mFinishLineColor = FINISHLINE_COLOR;
    private int mUnFinishLineColor = UNFINISHLINE_COLOR;
    private int mTextColor = TEXT_COLOR;
    private float mProgress =  PROGRESS;

    private Paint mPaint ;

    public MyProgress(Context context) {
        this(context,null);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inint(attrs);

    }

    private void inint(AttributeSet attrs) {

        mPaint = new Paint();

        obtainStyledAttr(attrs);

        mPaint.setTextSize(mTextSize);
    }

    /**
     * 获取自定义属性
     * @param attrs
     */
    private void obtainStyledAttr(AttributeSet attrs) {
        TypedArray t = getContext()
                .obtainStyledAttributes(attrs, R.styleable.MyProgress);

        mLineHeight= (int) t.getDimension(
                R.styleable.MyProgress_progress_lineheight,mLineHeight);

         mTextSize= (int) t.getDimension(
                 R.styleable.MyProgress_progress_textsize,mTextSize);

        mFinishLineColor = t.getColor(
                R.styleable.MyProgress_progress_finishlinecolor,mFinishLineColor);

        mUnFinishLineColor = t.getColor(
                R.styleable.MyProgress_progress_unfinishlinecolor,mUnFinishLineColor);

        mTextColor = t.getColor(
                R.styleable.MyProgress_progress_textcolort,mTextColor);

        mProgress = t.getFloat(
                R.styleable.MyProgress_progress,mProgress);

        t.recycle();
    }

    public int getprogress() {
        return (int) (mProgress*100);
    }

    public void setprogress(float progress) {
        this.mProgress = progress;
        //通知view重绘
        invalidate();
    }

    /**
     * dp转px
     * @param dp
     * @return
     */
    private int dptopx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param sp
     * @return
     */
    private int sptopx(int sp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heitht = getmeasure(heightMeasureSpec);
        //通知view测量完毕，并将数据传给它
        setMeasuredDimension(width,heitht);
    }

    /**
     * 计算view的高度
     * @param heightMeasureSpec
     * @return view的高度
     */
    private int getmeasure(int heightMeasureSpec) {

        int result = 0;

        int Mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        switch (Mode){
            case MeasureSpec.EXACTLY://用户给定精确值
                result = size;
                break;
            case MeasureSpec.UNSPECIFIED://父容器对于子容器没有任何限制,子容器想要多大就多大
                //测量文字的高度
                int textHeight = (int) (mPaint.descent()+mPaint.ascent());
                //获取文字和进度条两者高度的最大值
                result = getPaddingBottom()+getPaddingTop()+Math.max(textHeight,mLineHeight);
                break;
            case MeasureSpec.AT_MOST://子容器可以是声明大小内的任意大小
                int textheight = (int) (mPaint.descent()+mPaint.ascent());
                int maxHeight = getPaddingBottom()+getPaddingTop()+Math.max(textheight,mLineHeight);
                //获取计算出来的高度和实际可用最大高度的最小值
                result = Math.min(maxHeight,size);
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getPaddingLeft(),getHeight()/2);

        String str = " "+getprogress()+"% ";
        int strWidth = (int) mPaint.measureText(str);
        int width = getWidth()-getPaddingLeft()-getPaddingRight()-strWidth;
        int drawToX = width*getprogress()/100;
        //已经完成的部分
        if (getprogress()!=0){
            mPaint.setColor(mFinishLineColor);
            mPaint.setStrokeWidth(mLineHeight);
            canvas.drawLine(0,0,drawToX,0,mPaint);
        }
        //进度文字
        mPaint.setColor(mTextColor);
        int y = (int) ((mPaint.descent()+mPaint.ascent())/2);
        canvas.drawText(str,drawToX,-y,mPaint);
        //未完成部分
        if (getprogress()!=100){
            mPaint.setColor(mUnFinishLineColor);
            mPaint.setStrokeWidth(mLineHeight);
            canvas.drawLine(drawToX+strWidth,0,width+strWidth,0,mPaint);
        }
    }
}
