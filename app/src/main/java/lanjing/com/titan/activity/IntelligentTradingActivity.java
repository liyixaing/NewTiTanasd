package lanjing.com.titan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lxh.baselibray.base.XActivity;

import lanjing.com.titan.R;

/**
 * 开启智能交易时进入的页面  后面改用弹窗显示  此类和布局弃用
 */
public class IntelligentTradingActivity extends XActivity {


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_intelligent_trading;
    }
}
