package com.lxh.baselibray.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.R;
import com.lxh.baselibray.util.SizeUtils;


public class TitleView extends LinearLayout {

    private TextView mTvTitle;
    private ImageView mIvBack;
    private TextView mTvRight;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(final Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        boolean   ivBackIsVisiable=typedArray.getBoolean(R.styleable.TitleView_ivLeftVisiable,true);
        String  titleText=typedArray.getString(R.styleable.TitleView_tvTitleText);
        int color=typedArray.getColor(R.styleable.TitleView_titleTextColor, Color.parseColor("#2B2B2B"));
        int rightColor=typedArray.getColor(R.styleable.TitleView_rightTextColor, Color.parseColor("#2B2B2B"));
        int resourceId = typedArray.getResourceId(R.styleable.TitleView_ivLeftPic, R.drawable.icon_back);
        String rightText = typedArray.getString(R.styleable.TitleView_rightText);
        typedArray.recycle();

        View viewRoot = LayoutInflater.from(context).inflate(R.layout.view_titile, this);
        mIvBack=viewRoot.findViewById(R.id.iv_back);
        mTvTitle=viewRoot.findViewById(R.id.tv_title);
        mTvRight=viewRoot.findViewById(R.id.tv_right);

        mIvBack.setVisibility(ivBackIsVisiable==true?VISIBLE:GONE);
        mIvBack.setImageResource(resourceId);
        mTvTitle.setText(titleText);
        mTvTitle.setTextColor(color);
        mIvBack.setOnClickListener((View v) -> {
            ((Activity) context).finish();
        });
        mTvRight.setText(rightText);
        mTvRight.setTextColor(rightColor);
    }
    public void setTitleText(String s){
        mTvTitle.setText(s);
    }

    public void setRightOnclickListener(OnClickListener onclickListener){
        mTvRight.setOnClickListener(onclickListener);
    }

}
