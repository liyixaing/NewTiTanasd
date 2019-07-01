package lanjing.com.titan.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import lanjing.com.titan.R;

/**
 * Created by chenxi on 2019/5/11.
 */

public class UnfindSeekBar extends View {

    private Context context;//全局上下文
    private int TEXT_SIZE = 50;//显示白粉的字体的大小
    private float left = 0;//seekbar滑动的位置（滑块的滑动纯靠它来控制）
    private double SEEK_NUM = 0;//滑动的具体位置
    private Paint paint;//画笔
    private Bitmap bitmap;//滑块的样式


    public UnfindSeekBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public UnfindSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public UnfindSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    //初始化
    private void init() {
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setTextSize(TEXT_SIZE);//设置显示的尺寸
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.seek_pic);//初始化默认的滑块的样式
    }


    //测量的方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        RectF rectF = new RectF(40, 35, 540, 45);//底部显示的条是一个高度为10的矩形（这个位置是不变的，所以可以写死）
        canvas.drawRect(rectF, paint);
        canvas.drawBitmap(bitmap, left, 0, paint);//绘制底部的条
        canvas.drawText(cal(), left, 150, paint);//绘制滑块
    }

    //计算百分比(500是我写死的滑块的总宽度大小)
    private String cal() {
        if (left == 0) {
            return "0%";
        }
        SEEK_NUM = left / 500 * 100;
        if (SEEK_NUM < 1) {
            return "0" + new java.text.DecimalFormat("#.0").format(SEEK_NUM) + "%";
        }
        return new java.text.DecimalFormat("#.0").format(SEEK_NUM) + "%";
    }


    //重写OnTouchEvent事件，来控制滑块的滑动，其实利用的原理及时在MOVE事件中，不停的获知手指的位置，然后设置left的值，然后不停的重绘界面
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("滑动的x坐标", "=======" + event.getX());
                float downX = event.getX();
                if (downX >= left && downX <= (left + 80)) {//判断手指按下的时候，是否按到滑块（这个80是我默认的滑块的图标的大小）
                    bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.seek_bar_white);//如果手指按下的时候，在滑块的位置范围内，那么就设置成为按住的滑块的样式
                    left = event.getX();
                    if (left > 500) {
                        left = 500;
                    }
                    invalidate();//重绘界面
                    return true;//这里要返回true move事件才会执行，否则执行这个onTouchEvent的最后一行的代码，事件不在消费事件
                } else {
                    return false;
                }
            case MotionEvent.ACTION_MOVE://手指移动
                Log.e("滑动的x坐标", "=======" + event.getX());
                left = event.getX();
                if (left > 500) {
                    left = 500;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP://手指离开
                left = event.getX();
                if (left > 500) {
                    left = 500;
                } else if (left < 0) {
                    left = 0;
                }
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.seek_pic);//手指离开画布设置默认的显示的样式
                invalidate();//重绘界面
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    //获取现在的滑动的进度（最小为0，最大为100）
    public double getSEEK_NUM() {
        return SEEK_NUM;
    }


}
