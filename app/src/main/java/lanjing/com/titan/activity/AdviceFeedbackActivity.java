package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.FeedbackContact;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 建议反馈
 */
public class AdviceFeedbackActivity extends MvpActivity<FeedbackContact.FeedbackPresent> implements FeedbackContact.IFeedbackView {


    @BindView(R.id.edit_problem_title)
    EditText editProblemTitle;
    @BindView(R.id.edit_problem)
    EditText editProblem;
    @BindView(R.id.submit_advice_btn)
    TextView submitAdviceBtn;
    @BindView(R.id.img_feedback_list)
    ImageView imgFeedbackList;

    @Override
    public void initData(Bundle savedInstanceState) {
        setEditTextInhibitInputSpeChat(editProblemTitle);
        setEditTextInhibitInputSpeChat(editProblem);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advice_feedback;
    }


    @OnClick({R.id.edit_problem_title, R.id.submit_advice_btn,R.id.img_feedback_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_feedback_list:
                Intent intent = new Intent(context,FeedbackListActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_problem_title:
                editProblemTitle.setCursorVisible(true);//光标显示
                break;
            case R.id.submit_advice_btn:
                String title = editProblemTitle.getText().toString().trim();
                String content = editProblem.getText().toString().trim();
                if (ObjectUtils.isEmpty(title)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_title));
                    return;
                }
                if (ObjectUtils.isEmpty(content)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_content));
                    return;
                }
                showLoadingDialog();
                mPresent.feedback(context, content, title);
                break;
        }
    }
    //限制输入文本内容
    public static void setEditTextInhibitInputSpeChat(EditText editText){

        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat="[`~@ #_$%^&*()+=|{}':;'\\[\\].<>\\-/~@#￥%……&*（）\"——+|{}【】‘；：”“’、]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if(matcher.find())return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
    @Override
    protected FeedbackContact.FeedbackPresent createPresent() {
        return new FeedbackContact.FeedbackPresent();
    }

    @Override
    public void getFeedbackResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.submit_successfully));
            finish();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
