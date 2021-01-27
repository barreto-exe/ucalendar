package com.teamihc.ucalendar.backend.entidades;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.fragments.InicioFragment;
import com.teamihc.ucalendar.fragments.MuestraEventos;

import java.util.ArrayList;
import java.util.Date;

public class Evento
{
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinal;
    private String lugar;
    private int idCreador;
    private String color;
    private int cantidadLikes;
    private String foto;
    
    public Evento(String nombre, String descripcion, Date fechaInicio, Date fechaFinal, String lugar, int idCreador, String color, int cantidadLikes, String foto)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.lugar = lugar;
        this.idCreador = idCreador;
        this.color = color;
        this.cantidadLikes = cantidadLikes;
        this.foto = foto;
    }
    
    public static void obtenerEventos(Context context, MuestraEventos muestraEventos)
    {
        //Creando una solicitud http
        StringRequest stringRequest =
        new StringRequest
        ( //Inicio constructor
            
            //Método en que se hace la solicitud
            Request.Method.POST,
            
            //Indicar el servicio que procesa la solicitud
            Herramientas.URLServicio("obtener_eventos"),
            
            //Receptor de respuesta
            new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    //Recibir arreglo de eventos
                    Gson g = new Gson();
                    Evento[] test = g.fromJson(response, Evento[].class);

                    //Convertirlo en lista
                    ArrayList<Evento> listaEventos = new ArrayList<>();
                    for (Evento evento : test)
                    {
                        listaEventos.add(evento);
                    }
    
                    //Actualizar eventos mostrados en el inicio
                    muestraEventos.setEventos(listaEventos);
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
        ); //Fin constructor
    
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    
    @Override
    public String toString()
    {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                '}';
    }
}
