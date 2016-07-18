package com.net.browser;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @PackageName: com.net.browser
 * @Description:
 * @author: LanYing
 * @date: 2016/7/18 10:37
 */
public class NewActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        findViewById(R.id.btn_open).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MyBrowserActivity.class);
        intent.putExtra("url", "http://www.163.com");
        startActivity(intent);
    }
}
