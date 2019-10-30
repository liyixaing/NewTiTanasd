package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.AgreementContact;
import lanjing.com.titan.response.AgreementResponse;
import retrofit2.Response;

/**
 * 服务协议
 */
public class AgreementActivity extends MvpActivity<AgreementContact.AgreementPresent> implements AgreementContact.IAgreementView {

    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.webView)
    WebView webView;
    String url;

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoadingDialog();
        mPresent.getAgreement(context);
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//                设置缓存
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }
            }
        });
//        webView.loadUrl(url);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    protected AgreementContact.AgreementPresent createPresent() {
        return new AgreementContact.AgreementPresent();
    }

    @Override
    public void getAgreementResult(Response<AgreementResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
//            url = response.body().getData().getAgreement();
            webView.loadUrl(response.body().getData().getAgreement());
//            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
//            } else if (locale.equals(Locale.ENGLISH)) {
//                webView.loadUrl(response.body().getData().getAgreement());
//            }
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}
