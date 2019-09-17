package com.vignesh.remainder.notesmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.vignesh.remainder.R;


public class ColorGridViewAdapter extends BaseAdapter {
    Context context;
    int color_list[];
    CategoryDialog.CategoryColorSelector categoryColorSelector;

    ColorGridViewAdapter(Context context, int[] color_list, CategoryDialog.CategoryColorSelector categoryColorSelector){
        this.context = context;
        this.color_list = color_list;
        this.categoryColorSelector = categoryColorSelector;
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
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.color_round_button, null);
        }
        ImageButton color_button = (ImageButton) convertView.findViewById(R.id.round_color_button);
        final ImageView color_selector = (ImageView) convertView.findViewById(R.id.selector);

        color_button.setBackground(context.getDrawable(color_list[position]));
        color_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CategoryDialog.CategoryColorSelector.onSelect();
                color_selector.setVisibility(View.VISIBLE);
            }
        });
        return convertView;
    }
}
