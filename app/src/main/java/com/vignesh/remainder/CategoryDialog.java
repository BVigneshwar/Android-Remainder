package com.vignesh.remainder;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryDialog {
    Context context;

    CategoryDialog(Context context){
        this.context = context;
    }

    static class CategoryColorSelector{
        static GridView gridView;
        CategoryColorSelector(GridView gridView){
            this.gridView = gridView;
        }
        public static void onSelect(){
            for(int i=0; i<gridView.getChildCount(); i++){
                View v = gridView.getChildAt(i);
                ImageView selector_visibility = v.findViewById(R.id.selector);
                selector_visibility.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void openDialog(){
        final Dialog dialog = new Dialog(context, R.style.dialog_style);
        dialog.setContentView(R.layout.category_dialog);
        dialog.setTitle(context.getResources().getString(R.string.add_category));
        final GridView color_gridview = dialog.findViewById(R.id.color_gridview);
        Button save_button = dialog.findViewById(R.id.save_category_button);
        Button cancel_button = dialog.findViewById(R.id.cancel_button);
        color_gridview.setAdapter(new ColorGridViewAdapter(context, AppConstants.category_drawable_color_list, new CategoryDialog.CategoryColorSelector(color_gridview)));
        dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);

        save_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RelativeLayout relativeLayout = (RelativeLayout)((LinearLayout)v.getParent()).getParent();
                EditText et_category_title = (EditText) relativeLayout.findViewById(R.id.et_category_title);
                GridView gridView = relativeLayout.findViewById(R.id.color_gridview);
                int i=0;
                for(; i<gridView.getCount(); i++){
                    View view = gridView.getChildAt(i);
                    ImageView selector_visibility = view.findViewById(R.id.selector);
                    if(selector_visibility.getVisibility() == View.VISIBLE){
                        break;
                    }
                }
                String title = et_category_title.getText().toString();
                String color = AppConstants.color_list[i];
                if(title == null){
                    Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                }else{
                    NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(context);
                    if(notesDatabaseHandler.insertCategory(title, color))
                        Toast.makeText(context, "Category added successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context, "category addition failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    ((AppCompatActivity)context).getSupportFragmentManager().popBackStack();

                }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
