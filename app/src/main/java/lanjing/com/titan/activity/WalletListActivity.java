package lanjing.com.titan.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaydenxiao.guider.HighLightGuideView;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.WalletListAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WalletListResponse;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 钱包列表
 */
public class WalletListActivity extends MvpActivity<WalletContact.WalletPresent> implements WalletContact.IWalletView {

    @BindView(R.id.import_new_wallet_btn)
    TextView importNewWalletBtn;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    WalletListAdapter mAdapter;
    List<WalletListResponse.DataBean> mList;

    String token;
    @BindView(R.id.rv)
    SwipeMenuRecyclerView rv;

    @Override
    public void initData(Bundle savedInstanceState) {
        setSideslipMenu();
        mList = new ArrayList<>();
        mAdapter = new WalletListAdapter(R.layout.recy_item_wallet_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.walletList(context);
        mAdapter.setOnItemChildClickListener(new WalletListAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_set_wallet_name) {
                    Intent intentWalletName = new Intent(context, SetWalletActivity.class);
                    intentWalletName.putExtra("userName", mList.get(position).getUsername());
                    intentWalletName.putExtra("addressLabel", mList.get(position).getWId());
                    intentWalletName.putExtra("address", mList.get(position).getAddress());
                    intentWalletName.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intentWalletName);

                } else if (view.getId() == R.id.wallet_change) {//切换钱包
                    if (mList.get(position).getToken().equals("")) {
                        ToastUtils.showShortToast(context, "登陆信息已过期请重新登陆");
                    } else if (mList.get(position).getPhone().equals("")) {
                        ToastUtils.showShortToast(context, "该账号未绑定手机号");
                    } else if (mList.get(position).getInviter().equals("")) {
                        ToastUtils.showShortToast(context, "该账号未绑定推荐人id");
                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("flag", 0);
                        SPUtils.putString(Constant.TOKEN, mList.get(position).getToken(), context);
                        ToastUtils.showShortToast(context, getResources().getString(R.string.switch_success));
                        startActivity(intent);
                    }

                }
            }
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            mPresent.walletList(context);
        });
        refresh.setEnableLoadMore(false);//不进行上拉加载


        new Thread() {
            public void run() {
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }

        }.start();


    }


    Handler hand = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (isFristRun()) {
                // 如果是第一次启动程序则进入引导界面

                //全屏提示，没高亮控件情况引导
                HighLightGuideView.builder(context).addNoHighLightGuidView(R.drawable.wallet_list_yindao).setMaskColor(getResources().getColor(R.color.mask_color)).show();

                //有高亮控件情况引导（单个高亮控件）
//                HighLightGuideView.builder(context) .addHighLightGuidView(tvLanguage, R.drawable.dmtext) .setHighLightStyle(HighLightGuideView.VIEWSTYLE_OVAL) .show();

                //有高亮控件情况引导（多个高亮控件）
//                HighLightGuideView.builder(context) .addHighLightGuidView(view1, R.drawable.dstext) .addHighLightGuidView(view2, R.drawable.dmtext) .setHighLightStyle(HighLightGuideView.VIEWSTYLE_OVAL) .show();
            }

        }

        ;
    };

    // 判断是否是第一次启动程序 利用 SharedPreferences 将数据保存在本地
    private boolean isFristRun() {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                "share", MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!isFirstRun) {
            return false;
        } else {
            //保存数据 （第三步）
            editor.putBoolean("isFirstRun", false);
            //提交当前数据 （第四步）
            editor.commit();
            return true;
        }
    }

    // 2. 设置item侧滑菜单
    private void setSideslipMenu() {
        // 设置菜单创建器
        rv.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听
        rv.setSwipeMenuItemClickListener(mMenuItemClickListener);
    }

    // 3. 创建侧滑菜单
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
//            token = mList.get(viewType).getToken();
            SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                    .setBackgroundColor(getResources().getColor(R.color.red)) // 背景颜色
                    .setText(R.string.delete) // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(144) // 宽
                    .setHeight(MATCH_PARENT); //高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用））
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    // 4. 创建侧滑菜单的点击事件
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            token = mList.get(menuBridge.getAdapterPosition()).getToken();
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            showDeleteWalletDialog(token);
            menuBridge.closeMenu();
            //在menuBridge中我们可以得到侧滑的这一项item的position (menuBridge.getAdapterPosition())
        }
    };


    @Override
    public boolean useEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveActivity(EventImpl.UpdateWalletNameEvent event) {
        if (!ObjectUtils.isEmpty(event)) {
            mPresent.walletList(context);
        }
    }

    AlertDialog deleteWalletDialog = null;

    //删除钱包1100011
    private void showDeleteWalletDialog(String token) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_delete_wallet)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 250), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {//取消删除钱包
                    deleteWalletDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {//确定删除钱包
                    //删除钱包方法
                    mPresent.deleteWallet(context, token);
                    deleteWalletDialog.dismiss();
                });
        deleteWalletDialog = builder.create();
        deleteWalletDialog.show();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet_list;
    }

    @Override
    protected WalletContact.WalletPresent createPresent() {
        return new WalletContact.WalletPresent();
    }

    List<WalletListResponse.DataBean> data;

    @Override
    public void getWalletListResult(Response<WalletListResponse> response) {
        refresh.finishRefresh();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);
            }

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //删除钱包
    @Override
    public void getDeleteWalletResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.delete_success));
            mPresent.walletList(context);
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }

    @OnClick(R.id.import_new_wallet_btn)
    public void onViewClicked() {
        Intent intent = new Intent(context, ImportNewWalletActivity.class);
        startActivity(intent);
    }

//    else if(response.body().getCode() == LOG_BACK_IN){
//        Intent intent = new Intent(context,LoginActivity.class);
//        startActivity(intent);
//    }
}
