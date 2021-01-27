package com.teamihc.ucalendar.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.activities.DetallesEventoActivity;
import com.teamihc.ucalendar.adapters.FeedRVAdapter;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class InicioFragment extends Fragment implements MuestraEventos
{
    public ArrayList<Evento> eventos;
    public void setEventos(ArrayList<Evento> eventos)
    {
        this.eventos = eventos;
    }
    
    private SwipeRefreshLayout swipeRefresh;
    private Button testVerMas;
    private ImageView testImg;
    private RecyclerView recyclerView;
    private FeedRVAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes();
    }
    
    private void inicializarComponentes()
    {
        //Swipe-refresh
        swipeRefresh = getView().findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener
        (
            new SwipeRefreshLayout.OnRefreshListener()
            {
                @Override
                public void onRefresh()
                {
                    refrescarEventos();
                }
            }
        );
        
        //Imagen
        testImg = getView().findViewById(R.id.img_test);
    
        //Botón ver más
        testVerMas = getView().findViewById(R.id.test_card).findViewById(R.id.btnVerMas);
        testVerMas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity(), DetallesEventoActivity.class);
                startActivity(i);
            }
        });
        
        
        //Recycler
        eventos = new ArrayList<>();
       /* recyclerView = (RecyclerView) view.findViewById(R.id.recyclerFeed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);*/
    }
    
    private void refrescarEventos()
    {
        Evento.obtenerEventos(getActivity(), this);
        
        //Mostrar cargar por 1seg
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                swipeRefresh.setRefreshing(false);
            }
        });
        t.start();
    }
}