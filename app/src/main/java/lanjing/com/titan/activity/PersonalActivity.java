package lanjing.com.titan.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.event.BusFactory;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.BitmapUtils;
import com.lxh.baselibray.util.CameraUtils;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.PersonDataChangeContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.ModifyHeadResponse;
import lanjing.com.titan.response.Responseuplode;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 个人资料，修改头像和昵称
 */
public class PersonalActivity extends MvpActivity<PersonDataChangeContact.PersonDataChangePresent> implements PersonDataChangeContact.IPersonDataChangeView {
    @BindView(R.id.head_pic)
    ImageView headPic;
    @BindView(R.id.change_head_lay)
    RelativeLayout changeHeadLay;
    @BindView(R.id.personal_nick)
    TextView personalNick;
    @BindView(R.id.change_nick_lay)
    RelativeLayout changeNickLay;

    String nickname;
    RxPermissions rxPermissions;

    public static final int TAKE_PHOTO = 1;//启动相机标识
    public static final int SELECT_PHOTO = 2;//启动相册标识
    private File outputImagepath;//存储拍完照后的图片

    private Bitmap orc_bitmap;//拍照和相册获取图片的Bitmap
    AlertDialog dialog = null;

    private String base64Pic;

    @Override
    public void initData(Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(context);
        //获取昵称
        personalNick.setText(SPUtils.getString(Constant.NICK_NAME, null, context));
        //获取头像
        String portrait = SPUtils.getString(Constant.PORTRAIT, null, context);
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(context).load(portrait).apply(mRequestOptions).into(headPic);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal;
    }

    @OnClick({R.id.change_head_lay, R.id.change_nick_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_head_lay:
                showTakePhotoDialog();
                break;
            case R.id.change_nick_lay:
                showUpdateNicknameDialog();
                break;
        }
    }

    //限制输入文本内容
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    //修改头像弹窗
    private void showTakePhotoDialog() {
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .addDefaultAnimation()
                                .setContentView(R.layout.dialog_take_photo)
                                .setWidthAndHeight(SizeUtils.dp2px(this, 228), SizeUtils.dp2px(this, 80))
                                .setCancelable(true)
                                .setOnClickListener(R.id.tv_take_photo, v -> {
                                    SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                                            "yyyy_MM_dd_HH_mm_ss");
                                    String filename = timeStampFormat.format(new Date());
                                    outputImagepath = new File(Environment.getExternalStorageDirectory(),
                                            filename + ".jpg");
                                    Intent takePhotoIntent = CameraUtils.getTakePhotoIntent(this, outputImagepath);
                                    startActivityForResult(takePhotoIntent, TAKE_PHOTO); // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                                    dialog.dismiss();
                                }).setOnClickListener(R.id.tv_select_pic, v -> {
                                    dialog.dismiss();
                                    Intent selectPhotoIntent = CameraUtils.getSelectPhotoIntent();
                                    startActivityForResult(selectPhotoIntent, SELECT_PHOTO);
                                });
                        dialog = builder.create();
                        dialog.show();
                    } else {
                        ToastUtils.showLongToast(this, getResources().getString(R.string.permission_not_opened));
                    }
                });

    }


    //修改昵称弹窗
    AlertDialog updateNicknameDialog = null;

    private void showUpdateNicknameDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_update_nickname)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 300), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText newNickname = updateNicknameDialog.getView(R.id.ed_new_nickname);
                    nickname = newNickname.getText().toString();
                    if (ObjectUtils.isEmpty(nickname)) {
                        ToastUtils.showLongToast(context, getResources().getString(R.string.nickname_cannot_be_empty));
                        return;
                    }
                    mPresent.modifyNickname(context, nickname);
                    updateNicknameDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> updateNicknameDialog.dismiss());
        updateNicknameDialog = builder.create();
        updateNicknameDialog.show();
        setEditTextInhibitInputSpeChat(updateNicknameDialog.getView(R.id.ed_new_nickname));

    }


    /**
     * 拍完照和从相册获取玩图片都要执行的方法(根据图片路径显示图片)
     */
    private void displayImage(String imagePath, ImageView iv) {
        if (!TextUtils.isEmpty(imagePath)) {
            //orc_bitmap = BitmapFactory.decodeFile(imagePath);//获取图片
            orc_bitmap = CameraUtils.comp(BitmapFactory.decodeFile(imagePath)); //压缩图片

            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

            RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                    .skipMemoryCache(true);//不做内存缓存
            Glide.with(context).load(base64Pic).apply(mRequestOptions).into(headPic);

            CameraUtils.ImgUpdateDirection(imagePath, orc_bitmap, iv);//显示图片,并且判断图片显示的方向,如果不正就放正

            mPresent.modifyHead(context, base64Pic, "1");

        } else {
            ToastUtils.showLongToast(this, getResources().getString(R.string.image_acquisition_failed));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //打开相机后返回
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    displayImage(outputImagepath.getAbsolutePath(), headPic);

                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存
                    Glide.with(context).load(outputImagepath.getAbsolutePath()).apply(mRequestOptions).into(headPic);

                }
                break;
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
                    displayImage(imagePath, headPic);
                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存
                    Glide.with(context).load(imagePath).apply(mRequestOptions).into(headPic);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected PersonDataChangeContact.PersonDataChangePresent createPresent() {
        return new PersonDataChangeContact.PersonDataChangePresent();
    }

    //修改头像返回
    @Override
    public void getmodifyHeadResult(Response<Responseuplode> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.modify_avatar_successfully));
            SPUtils.putString(Constant.PORTRAIT, response.body().getUrl(), context);
            BusFactory.getBus().post(new EventImpl.UpdatePortraitEvent());
            mPresent.UserAvatarupdate(context, response.body().getUrl());
            finish();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getUserAvatar(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, response.body().getMsg());
        } else if (response.body().getCode() == -10) {
//            ToastUtils.showShortToast(context, );
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //修改昵称返回
    @Override
    public void getmodifyNicknameResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.modify_nickname_successfully));
            BusFactory.getBus().post(new EventImpl.UpdateNicknameEvent());
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
