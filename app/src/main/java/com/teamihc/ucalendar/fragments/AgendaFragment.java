package com.teamihc.ucalendar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.AgendaRVAdapter;
import com.teamihc.ucalendar.adapters.FeedRVAdapter;

import java.util.ArrayList;


public class AgendaFragment extends Fragment
{
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private AgendaRVAdapter adapter;
    /*
    // Para almacenar los eventos guardados
    private ArrayList<Evento> listaEventosGuardados;

    // Crear un Layout con una imagen de bienvenida para cuando el usuario no tenga guardado ningún evento
    private LinearLayout bienvenida;
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
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

    private void inicializarComponentes()
    {/*
        // Layout Bienvenida
        //bienvenida = getView().findViewById(R.id.bienvenida_agenda);

        // RecyclerView
        recyclerView = getView().findViewById(R.id.recyclerAgenda);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);


        listaEventosGuardado = new ArrayList<Articulo>();
        // Insertar función que carga en la lista los eventos actualizados
        adapter = new AgendaRVAdapter(listaEventosGuardados);
        recyclerView.setAdapter(adapter);

        ColocarBienvenida();
        */

        //Swipe-refresh
        swipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_agenda);
        swipeRefresh.setOnRefreshListener
                (
                        new SwipeRefreshLayout.OnRefreshListener()
                        {
                            @Override
                            public void onRefresh()
                            {
                                /*  ACTUALIZAR RECYCLER
                                listaEventosGuardados.clear();
                                adapter.notifyDataSetChanged();

                                // Insertar función que carga en la lista los eventos actualizados

                                adapter = new AgendaRVAdapter(listaEventosGuardados, R.layout.view_info_evento_agenda);
                                recyclerView.setAdapter(adapter);

                                ColocarBienvenida();
                                */
                            }
                        }
                );
    }

    /* //Método que verifica si hay evetos guardados o no
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