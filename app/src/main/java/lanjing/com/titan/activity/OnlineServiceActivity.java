package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;

/**
 * 在线客服
 */
public class OnlineServiceActivity extends XActivity {

    @BindView(R.id.ed_input_question)
    EditText edInputQuestion;
    @BindView(R.id.send)
    TextView send;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_online_service;
    }



    @OnClick({R.id.ed_input_question, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_input_question:
                edInputQuestion.setCursorVisible(true);//光标显示
                break;
            case R.id.send:
                break;
        }
    }
}
