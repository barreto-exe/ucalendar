package com.teamihc.ucalendar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.entidades.Evento;


public class DetallesEventoActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Evento evento;
    private TextView txtDescripcion;
    private Button btnVerMas;
    
    public DetallesEventoActivity()
    {
        evento = null;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_evento);
        String eventoJson = getIntent().getExtras().getString("evento");
        Gson g = new Gson();
        evento = g.fromJson(eventoJson, Evento.class);
        toolbar = findViewById(R.id.toolbardetalles);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        inicializarComponentes();
    }



    private void inicializarComponentes()
    {
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnVerMas = findViewById(R.id.btnVerMas);
    
        txtDescripcion.setVisibility(View.INVISIBLE);
        btnVerMas.setVisibility(View.INVISIBLE);
    }
}