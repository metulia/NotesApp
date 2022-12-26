package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isLandscape()) {
            initDrawer();
        }

        /*
        if (savedInstanceState == null) getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_container, new NotesFragment())
                .commit();

         */
        if (savedInstanceState == null) addFragment(ListFragment.newInstance());
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initDrawer() {

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_calendar:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("")
                                .replace(R.id.notes_container, new CalendarFragment())
                                .commit();
                        drawerLayout.close();
                        return true;

                    case R.id.action_exit:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Внимание!")
                                .setMessage("Вы действительно хотите завершить работу приложения?")
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                        Toast.makeText(MainActivity.this,
                                                "Работа с приложением завершена", Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setNegativeButton("Нет", null)
                                .show();
                        return true;
                }
                return false;
            }
        });
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_calendar:
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("")
                        .replace(R.id.notes_container, new CalendarFragment())
                        .commit();
                return true;

            case R.id.action_exit:
                new MyAlertDialogFragment().show(getSupportFragmentManager(), MyAlertDialogFragment.TAG);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}