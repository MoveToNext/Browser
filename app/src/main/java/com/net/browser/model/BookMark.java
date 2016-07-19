package com.net.browser.model;

import org.litepal.crud.DataSupport;

/**
 * @PackageName: com.net.browser.model
 * @Description:
 * @author: LanYing
 * @date: 2016/7/19 15:58
 */
public class BookMark extends DataSupport{
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
