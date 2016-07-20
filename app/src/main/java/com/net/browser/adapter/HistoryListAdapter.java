package com.net.browser.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.net.browser.R;

/**
 * @PackageName: com.net.browser
 * @Description: 历史记录的适配器
 * @author: LanYing
 * @date: 2016/7/19 13:41
 */
public class HistoryListAdapter extends BaseAdapter{
    private Context context;
    private WebBackForwardList list;
    public HistoryListAdapter(Context context, WebBackForwardList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getSize();
    }

    @Override
    public WebHistoryItem getItem(int position) {
        return list.getItemAtIndex(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.history_list, null);
            holder.url = (TextView) convertView.findViewById(R.id.url);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.pic_dele = (ImageView) convertView.findViewById(R.id.pic_dele);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        final WebHistoryItem item = getItem(position);
        holder.url.setText(item.getUrl());
        holder.title.setText(item.getTitle());
        holder.pic_dele.setVisibility(View.GONE);
        return convertView;
    }

    class ViewHolder{
        private TextView url;
        private TextView title;
        private ImageView pic_dele;
    }
}
