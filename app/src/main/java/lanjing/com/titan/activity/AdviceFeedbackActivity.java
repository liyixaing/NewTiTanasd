package lanjing.com.titan.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.BitmapUtils;
import com.lxh.baselibray.util.CameraUtils;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.FeedbackContact;
import lanjing.com.titan.response.Responseuplode;
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
    @BindView(R.id.iv_image_one)
    ImageView IvImageOne;//第一张图片
    @BindView(R.id.iv_image_tow)
    ImageView IvImageTow;//第二张图片
    @BindView(R.id.iv_image_three)
    ImageView IvImageThree;//第三张图片

    @BindView(R.id.iv_feedback_one)
    ImageView IvFeedbackOne;//选择图片后显示的第一张图片
    @BindView(R.id.iv_feedback_tow)
    ImageView IvFeedbackTow;//选择显示的第二张图片
    @BindView(R.id.iv_feedback_three)
    ImageView IvFeedbackThree;//选择后显示的第三张图片

    @BindView(R.id.rl_delt_one)
    RelativeLayout RlDeltOne;//删除第一张图片
    @BindView(R.id.rl_delt_tow)
    RelativeLayout RlDeltTow;//删除第二张图片
    @BindView(R.id.rl_delt_three)
    RelativeLayout RlDeltThree;//删除第三张图片

    @BindView(R.id.iv_delt_one)
    ImageView IvDeltOne;//第一张图片删除按钮
    @BindView(R.id.iv_delt_tow)
    ImageView IvDeltTow;//第二张图片删除按钮
    @BindView(R.id.iv_delt_three)
    ImageView IvDeltThiree;//第三张图片删除按钮
    public static final int SELECT_PHOTO = 2;//启动相册标识
    private Bitmap orc_bitmap;//拍照和相册获取图片的Bitmap
    private String base64Pic;

    int type = 0;//判断状态使用（不能作为参数传递）
    int Imagetype = 0;//判断删除的是哪一个图片
    String types = "2";
    String image1, image2, image3;

    RxPermissions rxPermissions;

    @Override
    public void initData(Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(context);
        setEditTextInhibitInputSpeChat(editProblemTitle);
        setEditTextInhibitInputSpeChat(editProblem);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advice_feedback;
    }

    String douhao1 = "";
    String douhao2 = "";

    @OnClick({R.id.edit_problem_title, R.id.submit_advice_btn, R.id.img_feedback_list, R.id.iv_image_one,
            R.id.iv_image_tow, R.id.iv_image_three, R.id.iv_delt_one, R.id.iv_delt_tow, R.id.iv_delt_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_feedback_list:
                Intent intent = new Intent(context, FeedbackListActivity.class);
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
                mPresent.addFeedback(context, content, title, image1 + douhao1 + image2 + douhao2 + image3);
                break;
            case R.id.iv_image_one://选择第一张图片
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                type = 1;
                                Intent PhotoIntentone = CameraUtils.getSelectPhotoIntent();
                                startActivityForResult(PhotoIntentone, SELECT_PHOTO);
                            } else {
                                ToastUtils.showLongToast(this, getResources().getString(R.string.permission_not_opened));
                            }
                        });

                break;
            case R.id.iv_image_tow://选择的第二张图片

                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                type = 2;
                                Intent PhotoIntentTow = CameraUtils.getSelectPhotoIntent();
                                startActivityForResult(PhotoIntentTow, SELECT_PHOTO);
                            } else {
                                ToastUtils.showLongToast(this, getResources().getString(R.string.permission_not_opened));
                            }
                        });

                break;
            case R.id.iv_image_three://选择第三张图片

                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(granted -> {
                            if (granted) {
                                type = 3;
                                Intent PhotoIntentThree = CameraUtils.getSelectPhotoIntent();
                                startActivityForResult(PhotoIntentThree, SELECT_PHOTO);
                            } else {
                                ToastUtils.showLongToast(this, getResources().getString(R.string.permission_not_opened));
                            }
                        });


                break;
            case R.id.iv_delt_one:
                Imagetype = 1;
                image1 = "";
//                mPresent.cancelImage(context, image1);
                IvFeedbackOne.setVisibility(View.GONE);
                RlDeltOne.setVisibility(View.GONE);
                IvImageOne.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_delt_tow:
                Imagetype = 2;
                douhao1 = "";
                image2 = "";
//                mPresent.cancelImage(context, image2);
                IvFeedbackTow.setVisibility(View.GONE);
                RlDeltTow.setVisibility(View.GONE);
                IvImageTow.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_delt_three:
                Imagetype = 3;
                douhao2 = "";
                image3 = "";
//                mPresent.cancelImage(context, image3);
                IvFeedbackThree.setVisibility(View.GONE);
                RlDeltThree.setVisibility(View.GONE);
                IvImageThree.setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * 拍完照和从相册获取玩图片都要执行的方法(根据图片路径显示图片)
     */
    private void displayImage(String imagePath, ImageView iv) {
        if (!TextUtils.isEmpty(imagePath)) {
            orc_bitmap = CameraUtils.comp(BitmapFactory.decodeFile(imagePath)); //压缩图片
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);
            if (type == 1) {
                image1 = base64Pic;
            } else if (type == 2) {
                douhao1 = ",";
                image2 = base64Pic;
            } else if (type == 3) {
                douhao2 = ",";
                image3 = base64Pic;
            }
            CameraUtils.ImgUpdateDirection(imagePath, orc_bitmap, iv);//显示图片,并且判断图片显示的方向,如果不正就放正
        } else {
            ToastUtils.showLongToast(this, getResources().getString(R.string.image_acquisition_failed));
        }
    }

    //选择图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > 19) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImgeOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    if (type == 1) {
                        IvFeedbackOne.setVisibility(View.VISIBLE);//显示选择图片
                        RlDeltOne.setVisibility(View.VISIBLE);//显示删除图片
                        IvImageOne.setVisibility(View.GONE);//隐藏加号图标
                        displayImage(imagePath, IvFeedbackOne);
                    } else if (type == 2) {

                        IvFeedbackTow.setVisibility(View.VISIBLE);//显示选择图片
                        RlDeltTow.setVisibility(View.VISIBLE);//显示删除图片
                        IvImageTow.setVisibility(View.GONE);//隐藏加号图标
                        displayImage(imagePath, IvFeedbackTow);
                    } else if (type == 3) {
                        IvFeedbackThree.setVisibility(View.VISIBLE);//显示选择图片
                        RlDeltThree.setVisibility(View.VISIBLE);//显示删除图片
                        IvImageThree.setVisibility(View.GONE);//隐藏加号图标
                        displayImage(imagePath, IvFeedbackThree);
                    }
                }
                break;
            default:
                break;
        }
    }

    //或许每个人心底都有一个难以磨灭的名字
    //限制输入文本内容
    public static void setEditTextInhibitInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~@ #_$%^&*()+=|{}':;'\\[\\].<>\\-/~@#￥%……&*（）\"——+|{}【】‘；：”“’、]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
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
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }


    //上传图片返回执行事件
    @Override
    public void getmodifyHeadResult(Response<Responseuplode> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    //删除图片返回执行
    @Override
    public void getcancelImage(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}
