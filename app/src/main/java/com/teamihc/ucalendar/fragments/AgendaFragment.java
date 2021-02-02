package com.teamihc.ucalendar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.AgendaRVAdapter;
import com.teamihc.ucalendar.backend.Herramientas;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AgendaFragment extends Fragment
{
    //Views
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvPadre;
    
    //Info
    private AgendaRVAdapter adapterPadre;
    private ArrayList<String> listaRVPadre;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        inicializarComponentes();
    }
    
    public void inicializarComponentes()
    {
        //Swipe-refresh
        swipeRefresh = getActivity().findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                actualizarEventosMostrados();
            }
        });
        
        //Recycler fechas padre
        rvPadre = getActivity().findViewById(R.id.recyclerAgenda);
        listaRVPadre = new ArrayList<>();
        for(Date d : Evento.obtenerFechasEventosGuardados())
        {
            listaRVPadre.add(Herramientas.formatearDiaFechaCalendario(d));
        }
        
        //Datos recycler
        adapterPadre = new AgendaRVAdapter(this.getActivity(), listaRVPadre);
        rvPadre.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvPadre.setAdapter(adapterPadre);
    }
    
    public void actualizarEventosMostrados()
    {
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
        
        inicializarComponentes();
    }

    /*
     //Método que verifica si hay evetos guardados o no
       //para mostrar la imagen de bienvenida o el recycler con los eventos
    private void ColocarBienvenida()
    {
        if (listaEventosGuardados.isEmpty())
        {
            bienvenida.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else
        {
            bienvenida.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }*/
}