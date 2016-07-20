package com.net.browser.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.net.browser.R;
import com.net.browser.model.BookMark;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @PackageName: com.net.browser
 * @Description: 历史记录的适配器
 * @author: LanYing
 * @date: 2016/7/19 13:41
 */
public class BookListAdapter extends BaseAdapter{
    private Context context;
    private List<BookMark> bookMarkList;
    public BookListAdapter(Context context, List<BookMark> bookMarkList) {
        this.context = context;
        this.bookMarkList = bookMarkList;
    }


    @Override
    public int getCount() {
        return bookMarkList.size();
    }

    @Override
    public BookMark getItem(int position) {
        return bookMarkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        final BookMark item = getItem(position);
        holder.url.setText(item.getUrl());
        holder.title.setText(item.getTitle());
        holder.pic_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookMarkList.remove(position);
                int delete = DataSupport.deleteAll(BookMark.class,"title = ?",item.getTitle());
                Log.d("delete", "" + delete+"position"+position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView url;
        private TextView title;
        private ImageView pic_dele;
    }
}
