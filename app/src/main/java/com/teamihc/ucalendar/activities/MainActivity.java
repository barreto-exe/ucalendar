package com.teamihc.ucalendar.activities;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.basedatos.Configuraciones;
import com.teamihc.ucalendar.fragments.AgendaFragment;
import com.teamihc.ucalendar.fragments.CalendarioFragment;
import com.teamihc.ucalendar.fragments.InicioFragment;

public class MainActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Dialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        inicializarComponentes();
        
        getFragmentManager().beginTransaction().replace(R.id.layout_principal, new InicioFragment()).commit();
    }
    
    public void inicializarComponentes()
    {
        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.view_top_bar);
        setSupportActionBar(toolbar);
        
        //Bottom bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        dialog = new Dialog(this);
    }
    
    private void cerrarSesion()
    {
        Configuraciones.setCorreoSesion("");
        Intent i = new Intent(MainActivity.this, InicioSesion.class);
        startActivity(i);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_bar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.menu_inicio_cerrar_sesion:
                cerrarSesion();
                break;
        }
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment fragment = null;
            switch (item.getItemId())
            {
                case R.id.nav_home:
                {
                    fragment = new InicioFragment();
                    break;
                }
                case R.id.nav_agenda:
                {
                    fragment = new AgendaFragment();
                    break;
                }
                case R.id.nav_calendario:
                {
                    fragment = new CalendarioFragment();
                    break;
                }
            }
            getFragmentManager().beginTransaction().replace(R.id.layout_principal,fragment).commit();
            return true;
        }
    };
    
}