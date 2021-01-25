package com.teamihc.ucalendar.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Usuario;

import org.apache.commons.codec.digest.DigestUtils;

public class InicioSesion extends AppCompatActivity
{
    private TextView txtCorreo;
    private TextView txtPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        InicializarComponentes();
    }
    
    private void InicializarComponentes()
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