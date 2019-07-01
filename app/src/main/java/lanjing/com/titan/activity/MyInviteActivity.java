package lanjing.com.titan.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.FriendListAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.FriendListContact;
import lanjing.com.titan.response.FriendListResponse;
import retrofit2.Response;

/**
 * 我的邀请
 */
public class MyInviteActivity extends MvpActivity<FriendListContact.FriendListPresent> implements FriendListContact.IFriendListView {

    @BindView(R.id.my_invite_bg)
    ImageView myInviteBg;
    @BindView(R.id.tv_friend_num)
    TextView tvFriendNum;
    @BindView(R.id.tv_reward_num)
    TextView tvRewardNum;
    @BindView(R.id.tv_team_sun)
    TextView TvTeamSun;
    @BindView(R.id.tv_team_sum)
    TextView Tv_TeamSum;
    @BindView(R.id.tv_register_url)
    TextView tvRegisterUrl;
    @BindView(R.id.register_btn)
    TextView registerBtn;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    FriendListAdapter mAdapter;
    List<FriendListResponse.DataBean> mList;

    int page = 1;
    int pageSize = 10;
    @BindView(R.id.tv_invite_url)
    TextView tvInviteUrl;
    @BindView(R.id.invite_btn)
    TextView inviteBtn;
    String inviteUrl;
    String registerUrl;

    @Override
    public void initData(Bundle savedInstanceState) {
        Locale locale = getResources().getConfiguration().locale;//判断当前的语言
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            myInviteBg.setBackground(getResources().getDrawable(R.mipmap.icon_my_invite_bg));
        } else if (locale.equals(Locale.ENGLISH)) {
            myInviteBg.setBackground(getResources().getDrawable(R.mipmap.icon_my_invite_en_bg));
        }

    }

    private void initList() {
        mList = new ArrayList<>();
        mAdapter = new FriendListAdapter(R.layout.recy_item_friend_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);

        mPresent.friendList(context, String.valueOf(page), String.valueOf(pageSize));

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.friendList(context, String.valueOf(page), String.valueOf(pageSize));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.friendList(context, String.valueOf(page), String.valueOf(pageSize));
        });
    }

    @Override
    protected void onResume() {
        initList();
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_invite;
    }

    @OnClick({R.id.invite_btn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.invite_btn://邀请链接
                openInviteUrl();
                break;
            case R.id.register_btn://注册链接
                openRegisterUrl();
                break;
        }
    }

    //打开邀请链接
    private void openInviteUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                (Uri.parse(inviteUrl))
        ).addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //打开注册链接
    private void openRegisterUrl() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                (Uri.parse(registerUrl))
        ).addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
    protected FriendListContact.FriendListPresent createPresent() {
        return new FriendListContact.FriendListPresent();
    }

    List<FriendListResponse.DataBean> data;

    @Override
    public void getFriendListResult(Response<FriendListResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Log.e("TAG", String.valueOf(response));
            tvFriendNum.setText(String.valueOf(response.body().getNum()));
            tvRewardNum.setText(String.valueOf(response.body().getReward()));
            TvTeamSun.setText(String.valueOf(response.body().getCurrent_predice_mining_number_of_people()));
            Tv_TeamSum.setText(String.valueOf(response.body().getCurrent_predice_mining_number_of_usd()));
            tvInviteUrl.setText(response.body().getRecommendurl());
            tvRegisterUrl.setText(response.body().getRegisterurl());
            inviteUrl = response.body().getRecommendurl();
            registerUrl = response.body().getRegisterurl();
            if (page == 1) {
                mList.clear();
                mAdapter.notifyDataSetChanged();
            }
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);
                if (data != null && data.size() == pageSize) {
                    refresh.setEnableLoadMore(true);
                } else {
                    refresh.setEnableLoadMore(false);
                }
                rv.setVisibility(View.VISIBLE);
            } else {
                rvNormalShow.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
            }
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
