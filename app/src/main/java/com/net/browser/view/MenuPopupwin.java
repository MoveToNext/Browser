package com.net.browser.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.net.browser.MyApplication;
import com.net.browser.R;
import com.net.browser.ui.HistoryActivity;
import com.net.browser.ui.MyBrowserActivity;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/18 14:21
 */
public class MenuPopupwin extends PopupWindow implements View.OnClickListener {
    private View view;
    private int height;
    Context mContext;
    private boolean isFulllScreen;
    private WebView web;
    public MenuPopupwin(Context mContext, boolean isFulllScreen, ProgressWebView web) {
        this.isFulllScreen = isFulllScreen;
        this.mContext = mContext;
        this.web = web;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.take_tab_pop, null);
        view.findViewById(R.id.full_screen).setOnClickListener(this);
        view.findViewById(R.id.history).setOnClickListener(this);
        view.findViewById(R.id.add_bookmark).setOnClickListener(this);
        view.findViewById(R.id.setting).setOnClickListener(this);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
//        // 设置弹出窗体的宽和高
        this.setHeight(200);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.full_screen:
                changeScreen();
            break;
            case R.id.history:
                //历史记录
                WebBackForwardList webBackForwardList = web.copyBackForwardList();

                MyApplication applicationContext = (MyApplication) mContext.getApplicationContext();
                applicationContext.setWebBackForwardList(webBackForwardList);
                Intent intent = new Intent(mContext,HistoryActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("his", webBackForwardList);
//                intent.putExtras(bundle);
                mContext.startActivity(intent);
//                String url = webBackForwardList.getItemAtIndex(0).getUrl();
//                String title = webBackForwardList.getItemAtIndex(0).getTitle();
//                Log.d("历史记录", "title"+title+":url"+url);
                break;
        }
        dismiss();
    }

    private void changeScreen() {
        if (isFulllScreen) {
            WindowManager.LayoutParams params = ((Activity)mContext).getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            ((Activity)mContext).getWindow().setAttributes(params);
            ((Activity)mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            Toast.makeText(mContext,"开启全屏",Toast.LENGTH_SHORT).show();
        }else {
            WindowManager.LayoutParams params = ((Activity)mContext).getWindow().getAttributes();
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            ((Activity)mContext).getWindow().setAttributes(params);
            ((Activity)mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            Toast.makeText(mContext,"关闭全屏",Toast.LENGTH_SHORT).show();
        }
        MyBrowserActivity.isFulllScreen = !isFulllScreen;
    }
}
