package lanjing.com.titan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.util.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.util.MoneyUtil;

/**
 * 节点
 */
public class NodeActivity extends XActivity {

    @BindView(R.id.tv_usd_node_num)
    TextView tvUsdNodeNum;

    @Override
    public void initData(Bundle savedInstanceState) {
        tvUsdNodeNum.setText(MoneyUtil.formatFour(SPUtils.getString(Constant.NODE_NUM,"",context)));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_node;
    }


}
