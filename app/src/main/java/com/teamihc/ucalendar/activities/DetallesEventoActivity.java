package com.teamihc.ucalendar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.FeedRVAdapter;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Evento;
import com.teamihc.ucalendar.controls.LikeableImageView;

import de.hdodenhof.circleimageview.CircleImageView;


public class DetallesEventoActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private Evento evento;
    private FeedRVAdapter feedAdapter;
    private Button btnVerMas;

    private TextView txtNombreEvento;
    private TextView txtNombreCreador;
    private TextView txtDescripcion;
    private TextView txtDescripcionDetallada;
    private TextView txtCantLikeInteresados;
    private TextView txtLugar;
    private TextView txtHora;
    private TextView txtFecha;
    private LikeableImageView imgEvento;
    private CircleImageView imgCreador;
    private ToggleButton btnLike;
    private ToggleButton btnGuardar;

    public DetallesEventoActivity()
    {
        evento = null;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_evento);
    
        toolbar = findViewById(R.id.toolbardetalles);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    
        inicializarParametrosIntent();
        inicializarComponentes();
        inicializarInformacion();
        inicializarEventos();
    }

    private void inicializarParametrosIntent()
    {
        evento = (Evento) getIntent().getExtras().getSerializable("evento");
        feedAdapter = (FeedRVAdapter) getIntent().getExtras().getSerializable("adapter");
    }
    private void inicializarComponentes()
    {
        //Inicializar
        txtNombreEvento = findViewById(R.id.nombreEvento);
        txtNombreCreador = findViewById(R.id.nombreCreador);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtFecha = findViewById(R.id.txtFecha);
        txtHora = findViewById(R.id.txtHora);
        txtLugar = findViewById(R.id.txtLugar);
        txtDescripcionDetallada = findViewById(R.id.txtDescripcionDetalle);
        txtCantLikeInteresados = findViewById(R.id.txtCantLikesInteresados);
        imgEvento = findViewById(R.id.imgEvento);
        imgCreador = findViewById(R.id.imgCreador);
        btnVerMas = findViewById(R.id.btnVerMas);
        btnLike = findViewById(R.id.btnLike);
        btnGuardar = findViewById(R.id.btnGuardar);
    
        //Atachar botón de like al doble tap de la imagen
        imgEvento.setToggleButton(btnLike);
        
        //Hacer invisibles elementos del cardview
        txtDescripcion.setVisibility(View.GONE);
        btnVerMas.setVisibility(View.GONE);
    }
    private void inicializarInformacion()
    {
        //Insertar información
        txtNombreEvento.setText(evento.getNombre());
        txtNombreCreador.setText(evento.getNombreCreador());
        txtFecha.setText(Herramientas.formatearDiaFechaFront(evento.getFechaInicio()));
        txtHora.setText(Herramientas.formatearHoraFront(evento.getFechaInicio()));
        txtLugar.setText(evento.getLugar());
        txtDescripcionDetallada.setText(evento.getDescripcion());
        txtCantLikeInteresados.setText(evento.getCantidadLikes() + " ME GUSTA - " + evento.getCantidadGuardados() + " INTERESADOS");
    
        Glide.with(this).load(evento.getFoto()).into(imgEvento);
        Glide.with(this).load(evento.getFotoCreador()).into(imgCreador);
    }
    private void inicializarEventos()
    {
        //Evento de like
        btnLike.setChecked(evento.getTieneLike());
        btnLike.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                evento.toggleLike(v.getContext());
                actualizarLikeInteresados();
            }
        });
    
        //Evento de guardar
        btnGuardar.setChecked(evento.getTieneGuardado());
        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                evento.toggleGuardar(v.getContext());
                actualizarLikeInteresados();
            }
        });
    
        //Salir de la ventana
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
    private void actualizarLikeInteresados()
    {
        txtCantLikeInteresados.setText(evento.getCantidadLikes() + " ME GUSTA - " + evento.getCantidadGuardados() + " INTERESADOS");
        if(FeedRVAdapter.feedActual != null)
        {
            FeedRVAdapter.feedActual.actualizaInfoEvento(evento);
        }
    }
}