package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ExemptionContact;
import lanjing.com.titan.response.ExemptionResponse;
import retrofit2.Response;

/**
 * 免责声明
 */
public class DisclaimerActivity extends MvpActivity<ExemptionContact.ExemptionPresent> implements ExemptionContact.IExemptionView {


    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public void initData(Bundle savedInstanceState) {
        showLoadingDialog();
        mPresent.getExemption(context);
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

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_disclaimer;
    }


    @Override
    protected ExemptionContact.ExemptionPresent createPresent() {
        return new ExemptionContact.ExemptionPresent();
    }

    @Override
    public void getExemptionResult(Response<ExemptionResponse> response) {
        dismissLoadingDialog();
        if(response.body().getCode() == Constant.SUCCESS_CODE){
            Locale locale = getResources().getConfiguration().locale;//判断当前的语言
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                webView.loadUrl(response.body().getExemptionCH());
            } else if (locale.equals(Locale.ENGLISH)) {
                webView.loadUrl(response.body().getExemptionEN());
            }
        }else {
            ToastUtils.showShortToast(context,response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context,getResources().getString(R.string.network_error));
    }
}
