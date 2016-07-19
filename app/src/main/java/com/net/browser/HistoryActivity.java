package com.net.browser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebBackForwardList;
import android.widget.ListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/19 13:38
 */
@ContentView(R.layout.activity_history)
public class HistoryActivity extends BaseActivity {

    @ViewInject(R.id.history_list)
    ListView history_list;
    private HistoryListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        MyApplication applicationContext = (MyApplication) getApplicationContext();
        WebBackForwardList list = applicationContext.getWebBackForwardList();
        adapter = new HistoryListAdapter(this,list);
    }
    private void initView() {
        history_list.setAdapter(adapter);
    }

}
