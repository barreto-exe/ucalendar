package com.teamihc.ucalendar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AgendaRVAdapter{/*
        extends RecyclerView.Adapter<AgendaRVAdapter.AgendaAdapter>
        implements View.OnClickListener{

    // Para almacenar los eventos guardados
    //private ArrayList<Evento> listaEventosGuardados;
    private CardView cardView;

    public AgendaRVAdapter(ArrayList<Evento> listaEventosGuardados)
    {
        this.listaEventosGuardados = listaEventosGuardados;
    }

    @Override
    public void onClick(View view) {
        // Falta programar el cambio de pantalla
    }

    @NonNull
    @Override
    public AgendaAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate((R.layout.view_info_evento), parent, false);
        view.setOnClickListener(this);
        return new AgendaAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaAdapter holder, int position) {
        //holder.asignarDatos(listaEventosGuardados.get(position));
    }

    @Override
    public int getItemCount() { return listaEventosGuardados.size(); }

    public class AgendaAdapter extends RecyclerView.ViewHolder
    {
        private View view;

        public AgendaAdapter(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            //cardView = (CardView) itemView.findViewById(R.id.info_evento_agenda);
        }

        public void asignarDatos(Evento evento)
        {
            // Aquí se llena el CardView cuando esté listo uwu
        }
    }*/
}
