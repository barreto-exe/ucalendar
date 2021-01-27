package com.teamihc.ucalendar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.adapters.AgendaRVAdapter;
import com.teamihc.ucalendar.adapters.FeedRVAdapter;

import java.util.ArrayList;


public class AgendaFragment extends Fragment
{
    private RecyclerView recyclerView;
    private AgendaRVAdapter adapter;
    //private ArrayList<Evento> listaEventosGuardados;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agenda, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAgenda);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerView.getLayoutManager().setMeasurementCacheEnabled(false);

        // Set adapter
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        /*listaEventosGuardados.clear();
        adapter.notifyDataSetChanged();
        Actualizar lista
        adapter = new AgendaRVAdapter(listaArticulos, R.layout.view_info_producto);
        recyclerView.setAdapter(adapter);
        Articulo.cargarInventarioEnLista(listaArticulos);

        ColocarBienvenida();*/
    }
}