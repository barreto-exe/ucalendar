package com.teamihc.ucalendar.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.entidades.Usuario;

public class InicioSesionActivity extends AppCompatActivity
{
    private TextView txtCorreo;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        inicializarComponentes();
    }

    private void inicializarComponentes()
    {
        txtCorreo   = (TextView) findViewById(R.id.txtCorreo);
        txtPassword = (TextView) findViewById(R.id.txtPass);
    }

    public void btnIngresar_click(View view)
    {
        Usuario usuario = new Usuario
        (
                txtCorreo.getText().toString(),
                txtPassword.getText().toString()
        );

        usuario.validar(this, MainActivity.class);
    }
}