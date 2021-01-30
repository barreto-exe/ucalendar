package com.teamihc.ucalendar.backend.entidades;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.SolicitudHTTP;
import com.teamihc.ucalendar.backend.basedatos.Configuraciones;
import com.teamihc.ucalendar.backend.basedatos.SqliteOp;
import com.teamihc.ucalendar.fragments.MuestraEventos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static com.teamihc.ucalendar.helper.NotificacionHelper.CANAL_ID;
public class Evento implements Serializable
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
    private NotificationManagerCompat notificationManager;
    
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
    
    public static void obtenerEventos(Context context, MuestraEventos muestraEventos, Boolean soloGuardados)
    {
        String servicio = "obtener_eventos";
        if(soloGuardados)
        {
            servicio += "_guardados";
        }
        
        SolicitudHTTP solicitud = new SolicitudHTTP(context, servicio)
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
                    
                    //Si tiene guardado, refrescar en BBDD local
                    if(evento.tieneGuardado)
                    {
                        evento.eliminarBBDD();
                        evento.guardarInteres();
                    }
                }
    
                //Actualizar eventos mostrados en el inicio
                muestraEventos.setEventos(listaEventos);
            }
    
            @Override
            public void eventoRespuestaErrorHTTP()
            {
            
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
            servicio = "recibir_like_borrado";
        }
    
        SolicitudHTTP solicitud = new SolicitudHTTP(context, servicio)
        {
            @Override
            public void eventoRespuestaHTTP(String response)
            {
                //No hacer nada
            }
    
            @Override
            public void eventoRespuestaErrorHTTP()
            {
        
            }
        };
        solicitud.getParametros().put("idUsuario", Configuraciones.getIdUsuarioSesion() + "");
        solicitud.getParametros().put("idEvento", idEvento + "");
        solicitud.ejecutar();
    }
    public void toggleGuardar(Context context)
    {
        tieneGuardado ^= true;
        String servicio;
        
        if(tieneGuardado)
        {
            cantidadGuardados++;
            servicio = "recibir_guardar";
            guardarInteres();
            crearNotificacion(context);
        }
        else
        {
            cantidadGuardados--;
            servicio = "recibir_guardar_borrado";
            eliminarGuardado();
        }
    
        SolicitudHTTP solicitud = new SolicitudHTTP(context, servicio)
        {
            @Override
            public void eventoRespuestaHTTP(String response)
            {
                //No hacer nada
            }
    
            @Override
            public void eventoRespuestaErrorHTTP()
            {
        
            }
        };
        solicitud.getParametros().put("idUsuario", Configuraciones.getIdUsuarioSesion() + "");
        solicitud.getParametros().put("idEvento", idEvento + "");
        solicitud.ejecutar();
    }
    
    private void guardarInteres()
    {
        registrarBBDD();
        
        String query = "INSERT INTO guardados VALUES (?)";
        SqliteOp op = new SqliteOp(query);
        op.pasarParametro(idEvento);
        op.ejecutar();
    }
    private void eliminarGuardado()
    {
        eliminarBBDD();
        
        String query =
            "DELETE FROM guardados WHERE id_evento = ?";
        SqliteOp op = new SqliteOp(query);
        op.pasarParametro(idEvento);
        op.ejecutar();
    }
    
    public void registrarBBDD()
    {
        String query =
            "INSERT INTO eventos " +
            "(id_evento, nombre, descripcion, fecha_inicio, fecha_final, lugar, color, " +
            "cantidad_likes, cantidad_guardados, foto, fotoCreador, nombreCreador) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
        SqliteOp op = new SqliteOp(query);
        op.pasarParametro(idEvento);
        op.pasarParametro(nombre);
        op.pasarParametro(descripcion);
        op.pasarParametro(Herramientas.formatearFechaTiempoBBDD(fechaInicio));
        op.pasarParametro(Herramientas.formatearFechaTiempoBBDD(fechaFinal));
        op.pasarParametro(lugar);
        op.pasarParametro(color);
        op.pasarParametro(cantidadLikes);
        op.pasarParametro(cantidadGuardados);
        op.pasarParametro(foto);
        op.pasarParametro(fotoCreador);
        op.pasarParametro(nombreCreador);
        op.ejecutar();
    }
    public void eliminarBBDD()
    {
        String query = "DELETE FROM eventos WHERE id_evento = ?";
        SqliteOp op = new SqliteOp(query);
        op.pasarParametro(idEvento);
        op.ejecutar();
    }
    
    @Override
    public String toString()
    {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                '}';
    }


    public void crearNotificacion(Context context)
    {
        notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new androidx.core.app.NotificationCompat.Builder(context, CANAL_ID).
                setSmallIcon(R.drawable.ucalendar_logo).
                setContentTitle(nombre).
                setContentText(descripcion).
                setPriority(NotificationCompat.PRIORITY_DEFAULT).
                setCategory(NotificationCompat.CATEGORY_EVENT).
                setColor(Color.GREEN).build();

        notificationManager.notify(1, notification);
    }
}
