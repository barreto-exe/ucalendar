package com.teamihc.ucalendar.backend.entidades;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamihc.ucalendar.activities.MainActivity;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.basedatos.Configuraciones;
import com.teamihc.ucalendar.backend.entidades.enums.Sexo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Usuario extends Persona
{
    String passEncriptada;

    public Usuario(String correo, String passDesencriptada)
    {
        this.correo = correo;
        //this.passEncriptada = Herramientas.encriptarMd5(passDesencriptada);
        this.passEncriptada = passDesencriptada;
    }

    /**
     * Valida la combinación de correo y contraseña en la base de datos.
     * @param context objeto en donde se realiza la consulta (de tipo activity, view, etc).
     * @param destino clase del Activity que se abrirá si la combinación es correcta.
     */
    public void validar(Context context, Class<?> destino)
    {
        StringRequest stringRequest =
            new StringRequest
                (
                    Request.Method.POST,
                    Herramientas.URLServicio("validar_usuario"),
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            String mensaje;
                            if(!response.isEmpty())
                            {
                                Intent i = new Intent(context, destino);
                                context.startActivity(i);
                                
                                Configuraciones.setCorreoSesion(correo);
                                
                                mensaje = "Inicio de sesión exitoso.";
                            }
                            else
                            {
                                mensaje = "Usuario y contraseña incorrectos.";
                            }
    
                            Toast.makeText(context, mensaje,Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(context,"Ha ocurrido un error. \n" + error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                )
            {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("correo", correo);
                    parametros.put("password", passEncriptada);
                    return parametros;
                }
            };
    
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
