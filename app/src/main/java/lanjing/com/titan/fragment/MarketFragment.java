package lanjing.com.titan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.MarketListAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.MarketContact;
import lanjing.com.titan.response.MarketListResponse;
import retrofit2.Response;

/**
 * 行情数据列表
 */
public class MarketFragment extends MvpFragment<MarketContact.MarketPresent> implements MarketContact.IMarketView {


    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    MarketListAdapter mAdapter;
    List<MarketListResponse.DataBean> mList;
    private Timer timer;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    private void initList(){
        mList = new ArrayList<>();
        mAdapter = new MarketListAdapter(R.layout.recy_item_market_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.marketList(context);

        refresh.setOnRefreshListener(refreshLayout -> {
            mPresent.marketList(context);
        });
        refresh.setEnableLoadMore(false);//不进行上拉加载

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // (1) 使用handler发送消息
                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);
            }
        },0,180000);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
    }


    // (2) 使用handler处理接收到的消息
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                mPresent.marketList(context);
            }
        }
    };

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    //数据变化时显示动画
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }



    @Override
    public void onResume() {
        initList();
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected MarketContact.MarketPresent createPresent() {
        return new MarketContact.MarketPresent();
    }
    List<MarketListResponse.DataBean> data;
    @Override
    public void getMarketResult(Response<MarketListResponse> response) {
        refresh.finishRefresh();
        if(response.body().getCode() == Constant.SUCCESS_CODE){
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);
                rv.setVisibility(View.VISIBLE);
            }else {
                rvNormalShow.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
            }
        }else {
            ToastUtils.showShortToast(context,response.body().getMag());
        }

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context,getResources().getString(R.string.network_error));
    }
}
