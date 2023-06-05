package com.example.staffmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.staffmanagement.databinding.ActivityStaffhomeBinding;

public class staffhome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityStaffhomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStaffhomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarStaffhome.toolbar);
//        binding.appBarStaffhome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_staffhome);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.staffhome, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_staffhome);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.nav_home){
            startActivity(new Intent(getApplicationContext(),staffhome.class));
        }
        if(id==R.id.profile){
            startActivity(new Intent(getApplicationContext(),ViewProfile.class));
        }
        if(id==R.id.attendance){
            startActivity(new Intent(getApplicationContext(),view_attendance.class));
        }
        if(id==R.id.leave_request){
            startActivity(new Intent(getApplicationContext(),view_leave_request.class));
        }
        if(id==R.id.resign){
            startActivity(new Intent(getApplicationContext(),view_resignation_status.class));
        }
        if(id==R.id.assignedwork){
            startActivity(new Intent(getApplicationContext(),View_assign_work.class));
        }
        if(id==R.id.notification){
            startActivity(new Intent(getApplicationContext(),Notification.class));
        }
        if(id==R.id.salary){
            startActivity(new Intent(getApplicationContext(),viewsalary.class));
        }
        if(id==R.id.logout){
            startActivity(new Intent(getApplicationContext(),login.class));
        }



        return true;
    }
}