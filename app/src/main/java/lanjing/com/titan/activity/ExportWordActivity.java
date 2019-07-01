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
 * /**
 * 钱包管理  导出助记词
 */
public class ExportWordActivity extends XActivity {


    @BindView(R.id.word_one)
    TextView wordOne;
    @BindView(R.id.word_two)
    TextView wordTwo;
    @BindView(R.id.word_three)
    TextView wordThree;
    @BindView(R.id.tv_copy_word)
    TextView tvCopyWord;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public void initData(Bundle savedInstanceState) {
        String help = getIntent().getStringExtra("word");
        String [] word = help.split("[,]");
        wordOne.setText(word[0]);
        wordTwo.setText(word[1]);
        wordThree.setText(word[2]);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_export_word;
    }


    @OnClick(R.id.tv_copy_word)
    public void onViewClicked() {
        myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        String wordHelp = wordOne.getText().toString()+" "+wordTwo.getText().toString()+" "+wordThree.getText().toString();
        myClip = ClipData.newPlainText("text", wordHelp);
        myClipboard.setPrimaryClip(myClip);
        ToastUtils.showShortToast(context, wordHelp + getResources().getString(R.string.word_copy_tip));
    }
}
