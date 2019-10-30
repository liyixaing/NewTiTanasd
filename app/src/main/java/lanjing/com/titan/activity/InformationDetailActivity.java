package lanjing.com.titan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.view.TitleView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;

/**
 * 资讯详情界面  点开是H5的页面  根据系统判断中英文
 */
public class InformationDetailActivity extends XActivity {

    @BindView(R.id.title_lay)
    TitleView titleLay;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public void initData(Bundle savedInstanceState) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置缓存
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
                try {
                    if (newProgress == 100) {
                        progressBar1.setVisibility(View.INVISIBLE);//加载完网页进度条消失  invisible使用这种隐藏方式就不会出现崩溃状态
                    } else {
                        progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        progressBar1.setProgress(newProgress);//设置进度值
                    }
                } catch (Exception e) {
                    Log.e("TAG", "异常抛出");
                }

            }
        });
        webView.loadUrl(getIntent().getStringExtra("content"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_information_detail;
    }
}
