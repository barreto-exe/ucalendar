package com.teamihc.ucalendar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamihc.ucalendar.Fragments.AgendaFragment;
import com.teamihc.ucalendar.Fragments.CalendarioFragment;
import com.teamihc.ucalendar.Fragments.InicioFragment;
import com.teamihc.ucalendar.R;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.topbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        dialog = new Dialog(this);

    }

    public void ingresosesion(View v){
        Intent i= new Intent(MainActivity.this, InicioSesion.class);
        startActivity(i);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment =null;
            switch (item.getItemId())
            {
                case R.id.nav_home: {
                   fragment= new InicioFragment();
                    break;
                }
                case R.id.nav_agenda: {
                    fragment= new AgendaFragment();
                    break;
                }
                case R.id.nav_calendario: {
                    fragment= new CalendarioFragment();
                    break;
                }
            }
            getFragmentManager().beginTransaction().replace(R.id.layout_principal,fragment).commit();
            return true;
        }
    };


}