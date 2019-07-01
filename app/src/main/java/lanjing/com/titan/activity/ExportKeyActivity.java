package lanjing.com.titan.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;

/**
 * 钱包管理  导出私钥
 */
public class ExportKeyActivity extends XActivity {

    @BindView(R.id.tv_private_key)
    TextView tvPrivateKey;
    @BindView(R.id.tv_copy_private_key)
    TextView tvCopyPrivateKey;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public void initData(Bundle savedInstanceState) {
        tvPrivateKey.setText(getIntent().getStringExtra("key"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_export_key;
    }


    @OnClick(R.id.tv_copy_private_key)
    public void onViewClicked() {
        myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        String key = tvPrivateKey.getText().toString();
        myClip = ClipData.newPlainText("text", key);
        myClipboard.setPrimaryClip(myClip);
        ToastUtils.showShortToast(context, key + getResources().getString(R.string.key_copy_tip));
    }
}
