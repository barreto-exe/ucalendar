package com.teamihc.ucalendar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.basedatos.Configuraciones;
import com.teamihc.ucalendar.backend.basedatos.SqliteOp;

public class SplashActivity extends AppCompatActivity
{
    //int SPLASH_TIEMPO = 3500; //el splash se va a mostrar por 3.5 segundos
    int SPLASH_TIEMPO = 0; //el splash se va a mostrar por 3.5 segundos
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SqliteOp.verificarBaseDatos(getAssets());
        setContentView(R.layout.activity_splash);
        
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Boolean sesionIniciada = !Configuraciones.getCorreoSesion().isEmpty();
                Intent intent;
                if(sesionIniciada)
                {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                else
                {
                    intent = new Intent(SplashActivity.this, InicioSesion.class);
                }
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIEMPO);
    }
}