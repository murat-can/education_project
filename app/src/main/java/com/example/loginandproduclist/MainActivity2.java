package com.example.loginandproduclist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.loginandproduclist.category.FragmentCategoryMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity2 extends AppCompatActivity {
    private BottomNavigationView bottom_navigation_bar;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_hold, new Fragment_mainActivity2()).commit();

        bottom_navigation_bar = findViewById(R.id.bottom_navigation_bar);
        bottom_navigation_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                 //   Toast.makeText(getApplicationContext(), "1.tıklandı", Toast.LENGTH_SHORT).show();
                    fragment = new Fragment_mainActivity2();
                }
                if (item.getItemId() == R.id.nav_Categorys) {
                //    Toast.makeText(getApplicationContext(), "category tıklandı", Toast.LENGTH_SHORT).show();
                    fragment = new FragmentCategoryMain();
                }
                if (item.getItemId() == R.id.nav_my_favorites) {
                //    Toast.makeText(getApplicationContext(), "favoriler tıklandı", Toast.LENGTH_SHORT).show();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_hold, fragment).commit();
                return false;
            }
        });
    }
}