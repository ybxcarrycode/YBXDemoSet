package com.ybxcc.testinysx.ybxdemoset.palette_cutImg;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ybxcc.testinysx.ybxdemoset.R;

import java.util.List;

public class ColorAdapter extends BaseAdapter {

    private Context context;
    List<Palette.Swatch> list;

    public ColorAdapter(Context context, List<Palette.Swatch> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cutimg_color_list, parent, false);
            holder = new ViewHolder();
            holder.img1 = convertView.findViewById(R.id.img1);
            holder.img2 = convertView.findViewById(R.id.img2);
            holder.img3 = convertView.findViewById(R.id.img3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img1.setBackgroundColor(list.get(position).getRgb());
        holder.img2.setBackgroundColor(list.get(position).getBodyTextColor());
        holder.img3.setBackgroundColor(list.get(position).getTitleTextColor());

        return convertView;
    }


    static class ViewHolder {
        ImageView img1;
        ImageView img2;
        ImageView img3;

    }

}
