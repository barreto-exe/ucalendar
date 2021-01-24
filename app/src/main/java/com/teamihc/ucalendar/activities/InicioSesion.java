package com.teamihc.ucalendar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.teamihc.ucalendar.R;

public class InicioSesion extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }
    
    public void btnIngresar_click(View view)
    {
        Intent i = new Intent(InicioSesion.this, MainActivity.class);
        startActivity(i);
    }
}