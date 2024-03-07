package com.example.super_ponic_ultra.Demoone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.super_ponic_ultra.R;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerlayout;

    ActionBarDrawerToggle toggel;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getting navigation view id
        navigationView=(NavigationView)  findViewById(R.id.navigationView);
//getting drawerlayout view id
        drawerlayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        frameLayout=(FrameLayout) findViewById(R.id.framelayout) ;
        toggel=new ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.Open,R.string.close);
        //toggel listen by drawer layout
        drawerlayout.addDrawerListener(toggel);
        toggel.syncState();
        //making Toast for click event then we will able to see blaack small popup screen to see what we clicked
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                int id = item.getItemId();
                drawerlayout.closeDrawer(GravityCompat.START);


                if (id == R.id.dashboard1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new DashboardFragment()).commit();
                    Toast.makeText(MainActivity.this, "Dashboard clicked", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.plantarium) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new PlantariumFragment()).commit();
                    Toast.makeText(MainActivity.this, "plantarium clicked", Toast.LENGTH_SHORT).show();
                }
                 else if (id == R.id.plantGaurd) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new PlantGaurdFragment()).commit();
                    Toast.makeText(MainActivity.this, "plantGaurd clicked", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.chatAssist) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new ChatAssistanceFragment()).commit();

                    Toast.makeText(MainActivity.this, "chatAssist clicked", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nutriGrow) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new NutriGrowFragment()).commit();
                    Toast.makeText(MainActivity.this, "nutriGrow clicked", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.plantLive) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new PlantLiveFragment()).commit();
                    Toast.makeText(MainActivity.this, "plantLive clicked", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.about_Us) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new AboutUS()).commit();
                    Toast.makeText(MainActivity.this, "about_Us clicked", Toast.LENGTH_SHORT).show();

                }

                else {
                    Toast.makeText(MainActivity.this, "Unhandled menu item", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


    }

}