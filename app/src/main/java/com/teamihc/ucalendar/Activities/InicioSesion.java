package com.teamihc.ucalendar.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.teamihc.ucalendar.R;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }

    public void ingresar(View view) {
        Intent i= new Intent(InicioSesion.this, MainActivity.class);
        startActivity(i);
    }
}