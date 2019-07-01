package lanjing.com.titan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;

/**
 * 建议反馈 详情
 */
public class FeedbackDetailActivity extends XActivity {

    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;


    @Override
    public void initData(Bundle savedInstanceState) {
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String reply = getIntent().getStringExtra("reply");
        tvTitles.setText(title);
        tvContent.setText(content);
        tvAnswer.setText(reply);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback_detail;
    }


}
