package com.teamihc.ucalendar.backend.entidades;

import android.content.Context;
import android.text.BoringLayout;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamihc.ucalendar.adapters.FeedRVAdapter;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.SolicitudHTTP;
import com.teamihc.ucalendar.backend.basedatos.Configuraciones;
import com.teamihc.ucalendar.fragments.MuestraEventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Evento
{
    private int posicionLista;
    private int idEvento;
    private String nombre;
    private String descripcion;
    private int cantidadLikes;
    private int cantidadGuardados;
    private Date fechaInicio;
    private Date fechaFinal;
    private String lugar;
    private String color;
    private String foto;
    private String fotoCreador;
    private String nombreCreador;
    private Boolean tieneLike, tieneGuardado;
    
    public Evento(int idEvento, String nombre, String descripcion, int cantidadLikes, int cantidadGuardados, Date fechaInicio, Date fechaFinal, String lugar, String color, String foto, String fotoCreador, String nombreCreador, Boolean tieneLike, Boolean tieneGuardado)
    {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadLikes = cantidadLikes;
        this.cantidadGuardados = cantidadGuardados;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.lugar = lugar;
        this.color = color;
        this.foto = foto;
        this.fotoCreador = fotoCreador;
        this.nombreCreador = nombreCreador;
        this.tieneLike = tieneLike;
        this.tieneGuardado = tieneGuardado;
    }
    
    //<editor-fold desc="Getters & Setters">
    public Boolean getTieneLike()
    {
        return tieneLike;
    }
    
    public Boolean getTieneGuardado()
    {
        return tieneGuardado;
    }
    
    public int getPosicionLista()
    {
        return posicionLista;
    }
    
    public void setPosicionLista(int posicionLista)
    {
        this.posicionLista = posicionLista;
    }
    
    public int getIdEvento()
    {
        return idEvento;
    }
    
    public void setIdEvento(int idEvento)
    {
        this.idEvento = idEvento;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    public Date getFechaInicio()
    {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFinal()
    {
        return fechaFinal;
    }
    public void setFechaFinal(Date fechaFinal)
    {
        this.fechaFinal = fechaFinal;
    }
    public String getLugar()
    {
        return lugar;
    }
    public void setLugar(String lugar)
    {
        this.lugar = lugar;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public int getCantidadLikes()
    {
        return cantidadLikes;
    }
    public void setCantidadLikes(int cantidadLikes)
    {
        this.cantidadLikes = cantidadLikes;
    }
    public String getFoto()
    {
        return foto;
    }
    public void setFoto(String foto)
    {
        this.foto = foto;
    }
    public int getCantidadGuardados()
    {
        return cantidadGuardados;
    }
    public void setCantidadGuardados(int cantidadGuardados)
    {
        this.cantidadGuardados = cantidadGuardados;
    }
    public String getFotoCreador()
    {
        return fotoCreador;
    }
    public void setFotoCreador(String fotoCreador)
    {
        this.fotoCreador = fotoCreador;
    }
    public String getNombreCreador()
    {
        return nombreCreador;
    }
    public void setNombreCreador(String nombreCreador)
    {
        this.nombreCreador = nombreCreador;
    }
    //</editor-fold>
    
    public static void obtenerEventos(Context context, MuestraEventos muestraEventos)
    {
        SolicitudHTTP solicitud = new SolicitudHTTP(context, "obtener_eventos")
        {
            @Override
            public void eventoRespuestaHTTP(String respuesta)
            {
                //Recibir arreglo de eventos
                Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Evento[] test = g.fromJson(respuesta, Evento[].class);
    
                //Convertirlo en lista
                ArrayList<Evento> listaEventos = new ArrayList<>();
                for (Evento evento : test)
                {
                    listaEventos.add(evento);
                }
    
                //Actualizar eventos mostrados en el inicio
                muestraEventos.setEventos(listaEventos);
            }
        };
        solicitud.getParametros().put("id_usuario_sesion", Configuraciones.getIdUsuarioSesion() + "");
        solicitud.ejecutar();
    }
    
    public void toggleLike(Context context)
    {
        tieneLike ^= true;
        
        String servicio;
        if(tieneLike)
        {
            cantidadLikes++;
            servicio = "recibir_like";
        }
        else
        {
            cantidadLikes--;
            servicio = "recibir_dislike";
        }
    
        SolicitudHTTP solicitud = new SolicitudHTTP(context, servicio)
        {
            @Override
            public void eventoRespuestaHTTP(String response)
            {
                //No hacer nada
            }
        };
        solicitud.getParametros().put("idUsuario", Configuraciones.getIdUsuarioSesion() + "");
        solicitud.getParametros().put("idEvento", idEvento + "");
        solicitud.ejecutar();
    }
    
    public void toggleGuardar(Context context)
    {
        tieneGuardado ^= true;
        
        if(tieneGuardado)
        {
            cantidadGuardados++;
        }
        else
        {
            cantidadGuardados--;
        }
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
