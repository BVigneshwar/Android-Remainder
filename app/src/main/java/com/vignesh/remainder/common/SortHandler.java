package com.vignesh.remainder.common;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.vignesh.remainder.R;

public class SortHandler {
    Context context;

    public SortHandler(Context context){
        this.context = context;
    }
    public void createSortByDialog(){
        final Dialog sort_by_dialog = new Dialog(context, R.style.dialog_style);
        sort_by_dialog.setContentView(R.layout.sort_by_dialog);
        sort_by_dialog.setTitle(context.getResources().getString(R.string.sort_by));
        sort_by_dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(sort_by_dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        sort_by_dialog.getWindow().setAttributes(layoutParams);

        Button save_button = (Button)sort_by_dialog.findViewById(R.id.save_button);
        Button cancel_button = (Button)sort_by_dialog.findViewById(R.id.cancel_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_dialog.cancel();
            }
        });
    }
}
