package lanjing.com.titan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;

/**
 * 公告详情
 */
public class NoticeDetailActivity extends XActivity {


    @BindView(R.id.content_title)
    TextView contentTitle;
    @BindView(R.id.notice_content)
    TextView noticeContent;
    @BindView(R.id.tv_notice_time)
    TextView tvNoticeTime;
    @BindView(R.id.tv_team)
    TextView tvTeam;

    @Override
    public void initData(Bundle savedInstanceState) {
        contentTitle.setText(getIntent().getStringExtra("title"));
        noticeContent.setText(getIntent().getStringExtra("content"));
        tvNoticeTime.setText(getIntent().getStringExtra("time"));
        tvTeam.setText(getIntent().getStringExtra("team"));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_detail;
    }

}
