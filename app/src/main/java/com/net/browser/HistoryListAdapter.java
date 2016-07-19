package com.net.browser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.history_list, null);
            holder.textView = (TextView) convertView.findViewById(R.id.teee);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.textView.setText(list.getItemAtIndex(position).getUrl());
        return convertView;
    }

    class ViewHolder{
        private TextView textView;
    }
}
