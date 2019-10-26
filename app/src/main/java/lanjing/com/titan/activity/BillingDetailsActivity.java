package lanjing.com.titan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.fragment.CashValueFragment;
import lanjing.com.titan.fragment.CoinCurrencyTradingFragment;
import lanjing.com.titan.fragment.RewardDropFragment;

/**
 * 账单明细   嵌入三个碎片    充值提现    币币交易    空投奖励
 */
public class BillingDetailsActivity extends XActivity {


    @BindView(R.id.tv_cash_value)
    TextView tvCashValue;
    @BindView(R.id.tv_coin_currency_trading)
    TextView tvCoinCurrencyTrading;
    @BindView(R.id.tv_reward_drop)
    TextView tvRewardDrop;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.rg)
    RadioGroup rg;
    private Fragment showFragment;

    @Override
    public void initData(Bundle savedInstanceState) {
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.tv_cash_value://充值提现
                    fragmentManager(R.id.fragment_container, new CashValueFragment(), "cashValue");
                    break;
                case R.id.tv_coin_currency_trading://币币交易
                    fragmentManager(R.id.fragment_container, new CoinCurrencyTradingFragment(), "coinCurrencyTrading");
                    break;
                case R.id.tv_reward_drop://空投奖励
                    fragmentManager(R.id.fragment_container, new RewardDropFragment(), "rewardDrop");
                    break;
            }
        });
        rg.getChildAt(0).performClick();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_billing_details;
    }

    private void fragmentManager(int resid, Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏正在显示的Fragment对象
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }

        Fragment mFragment = fragmentManager.findFragmentByTag(tag);
        if (mFragment != null) {
            fragmentTransaction.show(mFragment);
        } else {
            mFragment = fragment;
            fragmentTransaction.add(resid, mFragment, tag);
        }

        showFragment = mFragment;
        fragmentTransaction.commit();
    }

}
