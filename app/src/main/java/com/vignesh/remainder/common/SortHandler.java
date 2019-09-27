package com.vignesh.remainder.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.vignesh.remainder.R;

public class SortHandler implements RadioGroup.OnCheckedChangeListener{
    Context context;
    Dialog sort_by_dialog;
    String sort_by, order;

    public SortHandler(Context context){
        this.context = context;
    }
    public void createSortByDialog(){
        sort_by_dialog = new Dialog(context, R.style.dialog_style);
        sort_by_dialog.setContentView(R.layout.sort_by_dialog);
        sort_by_dialog.setTitle(context.getResources().getString(R.string.sort_by));
        sort_by_dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(sort_by_dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        sort_by_dialog.getWindow().setAttributes(layoutParams);

        Button save_button = (Button)sort_by_dialog.findViewById(R.id.save_button);
        Button cancel_button = (Button)sort_by_dialog.findViewById(R.id.cancel_button);
        RadioGroup sort_by_radiogroup = (RadioGroup) sort_by_dialog.findViewById(R.id.sort_by_criteria_group);
        RadioGroup order_radiogroup = (RadioGroup) sort_by_dialog.findViewById(R.id.sort_by_order_group);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_dialog.cancel();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_dialog.cancel();
            }
        });

        sort_by_radiogroup.setOnCheckedChangeListener(this);
        order_radiogroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getId() == R.id.sort_by_criteria_group){
            if(checkedId == R.id.sort_by_title){

            }else if(checkedId == R.id.sort_by_created_time){

            }else if(checkedId == R.id.sort_by_last_modified){

            }
        }else if(group.getId() == R.id.sort_by_order_group){
            if(checkedId == R.id.sort_by_ascending_order){

            }else if(checkedId == R.id.sort_by_descending_order){

            }
        }
    }
}
