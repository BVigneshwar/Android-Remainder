package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.List;

public class ColorGridViewAdapter extends BaseAdapter {
    Context context;
    int color_list[];
    ImageButton imageButton;
    ColorGridViewAdapter(Context context, int color_list[]){
        this.context = context;
        this.color_list = color_list;
    }
    @Override
    public int getCount() {
        return color_list.length;
    }

    @Override
    public Object getItem(int position) {
        return color_list[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = new ImageButton(context);
        }
        convertView.setBackgroundColor(context.getColor(color_list[position]));
        convertView.setLayoutParams(new ViewGroup.LayoutParams(80, 80));
        return convertView;
    }
}
