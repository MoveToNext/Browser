package com.net.browser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.net.browser.R;
import com.net.browser.adapter.PageListAdapter;
import com.net.browser.model.PageOpen;

import org.litepal.crud.DataSupport;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/18 10:37
 */
@ContentView(R.layout.activity_new)
public class NewActivity extends BaseActivity implements View.OnClickListener {
    @ViewInject(R.id.btn_open)
    Button btn_open;
    @ViewInject(R.id.page_list)
    ListView page_list;
    private List<PageOpen> list;
    private PageListAdapter pageListAdapter;
    private String title;
    private String url;
    private PageOpen pageOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        pageOpen = new PageOpen();
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        pageOpen.setUrl(url);
        pageOpen.setTitle(title);
        list = DataSupport.findAll(PageOpen.class);
        list.add(pageOpen);
        if (list.size()>0){
            pageListAdapter = new PageListAdapter(this,list);
            page_list.setAdapter(pageListAdapter);
        }
    }

    private void initView() {
        btn_open.setOnClickListener(this);
        page_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewActivity.this,MyBrowserActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        pageOpen.save();
        Intent intent = new Intent(this,MyBrowserActivity.class);
        intent.putExtra("url", "http://www.baidu.com");
        startActivity(intent);
        this.finish();
    }
}
