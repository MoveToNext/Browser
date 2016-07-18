package com.net.browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/18 10:01
 */
public class MyBrowserActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private final static String LOG = "MyBrowserActivity";
    @ViewInject(R.id.webview) private ProgressWebView web;
    @ViewInject(R.id.edit) private EditText edit;
//    @ViewInject(R.id.btn_go)  private Button btn_go;
    @ViewInject(R.id.tab_back)  private ImageView tab_back;
    @ViewInject(R.id.tab_forward)  private ImageView tab_forward;
    @ViewInject(R.id.tab_menu)  private ImageView tab_menu;
    @ViewInject(R.id.tab_home)  private ImageView tab_home;
    @ViewInject(R.id.tab_add)  private ImageView tab_add;
    @ViewInject(R.id.ll_tab)  private LinearLayout ll_tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybrowser);
        x.view().inject(this);
        initView();
        initWebView();
    }

    private void initView() {
//        btn_go.setOnClickListener(this);
        edit.setOnEditorActionListener(this);
        tab_back.setOnClickListener(this);
        tab_forward.setOnClickListener(this);
        tab_menu.setOnClickListener(this);
        tab_home.setOnClickListener(this);
        tab_add.setOnClickListener(this);
    }

    private void initWebView() {
        web.loadUrl("http://www.baidu.com");
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String url = intent.getStringExtra("url");
        Log.d(LOG, "onNewIntent-------"+url);
        if (url!=null){
            web.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG, "onDestroy-------");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (web.canGoBack()){
                web.goBack();
                return true;
            }else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_back:
                if (web.canGoBack()){
                    web.goBack();
                }
                break;
            case R.id.tab_forward:
                if (web.canGoForward()){
                    web.goForward();
                }
                break;
            case R.id.tab_menu:
                showPopFormBottom();
                break;
            case R.id.tab_home:

                break;
            case R.id.tab_add:
                startActivity(new Intent(this,NewActivity.class));
                break;
        }
    }

    private void showPopFormBottom() {
        int[] location = new int[2];
        ll_tab.getLocationOnScreen(location);
        TakePhotoPopWin takePhotoPopWin = new TakePhotoPopWin(this);
        //showAtLocation(View parent, int gravity, int x, int y)
        takePhotoPopWin.showAtLocation(ll_tab, Gravity.NO_GRAVITY, location[0], location[1]-takePhotoPopWin.getHeight());
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            String trim = edit.getText().toString().trim();
            if (!TextUtils.isEmpty(trim)){
                web.loadUrl(trim);
            }
        }
        return true;
    }
}
