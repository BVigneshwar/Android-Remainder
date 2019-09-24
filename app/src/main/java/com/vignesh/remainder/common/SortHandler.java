package com.vignesh.remainder.common;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;

import com.vignesh.remainder.R;

public class SortHandler {
    Context context;

    public SortHandler(Context context){
        this.context = context;
    }
    public void createSortByDialog(){
        Dialog sort_by_dialog = new Dialog(context, R.style.dialog_style);
        sort_by_dialog.setContentView(R.layout.category_dialog);
        sort_by_dialog.setTitle(context.getResources().getString(R.string.sort_by));
        sort_by_dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(sort_by_dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        sort_by_dialog.getWindow().setAttributes(layoutParams);
    }
}
