package com.vignesh.remainder.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vignesh.remainder.R;

public class SortHandler{
    Context context;
    String sort_by_array[];
    SortInterface sortInterface;
    Dialog sort_by_dialog;
    String selected_sort_order, selected_sort_by;

    public SortHandler(Context context, String sort_by_array[], SortInterface sortInterface){
        this.context = context;
        this.sortInterface = sortInterface;
        this.sort_by_array = sort_by_array;
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

        for(int index=0; index<sort_by_array.length; index++){
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(sort_by_array[index]);
            radioButton.setPadding(10, 10, 10, 10);
            radioButton.setTextAppearance(R.style.normal_text);
            sort_by_radiogroup.addView(radioButton);
        }

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortInterface.onSortBySaveButtonClick();
                sort_by_dialog.cancel();
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_dialog.cancel();
            }
        });

        sort_by_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = sort_by_dialog.findViewById(checkedId);
                selected_sort_by = radioButton.getText().toString();
            }
        });

        order_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.sort_by_ascending_order){
                    selected_sort_order = "ASC";
                }else if(checkedId == R.id.sort_by_descending_order){
                    selected_sort_order = "DESC";
                }
            }
        });
    }

    public String getSelectedSortOrder(){
        return selected_sort_order;
    }

    public String getSelectedSortBy(){
        return selected_sort_by;
    }
}
