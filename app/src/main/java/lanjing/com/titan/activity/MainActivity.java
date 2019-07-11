package lanjing.com.titan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.lxh.baselibray.BaseApplication;
import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.util.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.MainFragmentPagerAdapter;
import lanjing.com.titan.fragment.DealFragment;
import lanjing.com.titan.fragment.InformationFragment;
import lanjing.com.titan.fragment.MarketFragment;
import lanjing.com.titan.fragment.MyFragment;
import lanjing.com.titan.fragment.WalletFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * 主页面  嵌入五个碎片  钱包  行情  交易  咨询  我的
 */
public class MainActivity extends XActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.rg)
    RadioGroup rg;
    List<Fragment> fragmentList = new ArrayList<>();
    MainFragmentPagerAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        fragmentList.add(new WalletFragment());
        fragmentList.add(new MarketFragment());
        fragmentList.add(new DealFragment());
        fragmentList.add(new InformationFragment());
        fragmentList.add(new MyFragment());

        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        rg.check(R.id.rb_wallet);
                        break;
                    case 1:
                        rg.check(R.id.rb_market);
                        break;
                    case 2:
                        rg.check(R.id.rb_deal);
                        break;
                    case 3:
                        rg.check(R.id.rb_information);
                        break;
                    case 4:
                        rg.check(R.id.rb_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        rg.setOnCheckedChangeListener(this);
        rg.getChildAt(0).performClick();
        int id = getIntent().getIntExtra("flag", 0);
        if (id == 0) {
            viewPager.setCurrentItem(0);
        } else if (id == 2) {
            viewPager.setCurrentItem(2);
        } else if (id == 4) {
            viewPager.setCurrentItem(2);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_wallet:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_market:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_deal:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_information:
                viewPager.setCurrentItem(3);
                break;
            case R.id.rb_my:
                viewPager.setCurrentItem(4);
                break;

        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private long timeMillis;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                ToastUtils.showShortToast(context, getResources().getString(R.string.press_exit_again));
                timeMillis = System.currentTimeMillis();
            } else {
                BaseApplication.getActivityManage().finishAll();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
