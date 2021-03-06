package com.net.browser.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.net.browser.adapter.BookListAdapter;
import com.net.browser.adapter.HistoryListAdapter;
import com.net.browser.MyApplication;
import com.net.browser.R;
import com.net.browser.model.BookMark;

import org.litepal.crud.DataSupport;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/19 13:38
 */
@ContentView(R.layout.activity_history)
public class HistoryActivity extends BaseActivity implements View.OnClickListener {
    private boolean isMark;
    private boolean showBookMark = true;
    //专家推荐购买按钮
    @ViewInject(R.id.btn_buy)
    private Button btn_mark;
    //专家往日战绩按钮
    @ViewInject(R.id.btn_history)
    private Button btn_history;
    @ViewInject(R.id.history_list)
    ListView history_list;
    private HistoryListAdapter historyListAdapter;
    private WebBackForwardList historyList;
    private List<BookMark> bookMarkList;
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        MyApplication applicationContext = (MyApplication) getApplicationContext();
        historyList = applicationContext.getWebBackForwardList();
        historyListAdapter = new HistoryListAdapter(this, historyList);
        bookMarkList = DataSupport.findAll(BookMark.class);
        if (bookMarkList.size()>0){
            bookListAdapter = new BookListAdapter(this,bookMarkList);
            history_list.setAdapter(bookListAdapter);
        }
    }
    private void initView() {
        btn_mark.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        history_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private String url;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("mark", isMark+"");
                if (showBookMark){
                    url =  bookMarkList.get(position).getUrl();
                }else {
                    url = historyList.getItemAtIndex(position).getUrl();
                }
                Intent intent = new Intent(HistoryActivity.this,MyBrowserActivity.class);
                intent.putExtra(MyBrowserActivity.URL, url);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buy:
                btn_mark.setTextColor(Color.WHITE);
                btn_mark.setBackgroundColor(Color.TRANSPARENT);
                btn_history.setBackgroundColor(Color.WHITE);
                btn_history.setTextColor(Color.parseColor("#FFAA25"));
                isMark = true;
                selectAdapter();
                break;

            case R.id.btn_history:
                btn_history.setTextColor(Color.WHITE);
                btn_history.setBackgroundColor(Color.TRANSPARENT);
                btn_mark.setTextColor(Color.parseColor("#FFAA25"));
                btn_mark.setBackgroundColor(Color.WHITE);
                isMark = false;
                selectAdapter();
                break;
        }
    }

    private void selectAdapter() {
        if (isMark){
            history_list.setAdapter(bookListAdapter);
            showBookMark = true;
        }else {
            history_list.setAdapter(historyListAdapter);
            showBookMark = false;
        }
        isMark = !isMark;
    }
}
