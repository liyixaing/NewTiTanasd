package lanjing.com.titan.zxing.android;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.util.BitmapUtils;
import com.lxh.baselibray.util.CameraUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import lanjing.com.titan.R;
import lanjing.com.titan.activity.PaymentActivity;
import lanjing.com.titan.activity.PaymentCodeActivity;
import lanjing.com.titan.util.QRHelper;
import lanjing.com.titan.zxing.camera.CameraManager;
import lanjing.com.titan.zxing.view.ViewfinderView;

/**
 * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
 * 然后在扫描成功的时候覆盖扫描结果
 */
public final class CaptureActivity extends XActivity implements SurfaceHolder.Callback {
    private static final String TAG = CaptureActivity.class.getSimpleName();
    // 相机控制
    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private IntentSource source;
    private Collection<BarcodeFormat> decodeFormats;
    private Map<DecodeHintType, ?> decodeHints;
    private String characterSet;
    // 电量控制
    private InactivityTimer inactivityTimer;
    // 声音、震动控制
    private BeepManager beepManager;
    private ImageButton imageButton_back;

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    String walletAddress, labelAddress;
    LinearLayout ll_mycode;
    ImageView img_feedback_list;
    public static final int TAKE_PHOTO = 1;//启动相机标识
    public static final int SELECT_PHOTO = 2;//启动相册标识
    private Bitmap orc_bitmap;//拍照和相册获取图片的Bitmap
    private String base64Pic;

    /**
     * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
     */


    @Override
    public void initData(Bundle savedInstanceState) {
// 保持Activity处于唤醒状态
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        walletAddress = getIntent().getStringExtra("walletAddress");
        labelAddress = getIntent().getStringExtra("labelAddress");
        ll_mycode = findViewById(R.id.ll_mycode);
        ll_mycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到我的二维码界面】
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                    if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission
                            .WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(context, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                    }
                    //申请权限
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(context, PaymentCodeActivity.class);
                    intent.putExtra("walletAddress", walletAddress);
                    intent.putExtra("labelAddress", labelAddress);
                    startActivity(intent);
                }

            }
        });

        //点击跳转到相册
        img_feedback_list = findViewById(R.id.img_feedback_list);
        img_feedback_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onJumpPictures();//跳转到相册
            }
        });

        hasSurface = false;

        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.capture;
    }
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.capture);
//
//
//    }


    //跳转到相册
    private void onJumpPictures() {
        Intent selectPhotoIntent = CameraUtils.getSelectPhotoIntent();
        startActivityForResult(selectPhotoIntent, SELECT_PHOTO);

    }

    //相册返回回调
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
                    displayImage(imagePath);
                    RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                            .skipMemoryCache(true);//不做内存缓存


                }
                break;
            default:
                break;
        }
    }

    String result;

    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            //orc_bitmap = BitmapFactory.decodeFile(imagePath);//获取图片
            orc_bitmap = CameraUtils.comp(BitmapFactory.decodeFile(imagePath)); //压缩图片
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);
            RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                    .skipMemoryCache(true);//不做内存缓存

            //做图片识别处理
            result = QRHelper.getReult(orc_bitmap);
            //这里的异常抛出是因为识别到没有二维码的图片是会返回null
            try {
                //识别图片二维码成功做判断处理
                if (result.contains("TITAN")) {//判断字符串内容方法 兼容5.0以上
                    //存在titan字符则跳转到支付界面
                    Intent payment = new Intent(this, PaymentActivity.class);
                    payment.putExtra("rawResult", result);
                    startActivity(payment);
                    finish();//扫描成功后直接关闭这个界面
                } else {
                    //不存在则未定
                    ToastUtils.showLongToast(context, getResources().getString(R.string.recognition_failed));
                    finish();//关闭当前窗口
                }
            } catch (RuntimeException e) {
                ToastUtils.showLongToast(context, getResources().getString(R.string.recognition_failed));

            } catch (Exception ex) {
                ToastUtils.showLongToast(context, getResources().getString(R.string.recognition_failed));
            }
        } else {
            //图片获取失败
            ToastUtils.showLongToast(this, getResources().getString(R.string.image_acquisition_failed));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // CameraManager必须在这里初始化，而不是在onCreate()中。
        // 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
        // 当扫描框的尺寸不正确时会出现bug
        cameraManager = new CameraManager(getApplication());

        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(cameraManager);

        handler = null;

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            // activity在paused时但不会stopped,因此surface仍旧存在；
            // surfaceCreated()不会调用，因此在这里初始化camera
            initCamera(surfaceHolder);
        } else {
            // 重置callback，等待surfaceCreated()来初始化camera
            surfaceHolder.addCallback(this);
        }

        beepManager.updatePrefs();
        inactivityTimer.onResume();

        source = IntentSource.NONE;
        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!hasSurface) {
            SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
            SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 扫描成功，处理反馈信息
     *
     * @param rawResult
     * @param barcode
     * @param scaleFactor
     */
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        inactivityTimer.onActivity();

        boolean fromLiveScan = barcode != null;
        //这里处理解码完成后的结果，此处将参数回传到Activity处理
        if (fromLiveScan) {
            if (rawResult.getText().contains("TITAN")) {//判断字符串内容方法 兼容5.0以上
                beepManager.playBeepSoundAndVibrate();
                Intent intent = getIntent();
                intent.putExtra("codedContent", rawResult.getText());//输入文字内容
                intent.putExtra("codedBitmap", barcode);//输入图片内容
                setResult(RESULT_OK, intent);
                //存在titan字符则跳转到支付界面
                Intent payment = new Intent(this, PaymentActivity.class);
                payment.putExtra("rawResult", rawResult.getText());
                startActivity(payment);
                finish();//扫描成功后直接关闭这个界面
            } else {
                //不存在则未定
                ToastUtils.showLongToast(context, getResources().getString(R.string.recognition_failed));
                finish();//关闭当前窗口
            }
        }

    }

    /**
     * 初始化Camera
     *
     * @param surfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            return;
        }
        try {
            // 打开Camera硬件设备
            cameraManager.openDriver(surfaceHolder);
            // 创建一个handler来打开预览，并抛出一个运行时异常
            if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats,
                        decodeHints, characterSet, cameraManager);
            }
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    /**
     * 显示底层错误信息并退出应用
     */
    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("错误");
        builder.setPositiveButton("正确", new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }


}

