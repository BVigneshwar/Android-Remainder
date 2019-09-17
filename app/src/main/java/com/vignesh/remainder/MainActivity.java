package com.vignesh.remainder;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vignesh.remainder.notesmodule.NotesFragment;
import com.vignesh.remainder.taskmodule.TaskFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.notes_tab :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NotesFragment()).commit();
                        break;
                    case R.id.task_tab :
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TaskFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}
