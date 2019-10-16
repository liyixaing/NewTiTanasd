package lanjing.com.titan.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.contact.PaymentCode;
import lanjing.com.titan.response.FriendListResponse;
import lanjing.com.titan.util.QRCodeUtil;
import retrofit2.Response;

/**
 * 收款码界面
 */
public class PaymentCodeActivity extends MvpActivity<PaymentCode.FriendListPresent> implements PaymentCode.IFriendListView {


    @BindView(R.id.ll_save_album)
    LinearLayout ll_save_album;//保存到相册
    @BindView(R.id.ll_imageView)
    LinearLayout ll_imageView;
    @BindView(R.id.iv_er_code)
    ImageView iv_er_code;//二维码展示
    @BindView(R.id.tv_address)
    TextView tv_address;//地址显示
    @BindView(R.id.tv_label)
    TextView tv_label;//标签显示
    @BindView(R.id.ll_copy_address)
    LinearLayout ll_copy_address;//复制地址按钮
    @BindView(R.id.ll_copy_label)
    LinearLayout ll_copy_label;//复制标签

    String walletAddress, labelAddress;

    @Override
    public void initData(Bundle savedInstanceState) {
        walletAddress = getIntent().getStringExtra("walletAddress");
        labelAddress = getIntent().getStringExtra("labelAddress");
        tv_address.setText("地址：" + walletAddress);//地址赋值
        tv_label.setText("标签：" + labelAddress);//标签赋值

        ArrayMap<String, String> map = new ArrayMap();
        map.put("addres", walletAddress);
        map.put("label", labelAddress);
        Log.e("map", String.valueOf(map));

        //生成二维码
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_logo);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        final Bitmap bmm = bd.getBitmap();
        Bitmap asd = QRCodeUtil.createQRCode("TITAN"+","+walletAddress+","+labelAddress, 500);
        iv_er_code.setImageBitmap(asd);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_code;
    }

    @OnClick({R.id.ll_save_album, R.id.ll_copy_address, R.id.ll_copy_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_save_album://保存图片
                initSaveAlbum();//截屏保存
//                createViewBitmap(ll_imageView);//单个布局保存
                break;
            case R.id.ll_copy_address://复制地址
                ClipboardManager address = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                address.setText(walletAddress);
                ToastUtils.showLongToast(context, "复制成功，可以发给小伙伴们了。");
                break;
            case R.id.ll_copy_label://复制标签
                ClipboardManager lable = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                lable.setText(labelAddress);
                ToastUtils.showLongToast(context, "复制成功，可以发给小伙伴们了。");
                break;

        }
    }


    //生成部分view的图片
    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        saveBitmap(bitmap);
        return bitmap;
    }

    //将bitmap格式的图片保存到本地
    public void saveBitmap(Bitmap bm) {
        Log.e("xiaoqiang", "保存图片");
        long timeStamp = System.currentTimeMillis();
        Log.d("xxxxx", String.valueOf(timeStamp));
        File sdDir = Environment.getExternalStorageDirectory();
        String tmpFile = sdDir.toString() + "/DCIM/" + timeStamp + ".jpg";
        File f = new File(tmpFile);
        ToastUtils.showLongToast(context, "保存成功");
        if (f.exists())
            return;
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("小强", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //截屏保存图片
    public void initSaveAlbum() {
        long timeStamp = System.currentTimeMillis();
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        if (bitmap != null) {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + timeStamp + ".png";
                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                ToastUtils.showLongToast(context, "保存成功");
                Log.d("a7888", "存储完成");
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void getFriendListResult(Response<FriendListResponse> response) {

    }

    @Override
    public void getDataFailed() {

    }

    @Override
    protected PaymentCode.FriendListPresent createPresent() {
        return new PaymentCode.FriendListPresent();
    }
}
