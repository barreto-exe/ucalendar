package com.teamihc.ucalendar.fragments;


import android.app.Fragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.CalendarioRVAdapter;
import com.teamihc.ucalendar.backend.entidades.Evento;

import java.util.ArrayList;

public class CalendarioFragment extends Fragment
{
    private RecyclerView recyclerView;
    private CalendarioRVAdapter adapter;
    private ArrayList<Evento> listaEventos;

    // Crear un Layout con una imagen de bienvenida para cuando el usuario no tenga guardado ningún evento
    //private LinearLayout bienvenida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView
        recyclerView = getView().findViewById(R.id.eventosDelmes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);

        listaEventos = new ArrayList<Evento>();
        // Insertar función que carga en la lista los eventos actualizados
        adapter = new CalendarioRVAdapter(listaEventos);
        recyclerView.setAdapter(adapter);
    }
}