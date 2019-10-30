package lanjing.com.titan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.FeedbackDetaiContact;
import lanjing.com.titan.response.viewFeedbackResponse;
import retrofit2.Response;

/**
 * 建议反馈 详情
 * <p>
 * MvpActivity<FeedbackListContact.FeedbackListPresent> implements FeedbackListContact.IFeedbackListView
 */
public class FeedbackDetailActivity extends MvpActivity<FeedbackDetaiContact.FeedbackDetaiPresent> implements FeedbackDetaiContact.IFeedbackListView {

    @BindView(R.id.tv_titles)
    TextView tvTitles;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;

    @BindView(R.id.ll_image_container)
    LinearLayout LlImageContainer;
    @BindView(R.id.ll_text_container)
    LinearLayout LlTextContainer;
    @BindView(R.id.iv_image1)
    ImageView IvImage1;
    @BindView(R.id.iv_image2)
    ImageView IvImage2;
    @BindView(R.id.iv_image3)
    ImageView IvImage3;
    @BindView(R.id.iv_maximage)
    ImageView IvMaximage;

    String image1, image2, image3;

    Pattern pen;
    String slist;
    String[] temp;

    @Override
    public void initData(Bundle savedInstanceState) {
        String fid = getIntent().getStringExtra("fid");
        mPresent.feedbackList(context, fid);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback_detail;
    }


    @OnClick({R.id.iv_image1, R.id.iv_image2, R.id.iv_image3, R.id.iv_maximage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image1:
                IvMaximage.setVisibility(View.VISIBLE);
                LlImageContainer.setVisibility(View.GONE);
                LlTextContainer.setVisibility(View.GONE);
                Picasso.with(context)
                        .load(temp[0])
                        .into(IvMaximage);
                break;
            case R.id.iv_image2:
                IvMaximage.setVisibility(View.VISIBLE);
                LlImageContainer.setVisibility(View.GONE);
                LlTextContainer.setVisibility(View.GONE);
                Picasso.with(context)
                        .load(temp[1])
                        .into(IvMaximage);
                break;
            case R.id.iv_image3:
                IvMaximage.setVisibility(View.VISIBLE);
                LlImageContainer.setVisibility(View.GONE);
                LlTextContainer.setVisibility(View.GONE);
                Picasso.with(context)
                        .load(temp[2])
                        .into(IvMaximage);
                break;
            case R.id.iv_maximage:
                IvMaximage.setVisibility(View.GONE);
                LlImageContainer.setVisibility(View.VISIBLE);
                LlTextContainer.setVisibility(View.VISIBLE);

                break;
        }

    }

    @Override
    protected FeedbackDetaiContact.FeedbackDetaiPresent createPresent() {
        return new FeedbackDetaiContact.FeedbackDetaiPresent();
    }

    @Override
    public void getFeedbackListResult(Response<viewFeedbackResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {

            //判断是否有图片
            if (response.body().getData().getImgUrls().equals("")) {
                //没有图片要隐藏图片容器
                LlImageContainer.setVisibility(View.GONE);
            } else {
                //实现字符串分割
                pen = Pattern.compile(",");
                slist = response.body().getData().getImgUrls();
                temp = pen.split(slist);
                if (temp.length == 1) {
                    image1 = temp[0];
                    Picasso.with(context)
                            .load(temp[0])
                            .into(IvImage1);
                } else if (temp.length == 2) {
                    image1 = temp[0];
                    image2 = temp[1];
                    Picasso.with(context)
                            .load(temp[0])
                            .into(IvImage1);
                    Picasso.with(context)
                            .load(temp[1])
                            .into(IvImage2);
                } else if (temp.length == 3) {
                    image1 = temp[0];
                    image2 = temp[1];
                    image3 = temp[2];
                    Picasso.with(context)
                            .load(temp[0])
                            .into(IvImage1);
                    Picasso.with(context)
                            .load(temp[1])
                            .into(IvImage2);
                    Picasso.with(context)
                            .load(temp[2])
                            .into(IvImage3);
                }
            }

        }
        tvTitles.setText(response.body().getData().getTitle());//标题
        tvContent.setText(response.body().getData().getContent());//内容
        tvAnswer.setText(response.body().getData().getReply());
    }

    @Override
    public void getDataFailed() {

    }
}
