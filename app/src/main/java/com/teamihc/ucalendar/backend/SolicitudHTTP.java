package com.teamihc.ucalendar.backend;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public abstract class SolicitudHTTP
{
    private Context context;
    private String nombreServicio;
    private Map<String, String> parametros;
    
    public SolicitudHTTP(Context context, String nombreServicio)
    {
        this.parametros = new HashMap<>();
        this.context = context;
        this.nombreServicio = nombreServicio;
    }
    
    //<editor-fold desc="Getters & Setters">
    public Context getContext()
    {
        return context;
    }
    public void setContext(Context context)
    {
        this.context = context;
    }
    public String getNombreServicio()
    {
        return nombreServicio;
    }
    public void setNombreServicio(String nombreServicio)
    {
        this.nombreServicio = nombreServicio;
    }
    public Map<String, String> getParametros()
    {
        return parametros;
    }
    public void setParametros(Map<String, String> parametros)
    {
        this.parametros = parametros;
    }
    //</editor-fold>
    
    public void ejecutar()
    {
        //Creando una solicitud http
        StringRequest stringRequest =
        new StringRequest
        ( //Inicio constructor
            
            //Método en que se hace la solicitud
            Request.Method.POST,
        
            //Indicar el servicio que procesa la solicitud
            Herramientas.URLServicio(nombreServicio),
        
            //Receptor de respuesta
            new Response.Listener<String>()
            {
                @Override
                public void onResponse(String respuesta)
                {
                    try
                    {
                        eventoRespuestaHTTP(respuesta);
                    }
                    catch (Exception e)
                    {
                        Log.e("json", e.getMessage());
                    }
                }
            },
        
            //Receptor de error
            new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(context,"Ha ocurrido un error. \n" + error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        ) //Fin constructor
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    
    public abstract void eventoRespuestaHTTP(String response);
}
