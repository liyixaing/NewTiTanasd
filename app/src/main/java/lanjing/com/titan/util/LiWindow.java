package lanjing.com.titan.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/12 0012.
 */

public class LiWindow {
    private LiWindow mLiWindow;
    private PopupWindow mPopupWindow;
    private LayoutInflater inflater;
    private View mView;
    private Context mContext;
    private WindowManager show;
    WindowManager.LayoutParams context;
    private Map<String,Object> mMap = new HashMap<>();

    public Map<String, Object> getmMap() {
        return mMap;
    }

    public LiWindow(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        mLiWindow = this;
    }

    public LiWindow(Context context, Map<String,Object> map){
        this.mContext = context;
        this.mMap = map;
        inflater = LayoutInflater.from(context);
    }
    //中间显示
    public void showCenterPopupWindow(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }

    //中间显示
    public void showCenterPopupWindowPwd(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗
        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }

    //中间显示
    public void showCenterMatchParent(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.setOutsideTouchable(true);//点击空白处不关闭弹窗

        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }

    //底部显示
    public void showBottomPopupWindow(View mView) {
        mPopupWindow = new PopupWindow(mView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mView);
        mPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha(0.5f,mContext);
        WindowManager.LayoutParams nomal = ((Activity) mContext).getWindow().getAttributes();
        nomal.alpha = 0.5f;
        ((Activity) mContext).getWindow().setAttributes(nomal);
        mPopupWindow.setOnDismissListener(closeDismiss);
    }

    public static void setBackgroundAlpha(float bgAlpha,Context mContext){
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    /**
     * 设置弹窗动画
     * @param animId
     * @return showPopu
     */
    public LiWindow setAnim(int animId) {
        if (mPopupWindow != null) {
            mPopupWindow.setAnimationStyle(animId);
        }
        return mLiWindow;
    }

    //弹窗消失时关闭阴影
    public PopupWindow.OnDismissListener closeDismiss = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            WindowManager.LayoutParams nomal = ((Activity)mContext).getWindow().getAttributes();
            nomal.alpha = 1f;
            ((Activity)mContext).getWindow().setAttributes(nomal);
        }
    };
    public void closePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
    /*
        使用方法
    *   LiWindow liWindow = new LiWindow(MainActivity.this);
        View mView = LayoutInflater.from(MainActivity.this).inflate(R.layout.center_layout,null);
        liWindow.showCenterPopupWindow(mView);
    * */
}
